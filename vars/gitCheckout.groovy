def call(gitUrl, branchOrTag = "develop", folderName = "./"){
    def credentialsId = ""
    def gitRefs = ""
    repoAddedToHosts = []

    if (branchOrTag =~ /^(\d+)\/merge/) {
        gitRefs += "+refs/pull/${branchOrTag}:refs/remotes/origin/pr/${branchOrTag}"
    }
    else if (branchOrTag =~ /^v\d+\.\d+(\.\d+)?/) {
        // gitRefs += "+refs/tags/${branchOrTag}:refs/remotes/origin/tags/${branchOrTag}"
        gitRefs += "+refs/tags/${branchOrTag}:refs/tags/${branchOrTag}"
        branchOrTag = "refs/tags/${branchOrTag}"
    }
    else {
        gitRefs += "+refs/heads/${branchOrTag}:refs/remotes/origin/${branchOrTag}"
    }
    
    if (gitUrl.contains("bitbucket")) {
        credentialsId = "bitbucket-ssh-bitbucket"
    } 
    else if (gitUrl.contains("github")) {
        // TODO add github credentials for ssh and https clones
        credentialsId = "github-ssh-key"
    }
    else{
        consoleLog.error("Vendor ${gitUrl} is not supported")
        exit 1
    }

    withCredentials([sshUserPrivateKey(credentialsId: credentialsId, keyFileVariable: 'GIT_SSH_KEY')]){
        dir (folderName){
            def hostToKnow = gitUrl.split["@"][1].split[":"][0]
            sh """
                mkdir ~/.ssh/ && ssh-keyscan -t rsa ${hostToKnow} > ~/.ssh/known_hosts
                eval "\$(ssh-agent -s)"
                ssh-add ${GIT_SSH_KEY}
                git init

                git remote add origin ${gitUrl}

                git fetch origin "${gitRefs}" --quiet

                ${branchOrTag =~ /^(\d+)\/merge/ ? "git checkout FETCH_HEAD" : "git checkout ${branchOrTag}"}
                ssh-agent -k
            """
        }
    }
}