import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*
def jenkinsCredentials = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
        com.cloudbees.plugins.credentials.Credentials.class,
        Jenkins.instance,
        null,
        null
);

def Creds(ID){
    for (creds in jenkinsCredentials) {
        if(creds.id == $ID){
          //  println(creds.username)
          //  println(creds.password)
            if (ID == 'SONAR'){
                common.SonarQube(creds.username, creds.password)
            }
        }
    }
}