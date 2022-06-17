def SonarQube() {
    sh "sonar-scanner -Dsonar.projectKey=nodejs -Dsonar.sources=src1 sonar.host.url=http:sonar-dev.chaitu.org//:9000"
}