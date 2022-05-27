pipelineJob('job-dsl-plugin') {
  definition {
    cpsScm {
      scm {
        git {
          remote {
            url('https://github.com/ChaitanyaChandra/jenkins.git')
          }
          branch('*/main')
        }
      }
      lightweight()
    }
  }
}