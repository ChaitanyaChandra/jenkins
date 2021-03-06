def call() {
    pipeline {
        agent { label 'work_station'}
        options {
            disableConcurrentBuilds()
            ansiColor('xterm')
        }
        environment {
            CREDS = credentials('APP_CREDS')
        }
//        triggers {
//            pollSCM('H/2 * * * *')
//        }
        stages
                {
                    stage('Label Builds')
                            {
                                steps {
                                    script {
                                        env.gitTag = GIT_BRANCH.split('/').last()
                                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${gitTag}"
                                    }
                                }
                            }

                    stage ('compile the code')
                            {
                                steps
                                        {
                                            sh 'echo compile code'
                                            git branch: 'feature', url: 'https://github.com/ChaitanyaChandra/spec.git'
                                            dir('spec') {
                                            }
                                        }
                            }

                    stage ('check the code quality')
                            {
                                steps
                                        {
                                            script{
                                                common.SonarQube(CREDS_USR, CREDS_PSW, "sonar-dev.chaitu.org",  "nodejs")
                                            }
                                        }
                            }

                    stage ('Test cases')
                            {
                                steps
                                        {
                                            sh 'echo Test cases'
                                        }
                            }
                    stage ('Publish artifacts')
                            {
                                when {
                                    expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ]) }
                                }
                                steps
                                        {
                                            script{
                                                sh 'npm install'
                                                common.prepare_artifacts("spec")
                                                common.publish_artifacts(CREDS_USR, CREDS_PSW, "nexus-dev.chaitu.org", "nodejs", "spec")
                                            }
                                        }
                            }
                    stage('Publish AMI') {
                        when {
                            expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ]) }
                        }
                        steps {
                            script {
                                common.make_AMI(CREDS_USR, CREDS_PSW, "nodejs", "spec", "dev")
                            }
                        }
                    }
                }
        post {
            always {
                cleanWs()
            }
        }
    }
}