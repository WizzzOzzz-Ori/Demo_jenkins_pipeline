def call(def tag="${env.JOB_BASE_NAME.toLowerCase()}_${env.BUILD_NUMBER}"){
    // validateDockerServiceIsRunning()
    
    sh "docker push ${tag}"
}