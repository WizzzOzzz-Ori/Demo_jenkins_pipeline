@Library("shared-lib") _

pipeline{
    agent{
        node none
    }

    stages{
        stage{
            steps{
                script{
                    echo "hi"
                }
            }
        }
    }
}