def SonarQube() {
    sh "sonar-scanner -Dsonar.projectKey=nodejs -Dsonar.sources=. -Dsonar.host.url=http://sonar-dev.chaitu.org:9000 -Dsonar.login=admin -Dsonar.password=@123Chaitu"
}