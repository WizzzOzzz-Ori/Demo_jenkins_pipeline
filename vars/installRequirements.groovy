def call(def requirementsPath="requirements.txt"){
    sh "pip install -r ${requirementsPath}"
}