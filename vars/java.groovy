package vars

def call() {
  pipeline {
//    triggers {
//      pollSCM('H/2 * * * *')
//    }

    stages {

      stage('Compile the Code') {
        steps {
          sh 'mvn compile'
        }
      }

      stage('Check the Code Quality') {
        steps {
          script {
            common.sonarQube()
          }
        }
      }

      stage('Lint Checks') {
        steps {
          sh 'echo Test Cases'
        }
      }

      stage('Test Cases') {
        steps {
          sh 'echo Test Cases'
        }
      }
    }
  }
}
