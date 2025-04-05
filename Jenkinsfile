pipeline {
    agent any
    environment {
        PYTHON_VERSION = "3.13"
        PATH = "/Users/user/Library/Python/3.9/bin:$PATH" // Thêm đường dẫn pytest
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/anodtest/auto-bo-newwallet.git',
                    credentialsId: '421c09bc-dfa7-44b0-9d77-f7c7a0ac05d7',
                    branch: 'main'
            }
        }
        stage('Setup Environment') {
            steps {
                sh 'pip3 install pytest grpcio grpcio-tools protobuf'
            }
        }
        stage('Generate Protobuf Files') {
            steps {
                sh 'python3 -m grpc_tools.protoc --python_out=. --grpc_python_out=. -I src/test/java/com/anodtester/newwallet src/test/java/com/anodtester/newwallet/transaction.proto'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'pytest test_wallet.py -v -s'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'test_output.txt', allowEmptyArchive: true
            sh 'echo "Test results archived"'
        }
        success {
            echo 'Tests passed successfully!'
        }
        failure {
            echo 'Tests failed. Check the logs for details.'
        }
    }
}
