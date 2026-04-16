pipeline {
    agent any

    tools {
        // Must match the name in: Manage Jenkins -> Global Tool Configuration -> Maven
        maven 'Maven'
    }

    stages {
        stage('Clean Workspace') {
            steps {
                echo 'Cleaning up previous build files...'
                bat 'mvn clean'
            }
        }

        stage('Execute Unit Tests') {
            steps {
                echo 'Running Wallet Transaction JUnit tests...'
                // This runs the 3 tests we wrote in AppTest.java
                bat 'mvn test'
            }
        }

        stage('Compile & Package') {
            steps {
                echo 'Compiling code and generating JAR file...'
                // Skips tests here because they passed in the previous stage
                bat 'mvn package -DskipTests'
            }
        }

        stage('Archive Build Artifacts') {
            steps {
                echo 'Saving the generated JAR file in Jenkins...'
                // This makes the wallet-app.jar available to download from the Jenkins UI
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            // This pulls the JUnit XML reports into the Jenkins UI graph
            junit '**/target/surefire-reports/*.xml'
        }
        success {
            echo "Build Successful! Your Digital Wallet JAR is ready in the target folder."
        }
        failure {
            echo "Build Failed. Check the Console Output for Maven or JUnit errors."
        }
    }
}