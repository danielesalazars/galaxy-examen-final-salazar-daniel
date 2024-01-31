pipeline {
     agent any

     tools {
        maven 'maven-default'
     }

    stages {
        stage('Checking') {
            when {
                branch "features/*"
            }
            steps {
                script {
                     sh 'mvn verify'
                }
            }
            post {
                always {
                    junit(testResults: '**/surefire-reports/*.xml', allowEmptyResults: true)
                    junit(testResults: '**/failsafe-reports/*.xml', allowEmptyResults: true)
                    jacoco runAlways: true
                }
                //success {
                //    archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false, onlyIfSuccessful: true
                //}
            }
        }
        stage('OtherStage') {
            when {
                branch "features/*"
            }
            steps {
                script {
                    echo 'Stage 2'
                }
            }
        }

         stage('Building') {
            when {
                branch "master"
            }
            steps {
                script {
                     sh 'mvn verify'
                }
            }
            post {
                always {
                    junit(testResults: '**/surefire-reports/*.xml', allowEmptyResults: true)
                    junit(testResults: '**/failsafe-reports/*.xml', allowEmptyResults: true)
                    jacoco runAlways: true
                }
                success {
                    archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false, onlyIfSuccessful: true
                }
            }
         }

         stage('Other Building') {
                when {
                   not {
                       branch 'master'
                   }
               }
                steps {
                    script {
                        echo 'Stage 4'
                    }
              }
         }
         stage('Dockering') {
                 when {
                     branch "master"
                 }
                steps {
                    script {
                        copyArtifacts filter: 'target/*.jar',
                                      fingerprintArtifacts: true,
                                      projectName: '${JOB_NAME}',
                                      flatten: true,
                                      selector: specific('${BUILD_NUMBER}'),
                                      target: 'target';
                        sh 'docker --version'
                        sh 'docker-compose --version'
                        sh 'docker-compose build'
                    }
              }
         }

          stage('Tagging') {
               when {
                   branch "master"
               }
               environment {
                   DOCKER_CREDS = credentials('docker-credentials')
               }
                steps {
                     script {
                        sh 'docker login -u ${DOCKER_CREDS_USR} -p ${DOCKER_CREDS_PSW}'
                        sh 'docker tag msmicroservice ${DOCKER_CREDS_USR}/msmicroservice:$BUILD_NUMBER'
                        sh 'docker push ${DOCKER_CREDS_USR}/msmicroservice:$BUILD_NUMBER'
                     }
                }
          }
    }
}
