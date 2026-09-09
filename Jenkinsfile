pipeline {
    agent any

    parameters {
        string(
            name: 'APP_SERVER',
            defaultValue: 'ubuntu@YOUR_APP_EC2_IP',
            description: 'SSH target for the application EC2 server (for example: ubuntu@1.2.3.4)'
        )
    }

    environment {
        DOCKER_IMAGE = 'jack1503/jack-devops-app'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'SonarScanner'
                    withSonarQubeEnv('sonarqube') {
                        withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                            sh "${scannerHome}/bin/sonar-scanner -Dsonar.token=${SONAR_TOKEN}"
                        }
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE:$BUILD_NUMBER .'
            }
        }

        stage('Login to DockerHub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-cred',
                    usernameVariable: 'USERNAME',
                    passwordVariable: 'PASSWORD'
                )]) {
                    sh 'echo $PASSWORD | docker login -u $USERNAME --password-stdin'
                }
            }
        }

        stage('Push Image') {
            steps {
                sh 'docker push $DOCKER_IMAGE:$BUILD_NUMBER'
            }
        }

        stage('Deploy to EC2') {
            steps {
                sshagent(credentials: ['app-ec2-ssh']) {
                    sh """
                        ssh -o StrictHostKeyChecking=no ${params.APP_SERVER} '
                            docker pull ${DOCKER_IMAGE}:${BUILD_NUMBER} &&
                            (docker stop jenkins-cicd-app || true) &&
                            (docker rm jenkins-cicd-app || true) &&
                            docker run -d --name jenkins-cicd-app -p 8081:80 ${DOCKER_IMAGE}:${BUILD_NUMBER}
                        '
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'CI/CD pipeline completed successfully. Application deployed to EC2.'
        }
        failure {
            echo 'Pipeline failed. Check the failed stage in the Jenkins console output.'
        }
    }
}
