def SonarQube(USER, PASSWORD, URL, PROJECT) {
    sh "sonar-scanner -Dsonar.projectKey=${PROJECT} -Dsonar.sources=. -Dsonar.host.url=http://${URL}:9000 -Dsonar.login=${USER} -Dsonar.password=${PASSWORD}"
    sh "sonar-quality-gate.sh ${USER} ${PASSWORD} ${URL} ${PROJECT}"
}

prepare_artifacts(){

}

def publish_artifacts(){
    println "publishing artifacts"
    sh '''
      npm install 
      zip -r nodejs-${gitTag}.zip node_modules index.js
    '''
}