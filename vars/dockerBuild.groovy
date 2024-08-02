def call(def tagName="${env.JOB_BASE_NAME.toLowerCase()}_${env.BUILD_NUMBER}"){
    sh "docker build . -t ${tagName}"
}