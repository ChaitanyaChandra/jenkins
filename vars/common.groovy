def SonarQube(USER, PASSWORD, URL, PROJECT) {
//    sh "sonar-scanner -Dsonar.projectKey=${PROJECT} -Dsonar.sources=. -Dsonar.host.url=http://${URL}:9000 -Dsonar.login=${USER} -Dsonar.password=${PASSWORD}"
//    sh "sonar-quality-gate.sh ${USER} ${PASSWORD} ${URL} ${PROJECT}"
}

def prepare_artifacts(){
    println "publishing artifacts"
    sh '''
      npm install 
      zip -r nodejs-${gitTag}.zip node_modules index.js views public package.json
    '''
}

def publish_artifacts(USER, PASSWORD, URL, PROJECT){
    sh "curl -v -u ${USER}:${PASSWORD} --upload-file ${PROJECT}-${gitTag}.zip http://${URL}:8081/repository/${PROJECT}/${PROJECT}-${gitTag}.zip"
}