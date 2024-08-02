def call(def tagName="${env.BUILD_BASE_NAME}_${env.BUILD_NUMBER}"){
    sh "docker build . -t ${tagName}"
}