pipeline {
    agent any
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.33.10:8081"
        NEXUS_REPOSITORY = "maven-nexus-repo"
        NEXUS_CREDENTIAL_ID = "nexus-user-credentials"
    }
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

        stage("Maven Build") {
                    steps {
                        script {
                            sh "mvn package -DskipTests=true"
                        }
                    }
                }

        stage('MVN SONARQUBE'){
            steps{
                sh "mvn sonar:sonar -Dsonar.login=sqa_51a446c1c10f21425cebea77abed25fc0578094a"
            }
        }
        stage('Building image'){
                    steps{
                        sh "docker build -t ahmedjelassi/timemanagement-back ."
                    }
                }
        stage('Pushing image'){
                    steps{
                        sh "docker login -u ahmedjelassi -p Langue123"
                        sh "docker tag timemanagement-back jelassiahmed/timemanagement-back"
                        sh "docker push ahmedjelassi/timemanagement-back"
                    }
                }


        stage("Publish to Nexus Repository Manager") {
                    steps {
                        script {
                            pom = readMavenPom file: "pom.xml";
                            filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                            echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                            artifactPath = filesByGlob[0].path;
                            artifactExists = fileExists artifactPath;
                            if(artifactExists) {
                                echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                                nexusArtifactUploader(
                                    nexusVersion: NEXUS_VERSION,
                                    protocol: NEXUS_PROTOCOL,
                                    nexusUrl: NEXUS_URL,
                                    groupId: pom.groupId,
                                    version: pom.version,
                                    repository: NEXUS_REPOSITORY,
                                    credentialsId: NEXUS_CREDENTIAL_ID,
                                    artifacts: [
                                        [artifactId: pom.artifactId,
                                        classifier: '',
                                        file: artifactPath,
                                        type: pom.packaging],
                                        [artifactId: pom.artifactId,
                                        classifier: '',
                                        file: "pom.xml",
                                        type: "pom"]
                                    ]
                                );
                            } else {
                                error "*** File: ${artifactPath}, could not be found";
                            }
                        }
                    }
                }
   }
}
