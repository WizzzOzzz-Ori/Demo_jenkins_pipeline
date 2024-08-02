def call(def tagName="${env.JOB_BASE_NAME.toLowerCase()}_${env.BUILD_NUMBER}"){
    sh """
    while (! docker info > /dev/null 2>&1); do
        echo "Waiting for Docker daemon to start..."
        sleep 1
    done
    """
    sh "docker build . -t ${tagName}"
}