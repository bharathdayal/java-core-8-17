pipeline {
  agent any
  options { timestamps() }
  tools { jdk 'jdk-21' }

  parameters {
    booleanParam(name: 'PUSH_DOCKER_HUB', defaultValue: true, description: 'Push image to Docker Hub')
    string(name: 'DOCKERHUB_NAMESPACE', defaultValue: 'bharathdayal', description: 'Docker Hub username/org')
  }

  environment {
    GRADLE_USER_HOME = "${WORKSPACE}/.gradle"
    APP_NAME        = "myapp"
    TAG             = "${env.BUILD_NUMBER}"
    CONTAINER_NAME  = "app_${env.JOB_BASE_NAME}"
    EXPOSE_PORT     = "8086"
  }

  stages {
    stage('Checkout') { steps { checkout scm } }

    stage('Prep Gradle') {
      steps {
        sh '''
          sed -i -e 's/\\r$//' gradlew || true
          chmod +x gradlew
          ./gradlew --version
          java -version
        '''
      }
    }

    stage('Build') {
      steps {
        sh './gradlew --no-daemon clean bootJar -x test --stacktrace --info'
      }
      post {
        success { archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true }
      }
    }

    stage('Docker Build') {
      when { expression { return fileExists('Dockerfile') } }
      steps {
        sh """
          docker build -t ${APP_NAME}:${TAG} .
          docker tag ${APP_NAME}:${TAG} ${APP_NAME}:latest
        """
      }
    }

    stage('Run Locally') {
      when { expression { return fileExists('Dockerfile') } }
      steps {
        sh """
          docker rm -f ${CONTAINER_NAME} || true
          # If app listens on 8080 in-container, change to: -p ${EXPOSE_PORT}:8090
          docker run -d --name ${CONTAINER_NAME} -p ${EXPOSE_PORT}:${EXPOSE_PORT} ${APP_NAME}:latest
        """
      }
    }

    stage('Push to Docker Hub') {
      when {
        allOf {
          expression { return params.PUSH_DOCKER_HUB }
          expression { return fileExists('Dockerfile') }
        }
      }
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
          sh """
            echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin
            docker tag ${APP_NAME}:${TAG} ${params.DOCKERHUB_NAMESPACE}/${APP_NAME}:${TAG}
            docker tag ${APP_NAME}:latest ${params.DOCKERHUB_NAMESPACE}/${APP_NAME}:latest
            docker push ${params.DOCKERHUB_NAMESPACE}/${APP_NAME}:${TAG}
            docker push ${params.DOCKERHUB_NAMESPACE}/${APP_NAME}:latest
            docker logout
          """
        }
      }
    }
  }

  post {
    success { echo "✔ Built ${APP_NAME}:${TAG} and pushed to ${params.DOCKERHUB_NAMESPACE}/${APP_NAME} (if enabled)." }
    failure { echo "✖ Build failed" }
  }
}
