@Library("shared-lib") _

pipeline{
    agent any
    // agent {
    //     node {
    //         label "master"
    //     }
    // }

    stages{
        stage("clean Workspace"){
            steps{
                script{
                    sh "rm -rf * .*"
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

        stage("pip install"){
            steps{
                script{
                    sh "pip install -r requirements.txt"
                }
            }
        }
    }
}