pipeline {
  agent any
  options { timestamps() }
  tools { jdk 'jdk-21' }

  parameters {
    string(name: 'APP_NAME', defaultValue: 'myapp', description: 'Image/repo name')
    string(name: 'DOCKERHUB_NAMESPACE', defaultValue: 'bharathdayal', description: 'Docker Hub user/org')
    booleanParam(name: 'SMOKE_JAR', defaultValue: true, description: 'Run jar locally as a smoke test (no Docker)')
  }

  environment {
    GRADLE_USER_HOME = "${WORKSPACE}/.gradle"
    TAG             = "${env.BUILD_NUMBER}"
    EXPOSE_PORT     = "8086" // change if you use a different port
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

    stage('Build JAR') {
      steps {
        sh './gradlew --no-daemon clean bootJar -x test --stacktrace --info'
      }
      post { success { archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true } }
    }

    // Build & PUSH the container image directly to Docker Hub without a Docker daemon
    stage('Image (Jib → Docker Hub, delete tags)') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
          sh """
            NS='${params.DOCKERHUB_NAMESPACE}'
            REPO='${params.APP_NAME}'
            TAG='${TAG}'
    
            echo "Obtaining Docker Hub token..."
            TOKEN=\$(curl -s -H "Content-Type: application/json" \
              -X POST -d "{\\"username\\": \\"$DH_USER\\", \\"password\\": \\"$DH_PASS\\"}" \
              https://hub.docker.com/v2/users/login/ | tr -d '\\n' | sed -E 's/.*"token":"([^"]+)".*/\\1/')
    
            delete_tag() {
              t="\$1"
              echo "Deleting tag: \$NS/\$REPO:\$t (ignore 404)"
              curl -fsS -X DELETE -H "Authorization: JWT \$TOKEN" \
                "https://hub.docker.com/v2/repositories/\$NS/\$REPO/tags/\$t/" || true
            }
    
            if [ -n "\$TOKEN" ] && [ "\$TOKEN" != "null" ]; then
              delete_tag "\$TAG"
              delete_tag "latest"
            else
              echo "WARN: Could not obtain Docker Hub token; skipping tag deletes."
            fi
    
            echo "Building & pushing image with Jib..."
            ./gradlew --no-daemon jib \\
              -Djib.to.image=\${NS}/\${REPO} \\
              -Djib.to.tags=\${TAG},latest \\
              -Djib.from.image=eclipse-temurin:21-jre \\
              -Djib.container.ports=${EXPOSE_PORT} \\
              -Djib.to.auth.username=$DH_USER -Djib.to.auth.password=$DH_PASS \\
              --stacktrace --info
          """
        }
      }
    }

    // Optional smoke test without Docker (runs the jar directly)
    stage('Smoke test (run jar)') {
      when { expression { return params.SMOKE_JAR } }
      steps {
        sh """
          nohup java -jar build/libs/*.jar --server.port=${EXPOSE_PORT} > app.log 2>&1 &
          echo \$! > app.pid
          # basic wait; adjust if your app needs more time
          sleep 8
          # Try a simple ping; change path if you have an endpoint/actuator
          curl -sf http://localhost:${EXPOSE_PORT}/ || true
          kill \$(cat app.pid) || true
        """
      }
      post {
        always { archiveArtifacts artifacts: 'app.log', onlyIfSuccessful: false }
      }
    }
  }

  post {
    success { echo "✔ Built JAR and pushed image ${params.DOCKERHUB_NAMESPACE}/${params.APP_NAME}:${TAG} (and :latest)." }
    failure { echo "✖ Build failed" }
  }
}
