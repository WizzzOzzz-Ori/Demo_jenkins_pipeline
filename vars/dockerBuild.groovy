def call(){
    validateDockerServiceIsRunning()
    def tempTag = ""
    withDockerRegistry(credentialsId: 'dockerhub-credentials', url: 'https://index.docker.io/v1/') {
        tempTag = "${DOCKER_REGISTRY_USERNAME}/${env.JOB_BASE_NAME}_${env.BUILD_NUMBER}"
    }
    sh "docker build -t ${tempTag}."
    return tempTag
}