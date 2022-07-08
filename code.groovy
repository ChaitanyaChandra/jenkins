pipelineJob('ansible') {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/ChaitanyaChandra/jenkins.git',)
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/main')
          }
        }
      }
      'scriptPath'('jenkins')
      'lightweight'(true)
    }
  }
}

folder('CI-Pipelines') {
  displayName('CI-Pipelines')
  description('CI-Pipelines')
}

pipelineJob('CI-Pipelines/nodejs') {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/ChaitanyaChandra/spec.git',)
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/tags/*')
          }
          'hudson.plugins.git.BranchSpec' {
            'name'('*/feature')
          }
        }
      }
      'scriptPath'('Jenkinsfile')
      'lightweight'(true)
    }

  }
}


pipelineJob('CI-Pipelines/frontend') {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/ChaitanyaChandra/frontend.git',)
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/tags/*')
          }
          'hudson.plugins.git.BranchSpec' {
            'name'('*/main')
          }
        }
      }
      'scriptPath'('Jenkinsfile')
      'lightweight'(true)
    }

  }
}


folder('mutable') {
  displayName('mutable')
  description('mutable')
}

pipelineJob('mutable/terraform') {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/ChaitanyaChandra/terraform.git',)
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/terraform-ansible-nodejs')
          }
        }
      }
      'scriptPath'('ec2-env/Jenkinsfile')
      'lightweight'(true)
    }

  }
}

pipelineJob('mutable/r53') {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/ChaitanyaChandra/terraform.git',)
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/main')
          }
        }
      }
      'scriptPath'('route53/Jenkinsfile')
      'lightweight'(true)
    }
  }
}

/////////////////////
folder('Immutable') {
  displayName('Immutable')
  description('Immutable')
}

pipelineJob('Immutable/All-Infra-Create') {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/chaitanyachandra/jenkins.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/main')
          }
        }
      }
      'scriptPath'('Jenkinsfile-Immutable-All-in-one-Infra-Create')
      'lightweight'(true)
    }
  }
}

pipelineJob('Immutable/All-Infra-Create') {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/chaitanyachandra/jenkins.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/main')
          }
        }
      }
      'scriptPath'('Jenkinsfile-Immutable-All-in-one-Infra-Destroy')
      'lightweight'(true)
    }
  }
}

pipelineJob('Immutable/app-deploy') {
  configure { flowdefinition ->
    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteConfig' {
            'url'('https://github.com/chaitanyachandra/jenkins.git')
          }
        }
        'branches' {
          'hudson.plugins.git.BranchSpec' {
            'name'('*/main')
          }
        }
      }
      'scriptPath'('Jenkinsfile-Immutable-App-Deploy')
      'lightweight'(true)
    }
  }
}