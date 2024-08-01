@Library("shared-lib") _

pipeline{
    agent{
        node {
            label none
        }
    }

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