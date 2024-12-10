def call(def tagName="${env.JOB_BASE_NAME.toLowerCase()}_${env.BUILD_NUMBER}"){
    // validateDockerServiceIsRunning()
    def imageSha = dockerBuild()
    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]) {
        tagName = "${DOCKERHUB_USERNAME}/${tagName}"
    }
    sh "docker image tag ${imageSha} ${tagName}"
} 