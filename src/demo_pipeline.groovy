@Library("shared-lib") _

pipeline{
    // agent any
    agent {
        kubernetes {
            yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    jenkins/label: jenkins-wizzz-jenkins-agent
spec:
  containers:
  - name: python
    image: python:3.9-slim
    command:
    - cat
    tty: true
    volumeMounts:
    - name: workspace-volume
      mountPath: /home/jenkins/agent
  volumes:
  - name: workspace-volume
    emptyDir: {}
"""
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

        stage ("install requirements"){
            steps{
                script{
                    sh "pip install --upgrade pip"
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