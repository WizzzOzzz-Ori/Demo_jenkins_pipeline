@Library("shared-lib") _

pipeline{
    agent{
        node {
            label "kubernetes"
        }
    }

    stages{
        stage("git checkout"){
            steps{
                script{
                    gitCheckout("git@github.com:WizzzOzzz-Ori/python_app_for_demo.git", "main")
                }
            }
        }

        stage("git checkout"){
            steps{
                script{
                    sh "pip install -r requirements.txt"
                }
            }
        }
    }
}