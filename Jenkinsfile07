pipeline {
    agent any
    environment {
        DOCKER_CREDS = credentials('docker-credentials')
        }
        stages {
            stage('Build') {
                agent {
                    docker { image 'maven:3.6.3-openjdk-11-slim' }
                }
                    steps {
                        sh 'mvn -B verify'
                        archiveArtifacts artifacts: 'target/*.jar', fingerprint: true, onlyIfSuccessful: true
                    }
            }

            stage('Build Image') {
                steps {
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
            stage('Publish Image') {
                steps {
                    script {
                        sh 'docker login -u ${DOCKER_CREDS_USR} -p ${DOCKER_CREDS_PSW}'
                        sh 'docker tag msmicroservice ${DOCKER_CREDS_USR}/msmicroservice:$BUILD_NUMBER'
                        sh 'docker push ${DOCKER_CREDS_USR}/msmicroservice:$BUILD_NUMBER'
                        sh 'docker logout'
                    }
                }
            }
        }
}
