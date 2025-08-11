pipeline {
  agent any

  options { timestamps() }

  parameters {
    string(name: 'APP_NAME', defaultValue: 'myapp', description: 'Image/repo name')
    string(name: 'DOCKERHUB_NAMESPACE', defaultValue: 'yourdockerhubuser', description: 'Docker Hub username/org')
    booleanParam(name: 'RUN_CONTAINER', defaultValue: true, description: 'Run container locally after build')
    booleanParam(name: 'PUSH_DOCKER_HUB', defaultValue: true, description: 'Push image to Docker Hub')
  }

  environment {
    GRADLE_USER_HOME = "${WORKSPACE}/.gradle"
    JAVA_TOOL_OPTIONS = "--enable-preview"   // harmless if not using preview
    TAG = "${env.BUILD_NUMBER}"
    CONTAINER_NAME = "app_${env.JOB_BASE_NAME}"
    EXPOSE_PORT = "8086"                     // host and container port (matches your Dockerfile)
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
        sh 'chmod +x ./gradlew || true'
      }
    }

    stage('Build & Test') {
      steps {
        sh './gradlew --no-daemon clean test'
      }
      post {
        always { junit '**/build/test-results/test/*.xml' }
      }
    }

    stage('Package (bootJar)') {
      steps {
        sh './gradlew --no-daemon bootJar -x test'
      }
      post {
        success { archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true }
      }
    }

    stage('Docker Build') {
      steps {
        sh """
          # Build a local image first
          docker build -t ${params.APP_NAME}:${TAG} .
          docker tag ${params.APP_NAME}:${TAG} ${params.APP_NAME}:latest

          # Tag for Docker Hub
          docker tag ${params.APP_NAME}:${TAG} ${params.DOCKERHUB_NAMESPACE}/${params.APP_NAME}:${TAG}
          docker tag ${params.APP_NAME}:latest ${params.DOCKERHUB_NAMESPACE}/${params.APP_NAME}:latest
        """
      }
    }

    stage('Run Locally (optional)') {
      when { expression { return params.RUN_CONTAINER } }
      steps {
        sh """
          docker rm -f ${CONTAINER_NAME} || true
          docker run -d --name ${CONTAINER_NAME} -p ${EXPOSE_PORT}:${EXPOSE_PORT} ${params.APP_NAME}:latest
        """
      }
    }

  }

  post {
    success { echo "✔ Build ${env.BUILD_NUMBER} OK → Image: ${params.DOCKERHUB_NAMESPACE}/${params.APP_NAME}:${TAG}" }
    failure { echo "✖ Build ${env.BUILD_NUMBER} FAILED" }
    always  { echo "Done." }
  }
}
