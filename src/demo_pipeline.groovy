@Library("shared-lib") _

pipeline{
    agent{
        node {
            label "kubernetes"
        }
    }

    stages{
        stage("test stage"){
            steps{
                script{
                    test_sl()
                }
            }
        }
    }
}