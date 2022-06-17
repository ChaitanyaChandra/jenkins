def SonarQube(USER, PASSWORD) {
    sh "sonar-scanner -Dsonar.projectKey=node -Dsonar.sources=. -Dsonar.host.url=http://sonar-dev.chaitu.org:9000 -Dsonar.login=${USER} -Dsonar.password=${PASSWORD}"
    sh "sonar-quality-gate.sh ${USER} ${PASSWORD} sonar-dev.chaitu.org nodejs"
}