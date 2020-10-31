job('NodeJS Docker example') {
    scm {
        git('git://github.com/rustamsariyev/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Rustam Sariyev')
            node / gitConfigEmail('r.sariyev@outlook.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('sariyevrustam/docker-nodejs-demo01')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub-sariyevrustam')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
