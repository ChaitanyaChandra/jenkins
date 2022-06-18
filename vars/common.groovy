def SonarQube(USER, PASSWORD, URL, PROJECT) {
    sh "sonar-scanner -Dsonar.projectKey=${PROJECT} -Dsonar.sources=. -Dsonar.host.url=http://${URL}:9000 -Dsonar.login=${USER} -Dsonar.password=${PASSWORD}"
    sh "sonar-quality-gate.sh ${USER} ${PASSWORD} ${URL} ${PROJECT}"
}