pipeline {
  agent any
  options {
    timestamps()
   
  }
  tools { jdk 'jdk-21' }

  parameters {
    string(name: 'APP_NAME', defaultValue: 'myapp', description: 'Image/repo name')
    string(name: 'DOCKERHUB_NAMESPACE', defaultValue: 'bharathdayal', description: 'Docker Hub user/org')
    booleanParam(name: 'SMOKE_JAR', defaultValue: true, description: 'Run jar locally as a smoke test (no Docker)')

    // AWS / EC2 deployment params
    string(name: 'AWS_ACCOUNT_ID', defaultValue: '115047389529', description: 'AWS Account ID')
    string(name: 'AWS_REGION', defaultValue: 'us-east-2', description: 'AWS region')
    string(name: 'ECR_REPO', defaultValue: 'myapp', description: 'ECR repository name (usually same as APP_NAME)')
    string(name: 'EC2_HOST', defaultValue: 'ec2-18-219-94-140.us-east-2.compute.amazonaws.com', description: 'EC2 public DNS or IP')
    string(name: 'EC2_USER', defaultValue: 'ubuntu', description: 'SSH user (ec2-user, ubuntu, etc.)')
  }

  environment {
    GRADLE_USER_HOME = "${WORKSPACE}/.gradle"
    TAG             = "${env.BUILD_NUMBER}"
    EXPOSE_PORT     = "8086"
    // ECR registry URL
    ECR_REGISTRY    = "${params.AWS_ACCOUNT_ID}.dkr.ecr.${params.AWS_REGION}.amazonaws.com"
    ECR_IMAGE       = "${params.AWS_ACCOUNT_ID}.dkr.ecr.${params.AWS_REGION}.amazonaws.com/${params.ECR_REPO}"
  }

  stages {
    stage('Checkout') { steps { checkout scm } }



 stage('Prep Gradle') {
  steps {
    sh 'bash -lc "set -euo pipefail; sed -i -e \'s/\\r$//\' gradlew || true; chmod +x gradlew; ./gradlew --version; java -version"'
  }
}

    stage('Build JAR') {
      steps {
       sh '''
        bash -lc "
          set -euo pipefail
          ./gradlew --no-daemon clean bootJar -x test --stacktrace --info
        "
        '''
      }
      post { success { archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true } }
    }

    // ---- NEW: Publish image to Amazon ECR using Jib (no local Docker daemon required) ----
    stage('Publish (Jib → Amazon ECR)') {
      environment {
        AWS_DEFAULT_REGION = "${params.AWS_REGION}"
      }
      steps {
        withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-creds']]) {
          sh '''
            set -euo pipefail

            echo "Ensuring ECR repo exists: ${ECR_IMAGE}"
            aws ecr describe-repositories --repository-names "${params.ECR_REPO}" >/dev/null 2>&1 || \
              aws ecr create-repository --repository-names "${params.ECR_REPO}" >/dev/null

            echo "Building & pushing with Jib to ECR..."
            ./gradlew --no-daemon jib \
              -Djib.to.image=${ECR_IMAGE} \
              -Djib.to.tags=${TAG},latest \
              -Djib.from.image=eclipse-temurin:21-jre \
              -Djib.container.ports=${EXPOSE_PORT} \
              --stacktrace --info
          '''
        }
      }
    }

    // Optional local smoke test
    stage('Smoke test (run jar)') {
      when { expression { return params.SMOKE_JAR } }
      steps {
        sh """
          nohup java -jar build/libs/*.jar --server.port=${EXPOSE_PORT} > app.log 2>&1 &
          echo \$! > app.pid
          sleep 8
          curl -sf http://localhost:${EXPOSE_PORT}/ || true
          kill \$(cat app.pid) || true
        """
      }
      post {
        always { archiveArtifacts artifacts: 'app.log', onlyIfSuccessful: false }
      }
    }

    // ---- NEW: Deploy on EC2 (pull from ECR and (re)start) ----
    stage('Deploy to EC2') {
      steps {
        sshagent(credentials: ['ec2-ssh']) {
          sh '''
            set -e

            echo "Logging in to ECR on remote host and deploying ${ECR_IMAGE}:${TAG}"

            ssh -o StrictHostKeyChecking=no ${params.EC2_USER}@${params.EC2_HOST} bash -s <<'REMOTE'
              set -euo pipefail
              APP_NAME="${APP_NAME}"
              PORT="${EXPOSE_PORT}"
              ECR="${ECR_IMAGE}"
              TAG="${TAG}"
              REGION="${AWS_REGION}"

              # Login to ECR on the EC2 machine (requires AWS CLI + credentials/role on EC2)
              aws ecr get-login-password --region "$REGION" | \
                 docker login --username AWS --password-stdin ${ECR_REGISTRY}

              # Pull the new image
              docker pull "${ECR}:${TAG}"

              # Stop & remove existing container if present
              if docker ps -a --format '{{.Names}}' | grep -q "^${APP_NAME}$"; then
                echo "Stopping existing container ${APP_NAME}..."
                docker stop "${APP_NAME}" || true
                docker rm   "${APP_NAME}" || true
              fi

              # Run the new container
              echo "Starting container ${APP_NAME} on port ${PORT}..."
              docker run -d --name "${APP_NAME}" \
                -p ${PORT}:${PORT} \
                --restart=always \
                "${ECR}:${TAG}"

              # Optionally prune old images (keep current and latest)
              docker image prune -f >/dev/null 2>&1 || true
            REMOTE
          '''
        }
      }
    }
  }

  post {
    success {
      echo "✔ Built JAR and pushed ${ECR_IMAGE}:${TAG} (and :latest). Deployed to EC2 ${params.EC2_HOST}."
    }
    failure { echo "✖ Build/Deploy failed" }
  }
}
