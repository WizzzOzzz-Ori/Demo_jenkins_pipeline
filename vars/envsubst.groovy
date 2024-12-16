def call(String stringToModify, Map variablesToReplaceMap){
    // sh "apt update && apt-get install gettext-base -y"
    def listOfEV = variablesToReplaceMap.collect { key, value -> "${key}=${value}" }
    withEnv(listOfEV){
        return sh(script: "echo ${stringToModify} | envsubst", returnStdout: true)
    }
}