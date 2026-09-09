# Jenkins CI/CD + SonarQube + Docker + EC2 + Monitoring

## Project Overview

This project demonstrates an end-to-end CI/CD pipeline using **GitHub, Jenkins, SonarQube, Docker, Docker Hub, and AWS EC2**, with **Prometheus and Grafana** used for monitoring.

The main learning objective is to build a practical CI/CD pipeline from source-code change to container deployment, while enforcing code quality with SonarQube and monitoring the deployed application/server.

## Architecture

```text
Developer
   |
   | git push
   v
GitHub
   |
   | Webhook
   v
Jenkins (AWS EC2)
   |
   +--> Checkout Code
   |
   +--> SonarQube Analysis
   |
   +--> Quality Gate
   |       |
   |       +--> PASS -> Docker Build -> Docker Hub -> EC2 Deploy
   |       |
   |       +--> FAIL -> Pipeline Stops
   |
   v
Application EC2
   |
   +--> Docker Container + Nginx
   |
   +--> Prometheus -> Metrics
   |
   +--> Grafana -> Dashboards
```

## Technologies Used

- AWS EC2 (Ubuntu)
- GitHub
- Jenkins
- SonarQube
- Java
- Docker
- Docker Hub
- Nginx
- Prometheus
- Grafana

## Repository Structure

```text
Jenkins-CI-CD/
|
+-- index.html
+-- src/
|   +-- Main.java
+-- Dockerfile
+-- Jenkinsfile
+-- sonar-project.properties
+-- README.md
+-- kubernetes/
    +-- deployment.yaml
    +-- hpa.yaml
    +-- namespace.yaml
    +-- service.yaml
```

The `kubernetes/` directory is kept in the repository for future Kubernetes/EKS learning. It is **not used by the current CI/CD deployment pipeline**.

## Application

The project contains a simple Java application in `src/Main.java` specifically for practicing **SonarQube static code analysis**.

The repository also contains an HTML web application packaged using Docker and served through Nginx.

## SonarQube Setup in Jenkins

The Jenkins pipeline expects a Jenkins SonarQube installation named:

```text
sonarqube
```

The Jenkins server must also have the SonarScanner CLI configured as the Jenkins tool named:

```text
SonarScanner
```

The SonarQube authentication token should be configured securely in Jenkins/SonarQube rather than committed to GitHub.

## Jenkins Credentials Required

Create these credentials in Jenkins:

```text
sonar-token       -> Secret text
                   -> SonarQube authentication token

 dockerhub-cred   -> Username with password
                   -> Docker Hub username + access token/password

app-ec2-ssh       -> SSH Username with private key
                   -> Ubuntu SSH key for the application EC2 server
```

The `app-ec2-ssh` credential is used by the Jenkins pipeline to connect securely to the application EC2 server.

## Jenkins Pipeline Stages

```text
1. Checkout Code
        |
2. SonarQube Analysis
        |
3. Quality Gate
        |
   PASS only
        |
4. Build Docker Image
        |
5. Login to Docker Hub
        |
6. Push Docker Image
        |
7. Deploy to Application EC2
```

If the SonarQube Quality Gate fails, the Jenkins pipeline stops before Docker build, push, and deployment.

## Docker

The Dockerfile uses Nginx to serve the HTML application:

```dockerfile
FROM nginx:alpine
COPY . /usr/share/nginx/html
EXPOSE 80
```

The Java source is included in the repository for SonarQube analysis; the Docker image packages the web application with Nginx.

## EC2 Deployment

The Jenkins pipeline pushes a versioned Docker image to Docker Hub using the Jenkins build number as the image tag.

Example:

```text
jack1503/jack-devops-app:15
```

The application EC2 server then pulls that image and runs it as:

```text
jenkins-cicd-app
```

The container exposes port `80` internally and is published on EC2 port `8081`.

```text
http://<EC2-PUBLIC-IP>:8081
```

## Jenkins Deployment Parameter

The Jenkinsfile contains an `APP_SERVER` parameter.

Example:

```text
ubuntu@<APP-EC2-PUBLIC-IP>
```

Replace the default placeholder with the actual application EC2 SSH target when running the Jenkins job.

## Monitoring

Monitoring is separate from the CI/CD deployment stages.

```text
Application EC2
      |
      v
Prometheus
      |
      v
Grafana Dashboards
```

Prometheus collects metrics and Grafana visualizes them in dashboards. Monitoring does not replace the Jenkins deployment pipeline.

## Current Project Flow

```text
GitHub
   -> Jenkins Webhook
   -> Checkout
   -> SonarQube Analysis
   -> Quality Gate
   -> Docker Build
   -> Docker Hub
   -> Application EC2
   -> Docker Container
   -> Nginx
   -> Browser

Monitoring:
Application EC2
   -> Prometheus
   -> Grafana
```

## Kubernetes / EKS

Kubernetes and Amazon EKS are intentionally kept for a later learning stage. The current pipeline does **not** call `aws eks`, `kubectl`, or deploy to Kubernetes.

## Project Outcome

The final objective is an automated DevOps workflow where a GitHub push triggers Jenkins, SonarQube validates code quality, Docker packages the application, Docker Hub stores the image, and the approved image is automatically deployed to an AWS EC2 application server. Prometheus and Grafana provide monitoring and visualization for the running environment.
