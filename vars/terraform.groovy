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
            choice(name: 'ENVIRONMENT', choices: ['', 'dev', 'prod'], description: 'Pick Environment')
            choice(name: 'ACTION', choices: ['', 'apply', 'destroy'], description: 'Pick Terraform Action')
        }
        stages
                {
                    stage('Label Builds')
                            {
                                steps {
                                    script {
                                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${ENVIRONMENT}"
                                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${ACTION}"
                                    }
                                }
                            }

                    stage ('terraform apply')
                            {
                                steps
                                        {
                                            git branch: 'terraform-ansible-nodejs', credentialsId: 'Chaitanya', url: 'https://github.com/ChaitanyaChandra/terraform.git'
                                            dir('Terraform/ec2-env') {
                                                sh """
                                                pwd
                                               """
                                            }
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
//        post {
//            always {
//                cleanWs()
//            }
//        }
    }
}