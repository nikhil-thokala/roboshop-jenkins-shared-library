def call() {
    pipeline {
        agent any

        stages {

            stage('Init') {
                steps  {
//                    sh 'terraform init -backend-config=env-dev/state.tfvars'
                    sh 'terraform init -backend-config=env-dev/state.tfvars'
                }
            }

            stage('Apply') {
                steps {
//                    sh 'terraform apply -auto-approve -var-file=env-dev/main.tfvars'
                    sh 'terraform destroy -auto-approve -var-file=env-dev/main.tfvars'
                }
            }
        }
    }
}