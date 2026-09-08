# Jenkins CI/CD + SonarQube Project

## Project Overview

This project demonstrates an end-to-end CI/CD pipeline using **GitHub, Jenkins, SonarQube, Docker, Docker Hub, and AWS EC2**.

The main learning objective is **SonarQube integration with Jenkins** for continuous code-quality and security analysis.

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
Web Application
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
```

## Application

The project contains a simple Java application in `src/Main.java` specifically for practicing **SonarQube static code analysis**.

The repository also contains an HTML web application packaged using Docker and served through Nginx.

## SonarQube Setup in Jenkins

The Jenkins pipeline expects a Jenkins SonarQube installation named:

```text
sonarqube
```

The Jenkins server must also have the SonarScanner CLI available as `sonar-scanner`.

The SonarQube authentication token should be configured securely in Jenkins/SonarQube rather than committed to GitHub.

## SonarQube Learning Objectives

1. Install and configure SonarQube
2. Create a SonarQube project
3. Generate a SonarQube authentication token
4. Configure SonarQube in Jenkins
5. Install/configure SonarScanner
6. Run SonarQube analysis from Jenkins
7. Understand Bugs
8. Understand Vulnerabilities
9. Understand Code Smells
10. Understand Security Hotspots
11. Understand Duplications
12. Understand Code Coverage
13. Understand Reliability, Security, and Maintainability ratings
14. Configure a Quality Gate
15. Make Jenkins validate the Quality Gate
16. Fix issues and run the analysis again

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
7. Deploy Container
```

If the SonarQube Quality Gate fails, the Jenkins pipeline stops before Docker build/push/deployment.

## Docker

The Dockerfile uses Nginx to serve the HTML application:

```dockerfile
FROM nginx:alpine
COPY . /usr/share/nginx/html
EXPOSE 80
```

The Java source is included in the repository for SonarQube analysis; the Docker image continues to package the web application with Nginx.

## Deployment

The Docker container is deployed on an AWS EC2 instance and exposed on port `8081`.

```text
http://<EC2-PUBLIC-IP>:8081
```

## Current Project Flow

```text
GitHub
   -> Jenkins Webhook
   -> Checkout
   -> SonarQube Analysis
   -> Quality Gate
   -> Docker Build
   -> Docker Hub
   -> EC2
   -> Docker Container
   -> Nginx
   -> Browser
```

## Project Outcome

The final objective is an automated pipeline where a GitHub push triggers Jenkins, SonarQube analyzes the source code, the Quality Gate decides whether the pipeline may continue, and only approved builds are packaged and deployed using Docker on AWS EC2.
