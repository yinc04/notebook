pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'notebook-app'
        DOCKER_TAG = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Checking out code from SCM..."
                checkout scm
            }
        }

        stage('Build Frontend') {
            steps {
                echo "Building frontend Docker image..."
                dir('frontend') {
                    catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                        sh 'docker build -t ${DOCKER_IMAGE}-frontend:${DOCKER_TAG} .'
                    }
                }
            }
        }

        stage('Build Backend') {
            steps {
                echo "Building backend Docker image..."
                dir('backend') {
                    catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                        sh 'docker build -t ${DOCKER_IMAGE}-backend:${DOCKER_TAG} .'
                    }
                }
            }
        }

        stage('Push Images') {
            steps {
                withCredentials([string(credentialsId: 'docker-hub-password', variable: 'DOCKER_PASSWORD'),
                                 string(credentialsId: 'docker-hub-username', variable: 'DOCKER_USER')]) {
                    echo "Logging in to Docker Hub..."
                    catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                        sh 'docker login -u ${DOCKER_USER} -p ${DOCKER_PASSWORD}'
                    }
                    echo "Tagging frontend and backend images..."
                    sh 'docker tag ${DOCKER_IMAGE}-frontend:${DOCKER_TAG} ${DOCKER_USER}/${DOCKER_IMAGE}-frontend:${DOCKER_TAG}'
                    sh 'docker tag ${DOCKER_IMAGE}-backend:${DOCKER_TAG} ${DOCKER_USER}/${DOCKER_IMAGE}-backend:${DOCKER_TAG}'
                    echo "Pushing frontend and backend images to Docker Hub..."
                    catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                        sh 'docker push ${DOCKER_USER}/${DOCKER_IMAGE}-frontend:${DOCKER_TAG}'
                        sh 'docker push ${DOCKER_USER}/${DOCKER_IMAGE}-backend:${DOCKER_TAG}'
                    }
                    echo "Logging out from Docker Hub..."
                    sh 'docker logout'
                }
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying application using Docker Compose..."
                dir('frontend') {
                    catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                        sh 'docker-compose -f docker-compose.yml up -d'
                    }
                }
            }
        }

        stage('Cleanup') {
            steps {
                echo "Cleaning up local Docker images..."
                sh "docker rmi -f ${DOCKER_IMAGE}-frontend:${DOCKER_TAG} ${DOCKER_IMAGE}-backend:${DOCKER_TAG} ${DOCKER_USER}/${DOCKER_IMAGE}-frontend:${DOCKER_TAG} ${DOCKER_USER}/${DOCKER_IMAGE}-backend:${DOCKER_TAG}"
                sh 'docker system prune -af --volumes'
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed! Check the logs for more details."
        }
    }
}
