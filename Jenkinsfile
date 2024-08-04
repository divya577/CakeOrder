pipeline {
	agent any
	tools {
		maven 'maven 3.8.6'
		jdk 'OpenJDK11'
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
	}
post {
        always {
            emailext(
                to: 'divv10140@gmail.com',
                subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS',
                body: """\
$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS:

Check console output at $BUILD_URL to view the results.
""",
                attachLog: true
            )
        }
    }
}
	