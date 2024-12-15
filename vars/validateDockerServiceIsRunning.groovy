def call(){
    def isDockerDaemonOn = false
    while (!isDockerDaemonOn){
        try{
            sh script: "docker info", retrunStdout: true
            isDockerDaemonOn = true
        }
        catch (Exception ex){
            echo "daemon not ready yet"
            sleep 5
        }
    }                   
}