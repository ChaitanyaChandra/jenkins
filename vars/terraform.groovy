def call() {
    pipeline {
        agent { label 'work_station'}
        options {
            disableConcurrentBuilds()
            ansiColor('xterm')
        }
//        triggers {
//            pollSCM('H/2 * * * *')
//        }
        parameters {
            choice(name: 'ENV', choices: ['dev', 'prod'], description: 'choose dev or pod')
            choice(name: 'ACTION', choices: ['apply', 'destroy'], description: 'apply or destroy')
        }
        stages
                {
                    stage('Label Builds')
                            {
                                steps {
                                    script {
                                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${ENV}"
                                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${ACTION}"
                                    }
                                }
                            }

                    stage ('terraform init')
                            {
                                steps
                                        {
                                            git branch: 'terraform-ansible-nodejs', url: 'https://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@github.com/ChaitanyaChandra/terraform.git'
                                            dir('Terraform/ec2-spot') {
                                            }
                                            sh "terraform init ---backend-config=./env/${ENV}.tfvars"
                                            sh "terraform ${ACTION} --auto-approve"
                                        }
                            }
                    stage ('terraform apply')
                            {
                                steps
                                        {
                                            sh "terraform ${ACTION} --auto-approve"
                                        }
                            }
//                    stage ('Publish artifacts')
//                            {
//                                steps
//                                        {
//                                            script{
//                                                common.prepare_artifacts()
//                                                common.publish_artifacts(CREDS_USR, CREDS_PSW, "nexus-dev.chaitu.org", "nodejs")
//                                            }
//                                        }
//                            }
                }
        post {
            always {
                cleanWs()
            }
        }
    }
}