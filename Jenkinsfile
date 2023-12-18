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
                sh "mvn clean test"
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
         stage('Deploy to Nexus') {
                    steps {
                        nexusArtifactUploader artifacts: [
                                            [
                                                artifactId: 'timemanagement-app',
                                                classifier: '',
                                                file: 'target/timemanagement-app-0.0.1-SNAPSHOT.jar',
                                                type: 'jar'
                                            ]
                                        ],
                                         credentialsId: 'nexus3',
                                         groupId: 'com.timemanagemenet.timemanagementapp',
                                         nexusUrl: 'localhost:8081',
                                         nexusVersion: 'nexus3',
                                         protocol: 'http',
                                         repository: 'timemanagement-app-release',
                                         version: '1.0.0'
                                    }
                    }

   }
}
