/**
 * method to get repository name
 * @param repository
 * @see <a href="https://javadoc.jenkins.io/plugin/git/hudson/plugins/git/GitSCM.html">GitSCM</a>
 * @see <a href="https://javadoc.jenkins.io/plugin/git/hudson/plugins/git/UserRemoteConfig.html">getUserRemoteConfigs</a>
 * @see <a href="https://www.jenkins.io/doc/pipeline/steps/workflow-basic-steps/#unstable-set-stage-result-to-unstable">Pipeline: Basic Steps</a>
 */

def repositoryName(Object scm) {
    echo "scm ${scm}"
    def name = scm.getUserRemoteConfigs()[0].getUrl().tokenize('/').last().split("\\.")[0]
    echo "name repository ${name}"
    return name
}

def prepareDeploy() {
    def REPO_NAME = repositoryName(scm)
    echo "REPO_NAME ${REPO_NAME}"
    def agentLabel1 = "principal"
    def solutionProject = "ejercito-solution"
    echo "Agent: ${agentLabel1}, Group: ${solutionProject}"
    return [agentLabel1, solutionProject]
}

def deployParams() {
    dir('deploy-config') {
        checkout([
                $class           : 'GitSCM',
                branches         : [[name: 'master']],
                userRemoteConfigs: [[
                                            url          : 'https://github.com/dbacilio88/microservices-configuration.git',
                                            credentialsId: 'github-jenkins-ssh'
                                    ]]
        ])
    }
    def REPO_NAME = repositoryName(scm)
    def agentLabel = "jenkins"
    def solutionProject = "jenkins"
    echo "Agent: ${agentLabel}, group ${solutionProject}"
    return [agentLabel, solutionProject]
}