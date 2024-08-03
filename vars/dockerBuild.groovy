def call(){
    validateDockerServiceIsRunning()
    def tempTag = ""
    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]) {
        tempTag = "${DOCKERHUB_USERNAME}/${env.JOB_BASE_NAME}_${env.BUILD_NUMBER}"
    }
    sh "docker build -t ${tempTag} ."
    return tempTag
}