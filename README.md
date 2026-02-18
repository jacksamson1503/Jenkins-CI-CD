# Jenkins-CI-C

To build an automated CI/CD pipeline where:
When code is pushed to GitHub
Jenkins automatically builds Docker image
Pushes image to Docker Hub
Deploys updated container on EC2
Website updates automatically

ARCHITECTURE
GitHub → Webhook → Jenkins → Docker Build → Docker Hub → EC2 Container → Browser

CREATED EC2 INSTANCE
Installed Jenkins
Installed Docker
Opened port 8080 (Jenkins)
Opened port 8081 (Application)

CREATED GITHUB REPO(Refer in my Repo for code)
Files should be there:
index.html
click.html
Dockerfile
Jenkinsfile

CREATED DOCKERFILE
FROM nginx:alpine
COPY . /usr/share/nginx/html
EXPOSE 80
Purpose:
Use Nginx image
Copy HTML files
Serve website on port 80

CREATED JENKINS PIPELINE (Jenkinsfile)
Stages:
Checkout code
Build Docker image
Login to Docker Hub
Push image
Remove old container
Run new container

CONFIGURED THE DOCKER CRENDIALES IN JENKINS
global
username:xxxxx
password:xxxxx
id:xxxx



