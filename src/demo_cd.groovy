@Library("shared-lib") _

def podYaml = libraryResource "pod-build-container.yaml"

def dockerTag = ""

pipeline{
    agent {
        kubernetes {
            yaml podYaml
            defaultContainer 'docker'
        }
    }
    

    stages{
        stage("git checkout"){
            steps{
                container('jnlp') {
                    script{
                        gitCheckout("git@github.com:WizzzOzzz-Ori/python_app_for_demo.git", "main")
                    }
                }
            }
        }

        stage("Deploy pod"){
            steps{
                script{
                    dockerTag = dockerBuild()
                }
            }
        }
    }
}