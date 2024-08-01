pipeline {
    agent any
    tools {
        maven 'maven 3.8.6'
        jdk 'OpenJDK11'
    }
    environment {
        SONARQUBE_URL = 'http://localhost:9000'
        SONARQUBE_TOKEN = credentials('squ_023da3530e45a76129178cc41adfd7a8bd337580') // Ensure you have set up credentials in Jenkins
    }
    stages {
        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }
        stage('Clean') {
            steps {
                echo "Start Clean"
                bat "mvn clean"
            }
        }
        stage('Test') {
            steps {
                echo "Start Test"
                bat "mvn test"
            }
        }
        stage('Build & SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('My SonarQube Server') {
                        bat 'mvn clean package sonar:sonar -Dsonar.projectKey=cake'
                    }
                }
            }
        }
        stage('Quality Gate') {
            steps {
                script {
                    timeout(time: 1, unit: 'HOURS') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }
    }
    post {
        always {
            echo 'Cleaning up...'
        }
    }
}

	