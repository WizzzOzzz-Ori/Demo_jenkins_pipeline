@Library("shared-lib") _

pipeline{
    agent{
        node none
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