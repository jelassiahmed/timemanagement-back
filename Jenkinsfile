pipeline {
    agent any

    stages {
        stage('Pulling from git...'){
            steps{
               git branch : 'master',
                url : 'https://github.com/jelassiahmed/timemanagement-back'
            }
        }

        stage('Testing maven...'){
            steps {
                sh """mvn -versinon"""
            }
        }

        stage('Show system date........'){
            steps{
               script {
                    DATE_TAG = java.time.LocalDate.now()
                    DATETIME_TAG = java.time.LocalDateTime.now()
                }
                sh "echo ${DATETIME_TAG}"
            }
        }
   }
}