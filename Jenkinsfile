pipeline {
  agent any
  options { timestamps() }
  tools { jdk 'jdk-21' }

  parameters {
    string(name: 'DOCKERFILE_PATH', defaultValue: 'Dockerfile.dockerfile', description: 'Path to Dockerfile relative to repo root')
    string(name: 'BUILD_CONTEXT',  defaultValue: '.',           description: 'Docker build context directory')
    string(name: 'APP_NAME',       defaultValue: 'myapp',       description: 'Image/repo name')
    string(name: 'DOCKERHUB_NAMESPACE', defaultValue: 'bharathdayal', description: 'Docker Hub user/org')
    booleanParam(name: 'RUN_CONTAINER',   defaultValue: true,  description: 'Run container locally after build')
    booleanParam(name: 'PUSH_DOCKER_HUB', defaultValue: true,  description: 'Push image to Docker Hub')
  }

  environment {
    GRADLE_USER_HOME = "${WORKSPACE}/.gradle"
    TAG            = "${env.BUILD_NUMBER}"
    CONTAINER_NAME = "app_${env.JOB_BASE_NAME}"
    EXPOSE_PORT    = "8086"   // change to "-p 8086:8080" below if your app listens on 8080 in-container
  }

  stages {
    stage('Checkout') { steps { checkout scm } }

    stage('Workspace debug') {
      steps {
        sh '''
          echo "PWD=$(pwd)"
          echo "Listing top-level:"
          ls -la
          echo "Looking for Dockerfile candidates:"
          find . -maxdepth 3 -iname "Dockerfile" -o -iname "dockerfile" -print || true
        '''
      }
    }

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

    stage('Build (bootJar)') {
      steps {
        sh './gradlew --no-daemon clean bootJar -x test --stacktrace --info'
      }
      post {
        success { archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true }
      }
    }

    stage('Docker Build') {
    agent { docker { image 'docker:27-cli' args '-v /var/run/docker.sock:/var/run/docker.sock' } }
    steps {
      sh "docker version"
      sh "docker build -f ${params.DOCKERFILE_PATH} -t ${APP_NAME}:${TAG} ${params.BUILD_CONTEXT}"
      sh "docker tag ${APP_NAME}:${TAG} ${APP_NAME}:latest"
    }
}

    

    stage('Run Locally') {
      when { expression { return fileExists(params.DOCKERFILE_PATH) && params.RUN_CONTAINER } }
      steps {
        sh """
          docker rm -f ${CONTAINER_NAME} || true
          # If your app listens on 8080 inside the container, change to: -p ${EXPOSE_PORT}:8090
          docker run -d --name ${CONTAINER_NAME} -p ${EXPOSE_PORT}:${EXPOSE_PORT} ${params.APP_NAME}:latest
        """
      }
    }

    stage('Push to Docker Hub') {
      when { expression { return fileExists(params.DOCKERFILE_PATH) && params.PUSH_DOCKER_HUB } }
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
          sh """
            echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin
            docker tag ${params.APP_NAME}:${TAG} ${params.DOCKERHUB_NAMESPACE}/${params.APP_NAME}:${TAG}
            docker tag ${params.APP_NAME}:latest ${params.DOCKERHUB_NAMESPACE}/${params.APP_NAME}:latest
            docker push ${params.DOCKERHUB_NAMESPACE}/${params.APP_NAME}:${TAG}
            docker push ${params.DOCKERHUB_NAMESPACE}/${params.APP_NAME}:latest
            docker logout
          """
        }
      }
    }
  }

  post {
    success { echo "✔ Built ${params.APP_NAME}:${TAG}. Pushed/run if enabled." }
    failure { echo "✖ Build failed" }
  }
}
