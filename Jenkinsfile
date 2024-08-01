pipeline {
	agent any
	tools {
		maven 'maven 3.8.6'
		jdk 'OpenJDK11'
		}
	environment {
        SONARQUBE_URL = 'http://localhost:9000'
        SONARQUBE_TOKEN = credentials('squ_023da3530e45a76129178cc41adfd7a8bd337580') 
    }
	stages{
		stage("clean"){
			steps{
				echo "Start Clean"
				bat "mvn clean"
		}
	}
	stage("test"){
		steps{
			echo "Start Test"
				bat "mvn test"
		}
	}
	stage("build"){
		steps{
			echo "Start build"
				bat "mvn install -DskipTests"
			}
		}
		stage("sonar") {
            steps {
                echo "Start SonarQube Analysis"
                bat "mvn sonar:sonar -Dsonar.host.url=${SONARQUBE_URL} -Dsonar.login=${SONARQUBE_TOKEN}"
            }
        }
	}
}
	