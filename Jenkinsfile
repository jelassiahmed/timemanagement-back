pipeline {
    agent any

    stages {
        stage('Pulling from git...'){
            steps{
               git branch: 'master',
                url: 'https://github.com/jelassiahmed/timemanagement-back'
            }
        }

        stage('Clean...'){
            steps{
                sh "mvn clean"
            }
        }

        stage('Compile...'){
            steps{
                sh "mvn compile"
            }
        }

   }
}
