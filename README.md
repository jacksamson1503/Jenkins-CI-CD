# Jenkins CI/CD + SonarQube Project

## Project Overview

This project demonstrates an end-to-end CI/CD pipeline using **GitHub, Jenkins, SonarQube, Docker, Docker Hub, and AWS EC2**.

The main learning objective of this project is **SonarQube integration with Jenkins** for continuous code-quality and security analysis.

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
   +--> SonarQube Code Analysis
   |
   +--> Quality Gate
   |
   +--> Docker Build
   |
   +--> Docker Hub
   |
   +--> Deploy Container on EC2
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

The project contains a simple Java application in `src/Main.java`. This application is included specifically for practicing **SonarQube static code analysis**.

The repository also contains a simple HTML web application that is packaged using Docker and served through Nginx.

## SonarQube Learning Objectives

This project will be used to learn:

1. SonarQube installation and configuration
2. Creating a SonarQube project
3. Generating a SonarQube authentication token
4. Installing and configuring SonarQube integration in Jenkins
5. Configuring SonarScanner
6. Running SonarQube analysis from Jenkins
7. Understanding Bugs
8. Understanding Vulnerabilities
9. Understanding Code Smells
10. Understanding Security Hotspots
11. Understanding Duplications
12. Understanding Code Coverage
13. Understanding Reliability, Security, and Maintainability ratings
14. Configuring a Quality Gate
15. Making Jenkins validate the Quality Gate
16. Fixing SonarQube issues and running the analysis again

## CI/CD Pipeline Stages

The Jenkins pipeline will contain the following stages:

```text
1. Checkout Code
        |
2. SonarQube Analysis
        |
3. Quality Gate
        |
4. Build Docker Image
        |
5. Login to Docker Hub
        |
6. Push Docker Image
        |
7. Deploy Container
```

## Docker

The Dockerfile uses Nginx to serve the HTML application:

```dockerfile
FROM nginx:alpine
COPY . /usr/share/nginx/html
EXPOSE 80
```

## Deployment

The Docker container is deployed on an AWS EC2 instance and exposed on port `8081`.

```text
http://<EC2-PUBLIC-IP>:8081
```

## Current Project Flow

```text
GitHub
   -> Jenkins Webhook
   -> Jenkins
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

The final objective is to have an automated pipeline where a GitHub push triggers Jenkins, SonarQube checks the code quality, the Quality Gate determines whether the pipeline can continue, and the approved application is packaged and deployed using Docker on AWS EC2.
