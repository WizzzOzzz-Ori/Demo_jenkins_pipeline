@Library("shared-lib") _

pipeline{
    agent{
        node {
            label "master"
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