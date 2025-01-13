def call(String stringToModify, Map variablesToReplaceMap){
    sh "apk update && apk add gettext -y"
    def listOfEV = variablesToReplaceMap.collect { key, value -> "${key}=${value}" }
    withEnv(listOfEV){
        return sh(script: "echo ${stringToModify} | envsubst", returnStdout: true)
    }
}