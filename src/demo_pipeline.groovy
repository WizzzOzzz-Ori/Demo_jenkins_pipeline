@Library("shared-lib") _

def podYaml = libraryResource "pod-build-container.yaml"

def dockerTag = ""

pipeline{
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

        stage("git checkout"){
            steps{
                script{
                    gitCheckout("git@github.com:WizzzOzzz-Ori/python_app_for_demo.git", "main")
                }
            }
        }

        stage("build docker"){
            steps{
                container('docker') {
                    script{
                        dockerTag = dockerBuild()
                    }
                }
            }
        }

        stage("Push Docker Image") {
            steps {
                container('docker') {
                    script {
                        withDockerRegistry(credentialsId: 'dockerhub-credentials', url: 'https://index.docker.io/v1/') {
                            withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]) {
                                sh "docker push ${DOCKERHUB_USERNAME}/${dockerTag}"
                            }
                        }
                    }
                }
            }
        }
    }
}