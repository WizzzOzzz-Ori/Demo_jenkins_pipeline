def call(){
    validateDockerServiceIsRunning()
    def tempTag = ""
    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]) {
        tempTag = "${DOCKERHUB_USERNAME}/${env.JOB_BASE_NAME.toLowerCase()}:${env.BUILD_NUMBER}"
    }
    withEnv(["DOCKER_BUILDKIT=1"]){
        sh "docker build --memory=4g --cpus=4 -t ${tempTag} ."
    }
    return tempTag
}