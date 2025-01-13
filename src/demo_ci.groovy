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
    
    options {
        skipDefaultCheckout true
    }

    stages{
        stage("git checkout"){
            steps{
                container('jnlp') {
                    script{
                        checkout([
                            $class: 'GitSCM',
                            branches: [[name: "main"]],
                            userRemoteConfigs: [[
                                url: "git@github.com:WizzzOzzz-Ori/python_app_for_demo.git",
                                credentialsId: "github-ssh-key"
                            ]]
                        ])
                    }
                }
            }
        }

        stage("build docker"){
            steps{
                script{
                    dockerTag = dockerBuild()
                }
            }
        }

        stage("Push Docker Image") {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'dockerhub-credentials', url: 'https://index.docker.io/v1/') {
                        sh "docker push ${dockerTag}"
                    }
                }
            }
        }
    }
}