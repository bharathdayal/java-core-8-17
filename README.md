# Java ◦ Jenkins ◦ Docker — Quick Start (with Windows notes)

This README shows how to run **Jenkins in Docker**, free up ports on Windows if needed, and **build & run a Spring Boot app** in a container using the provided `Dockerfile`.

---

## Prerequisites
- Docker installed (Desktop on Windows/macOS, Engine on Linux).
- Git installed.
- Java 21 SDK on your dev machine (JDK not required inside the app image).

---

## 1) Pull Jenkins image
You can use either the LTS tag or a JDK 21 tag:
```bash
docker pull jenkins/jenkins:lts
# or
docker pull jenkins/jenkins:jdk21
```

---

## 2) Run Jenkins in Docker

### Option A — Simple (no Docker access inside Jenkins)
```bash
docker run -d --name jenkins \
  -p 8080:8080 -p 50000:50000 \
  -v jenkins_home:/var/jenkins_home \
  jenkins/jenkins:lts
```

### Option B — Jenkins with access to host Docker (build images from Jenkins)
Mount the Docker socket (host) so Jenkins jobs can run `docker build`, `docker run`, etc.
```bash
docker run -d --name jenkins \
  -p 8080:8080 -p 50000:50000 \
  -v jenkins_home:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v /usr/bin/docker:/usr/bin/docker \
  jenkins/jenkins:lts
```

> **Windows tip:** if port **8080** is busy, map another host port to the container’s **8080** (Jenkins listens on 8080 **in** the container):
```bash
# Jenkins on http://localhost:8090
docker run -d --name jenkins \
  -p 8090:8080 -p 50000:50000 \
  -v jenkins_home:/var/jenkins_home \
  jenkins/jenkins:lts
```

### Freeing port 8080 on Windows (PowerShell)
```powershell
netstat -ano | findstr :8080      # get PID using 8080
taskkill /PID <PID_FROM_ABOVE> /F # stop the process
```

---

## 3) Spring Boot app — Dockerfile
Use the provided Dockerfile to package your app:

```dockerfile
# Dockerfile
FROM eclipse-temurin:21-jre

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8086
ENTRYPOINT ["java","-jar","/app.jar"]
```

> **Important:** Spring Boot’s default port is **8080**.  
> - If you keep `EXPOSE 8086`, set `server.port=8086` in `src/main/resources/application.properties` **or** run with `--server.port=8086`.  
> - Otherwise, change `EXPOSE 8086` to `EXPOSE 8080` and map ports accordingly.

---

## 4) Build and run the app locally (no Jenkins)

```bash
# from project root
./gradlew clean bootJar -x test

# build the image (Dockerfile in repo root)
docker build -t myapp:latest .

# run the container
# If your app listens on 8086 inside the container (EXPOSE 8086):
docker run -d --name myapp -p 8086:8086 myapp:latest

# If your app listens on 8080 inside the container:
docker run -d --name myapp -p 8086:8080 myapp:latest
```

---

## 5) Minimal Jenkins Pipeline (optional)
Add a `Jenkinsfile` to your repo to build the JAR and (optionally) build & run the image. This assumes Jenkins can use Docker (Option B above).

```groovy
pipeline {
  agent any
  options { timestamps() }
  tools { jdk 'jdk-21' }

  environment {
    GRADLE_USER_HOME = "${WORKSPACE}/.gradle"
    APP_NAME = "myapp"
    TAG = "${env.BUILD_NUMBER}"
    EXPOSE_PORT = "8086" // change to 8080 if your app uses the default port
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
      steps { sh './gradlew --no-daemon clean bootJar -x test --stacktrace --info' }
      post { success { archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true } }
    }

    stage('Docker Build') {
      when { expression { return fileExists('Dockerfile') || fileExists('Dockerfile.dockerfile') } }
      steps {
        sh '''
          DOCKERFILE="Dockerfile"
          [ -f "Dockerfile.dockerfile" ] && DOCKERFILE="Dockerfile.dockerfile"
          docker build -f "$DOCKERFILE" -t ${APP_NAME}:${TAG} .
          docker tag ${APP_NAME}:${TAG} ${APP_NAME}:latest
        '''
      }
    }

    stage('Run Locally') {
      when { expression { return fileExists('Dockerfile') || fileExists('Dockerfile.dockerfile') } }
      steps {
        sh '''
          docker rm -f ${APP_NAME} || true
          # If app listens on 8086 in-container:
          docker run -d --name ${APP_NAME} -p ${EXPOSE_PORT}:${EXPOSE_PORT} ${APP_NAME}:latest
          # For 8080 in-container, change to: -p ${EXPOSE_PORT}:8080
        '''
      }
    }
  }
}
```

---

## 6) Common issues & fixes

- **Port already in use (Windows):** use `netstat -ano | findstr :8080` then `taskkill /PID <PID> /F`, or run Jenkins on another port mapping (`-p 8090:8080`).  
- **`docker: Permission denied` in Jenkins container:** re-run Jenkins with the Docker socket and binary mounted (Option B).  
- **`gradlew: not found` or CRLF issues on Linux agents:** run `sed -i -e 's/\r$//' gradlew && chmod +x gradlew`.  
- **Java 21 mismatch:** ensure your base image is `eclipse-temurin:21-jre` and Jenkins toolchain uses JDK 21.

---

## 7) Clean up
```bash
docker rm -f jenkins myapp || true
docker volume rm jenkins_home || true
docker image rm myapp:latest || true
```

---

### Quick references
- Jenkins UI: http://localhost:8080 (or http://localhost:8090 if you mapped 8090:8080)
- Jenkins home (volume): `jenkins_home`
- App container: `myapp` (in examples above)
