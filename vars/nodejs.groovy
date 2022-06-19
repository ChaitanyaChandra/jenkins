def call() {
    pipeline {
        agent { label 'work_station'}
        options {
            disableConcurrentBuilds()
            ansiColor('xterm')
            addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${gitTag}"
        }
        environment {
            SONAR_CREDS = credentials('SONAR')

        }
//        triggers {
//            pollSCM('H/2 * * * *')
//        }
        stages
                {
                    stage ('compile the code')
                            {
                                steps
                                        {
                                            sh 'echo compile code'
                                            git branch: 'feature', url: 'https://github.com/ChaitanyaChandra/spec.git'
//                                            Refspec: '+refs/tags/*:refs/remotes/origin/tags/*'
//                                            Branch: 'specifier **/tags/**'
                                            dir('spec') {
                                            }
                                        }
                            }

                    stage ('check the code quality')
                            {
                                steps
                                        {
                                            script{
                                                common.SonarQube(SONAR_CREDS_USR, SONAR_CREDS_PSW, "sonar-dev.chaitu.org",  "nodejs")
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
                                            sh 'env'
                                            script{
                                                common.publish_artifacts()
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