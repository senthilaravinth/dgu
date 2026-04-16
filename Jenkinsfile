pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        // Descriptive name for your project assessment
        IMAGE_NAME = "digital-wallet-app"
        IMAGE_TAG = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Maven Build & Test') {
            steps {
                echo 'Running JUnit Tests and Packaging...'
                // This ensures Task 2 and 3 are verified
                bat 'mvn clean package'
            }
        }

        stage('Docker Image Build') {
            steps {
                echo 'Building Docker Image for Digital Wallet...'
                // Build the image using the local Dockerfile
                bat "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                bat "docker build -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Security & Verification') {
            steps {
                echo 'Verifying Docker Image exists locally...'
                bat "docker images | findstr ${IMAGE_NAME}"
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo "Successfully built Docker Image: ${IMAGE_NAME}:${IMAGE_TAG}"
        }
        failure {
            echo "Pipeline failed. Ensure Docker Desktop is running on your machine."
        }
    }
}