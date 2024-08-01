@Library("shared-lib") _

pipeline{
    agent any

    stages{
        stage("test stage"){
            steps{
                script{
                    echo "hi"
                }
            }
        }
    }
}