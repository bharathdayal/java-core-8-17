pipeline {
  agent any
  options { timestamps() }
  tools { jdk 'jdk-21' }

  environment {
    GRADLE_USER_HOME = "${WORKSPACE}/.gradle"
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Prep Gradle') {
      steps {
        sh '''
          sed -i -e 's/\r$//' gradlew || true
          chmod +x gradlew
          ./gradlew --version
          java -version
        '''
      }
    }

    stage('Build') {
      steps {
         sh 'gradle clean bootJar -x test --no-daemon --stacktrace --info'
      }
      post {
        success { archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true }
      }
    }
  }
}
