def call(){
    def isDockerDaemonOn = false
    while (!isDockerDaemonOn){
        try{
            def var = sh script: "docker info", retrunStderr: true
            isDockerDaemonOn = true
        }
        catch (Exception ex){
            echo "daemon not ready yet"
            sleep 5
        }
    }                   
}