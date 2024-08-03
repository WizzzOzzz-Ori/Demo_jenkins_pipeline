def call(){
    validateDockerServiceIsRunning()
    def tempTag = "${DOCKER_REGISTRY_USERNAME}/${env.JOB_BASE_NAME}_${env.BUILD_NUMBER}"
    sh "docker build -t ${tempTag}."
    return tempTag
}