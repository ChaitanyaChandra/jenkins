def call() {
    pipeline {
        agent { label 'work_station'}
        options {
            disableConcurrentBuilds()
            ansiColor('xterm')
        }
        //  triggers { pollSCM('* * * * *') }
        stages
                {
                    stage ('compile the code')
                            {
                                steps
                                        {
                                            sh 'echo compile code'
                                            git branch: 'master', url: 'https://github.com/ChaitanyaChandra/spec.git'
                                            dir('spec') {
                                            }
                                        }
                            }

                    stage ('check the code quality')
                            {
                                steps
                                        {
                                            script{
                                                common.SonarQube(SONAR_CREDS_USR, SONAR_CREDS_PSW)
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

                }
        post {
            always {
                cleanWs()
            }
        }
    }
}