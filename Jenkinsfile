try {
    node {

        stage('Preparation') {
            cleanWs()
            checkout scm

            sh 'echo install Snyk'
            sh("curl -O -s -L https://static.snyk.io/cli/latest/snyk-linux")
            sh("curl -O -s -L https://static.snyk.io/cli/latest/snyk-linux.sha256")
            sh("shasum -c snyk-linux.sha256")
            sh("chmod +x snyk-linux && mv snyk-linux ./snyk")

            sh 'echo Snyk Login'
            withCredentials([string(credentialsId: 'SNYK_TOKEN', variable: 'SNYK_TOKEN')]) {
                sh './snyk auth ${SNYK_TOKEN}'
                }
        }

        stage('Snyk SCA') {
            tools {
                maven '3.8.7'
            }
            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {

                sh './snyk test --org='4ccff8cb-520b-4020-8159-5c24b69ca40f' --sarif-file-output=results-open-source.sarif'
            }
            recordIssues tool: sarif(name: 'Snyk Open Source', id: 'snyk-open-source', pattern: 'results-open-source.sarif')
        }

        stage('Snyk Code') {
            tools {
                maven '3.8.7'
            }
            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                sh './snyk code test --sarif-file-output=results-code.sarif'
            }
            recordIssues  tool: sarif(name: 'Snyk Code', id: 'snyk-code', pattern: 'results-code.sarif')
        }

/*
        stage('Snyk Container') {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {

                    credentialBinding = utils.getCredentialMap(map_credentials["redhat"].credentials)
                    withCredentials(credentialBinding){
                        sh ("set +x ; docker login -u ${REGISTRY_USERNAME} -p ${REGISTRY_PASSWORD} ${REGISTRY_REDHAT} ")
                        sh "./snyk container test ${REGISTRY_REDHAT}/juice-shop --file=Dockerfile --sarif-file-output=results-container.sarif"
                    }
                }
                recordIssues tool: sarif(name: 'Snyk Container', id: 'snyk-container', pattern: 'results-container.sarif')
            }

        stage('Snyk IaC') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh './snyk iac test --sarif-file-output=results-iac.sarif'
                }
                recordIssues tool: sarif(name: 'Snyk IaC', id: 'snyk-iac', pattern: 'results-iac.sarif')
            }
        }
*/

    }
} catch (Exception e) {
    node {
        throw e
    }
}
