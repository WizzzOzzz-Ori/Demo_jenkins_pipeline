def call(){
    validateDockerServiceIsRunning()
    def tempTag = ""
    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]) {
        tempTag = "${DOCKERHUB_USERNAME}/${env.JOB_BASE_NAME.toLowerCase()}:${env.BUILD_NUMBER}"
    }
    withEnv(["DOCKER_BUILDKIT=0"]){
        sh "docker build -t ${tempTag} ."
    }
    return tempTag
}