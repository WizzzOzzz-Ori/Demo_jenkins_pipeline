def call(def tagName="${env.JOB_BASE_NAME.toLowerCase()}_${env.BUILD_NUMBER}"){
    validateDockerServiceIsRunning()
    def imageSha = dockerBuild()
    withDockerRegistry(credentialsId: 'dockerhub-credentials', url: 'https://index.docker.io/v1/') {
        tagName = "${DOCKER_REGISTRY_USERNAME}/${tagName}"
    }
    sh "docker image tag ${imageSha} ${tagName}"
} 