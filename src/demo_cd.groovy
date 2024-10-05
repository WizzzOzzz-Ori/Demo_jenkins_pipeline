@Library("shared-lib") _

def podYaml = libraryResource "pod-build-container.yaml"

def imageName = ""
def imageVersion = ""

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

        stage("Gather metadata"){
            steps{
                script{
                    def versionsYaml = readYaml file: "kubernetes/versions.yaml"
                    imageName = versionsYaml.image_name
                    imageVersion = versionsYaml.image_version
                    def podYaml = readFile "kubernetes/app_pod.yaml"
                    podYaml = envsubst(podYaml, [image_name: imageName, image_version: imageVersion])
                    writeFile file: "kubernetes/app_pod.yaml", text: podYaml
                }
            }
        }

        stage("Deploy pod"){
            steps{
                script{
                    sh "cat kubernetes/app_pod.yaml"
                }
            }
        }
    }
}