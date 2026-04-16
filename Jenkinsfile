pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        IMAGE_NAME = "digital-wallet-app"
        IMAGE_TAG = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Build & Test') {
            steps {
                echo 'Running Maven build and JUnit tests...'
                bat 'mvn clean package'
            }
        }

        stage('Dockerize') {
            steps {
                echo 'Building Docker image...'
                bat "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                bat "docker build -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('K8s Deployment') {
            steps {
                echo 'Deploying to Kubernetes from root folder...'
                // Updated path to root
                bat "kubectl apply -f deployment.yaml"
                
                echo 'Verifying Pod status...'
                bat "kubectl get pods"
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo "Successfully deployed! Access the wallet app at http://localhost:30005"
        }
    }
}