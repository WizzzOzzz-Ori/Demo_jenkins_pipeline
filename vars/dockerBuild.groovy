def call(def tagName="${env.JOB_BASE_NAME}_${env.BUILD_NUMBER}"){
    sh "docker build . -t ${tagName}"
}