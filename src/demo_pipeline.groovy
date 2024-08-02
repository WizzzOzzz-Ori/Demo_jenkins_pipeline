@Library("shared-lib") _

def podYaml = libraryResource "pod-build-container.yaml"

pipeline{
    // agent any
    agent {
        kubernetes {
            yaml podYaml
        }
    }
    

    stages{
        stage("clean Workspace"){
            steps{
                script{
                    sh "rm -rf * .git"
                }
            }
        }

        // stage ("install requirements"){
        //     steps{
        //         script{
        //             sh "pip install --upgrade pip"
        //         }
        //     }
        // }

        stage("git checkout"){
            steps{
                script{
                    gitCheckout("git@github.com:WizzzOzzz-Ori/python_app_for_demo.git", "main")
                }
            }
        }

        stage("pip install"){
            steps{
                container('python') {
                    script{
                        installRequirements()
                    }
                }
            }
        }

        stage("build docker"){
            steps{
                script{
                    dockerBuild()
                }
            }
        }
    }
}