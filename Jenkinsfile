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

        stage('MVN SONARQUBE'){
            steps{
                sh "mvn sonar:sonar -Dsonar.login=sqa_51a446c1c10f21425cebea77abed25fc0578094a"
            }
        }

   }
}
