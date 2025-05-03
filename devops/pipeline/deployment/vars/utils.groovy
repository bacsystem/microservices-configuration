/**
 * method to get repository name
 * @param repository
 * @see <a href="https://javadoc.jenkins.io/plugin/git/hudson/plugins/git/GitSCM.html">GitSCM</a>
 * @see <a href="https://javadoc.jenkins.io/plugin/git/hudson/plugins/git/UserRemoteConfig.html">getUserRemoteConfigs</a>
 * @see <a href="https://www.jenkins.io/doc/pipeline/steps/workflow-basic-steps/#unstable-set-stage-result-to-unstable">Pipeline: Basic Steps</a>
 */

def repositoryName(Object scm) {
    echo "[INFO] SCM object: ${scm}"
    // Ensure the SCM object is not null and has the expected structure
    if (scm == null || !scm.getUserRemoteConfigs()) {
        error "[ERROR] SCM object is invalid or missing userRemoteConfigs"
    }
    // Extract the URL from the first remote config
    def repoUrl = scm.getUserRemoteConfigs()[0]?.getUrl()

    if (!repoUrl) {
        error "[ERROR] Repository URL not found in SCM configuration"
    }

    // Tokenize and extract repository name
    def repoName = repoUrl.tokenize('/').last().split("\\.")[0]

    echo "[INFO] Extracted repository name: ${repoName}"

    return repoName
}

def deployParams() {
    def deployDir = 'deploy-config'
    def configRepo = 'https://github.com/dbacilio88/microservices-configuration.git'
    def credentialsId = 'github-jenkins-ssh'
    def configFilePath = "${deployDir}/deploy/helm/environment/config/deploy-config.yml"

    // Checkout the configuration repository
    dir(deployDir) {
        checkout([
                $class           : 'GitSCM',
                branches         : [[name: 'master']],
                userRemoteConfigs: [[
                                            url          : configRepo,
                                            credentialsId: credentialsId
                                    ]]
        ])
    }
    // Extract the current repository name
    String repoName = repositoryName(scm)
    echo "[INFO] Detected repository name: ${repoName}"

    // Read the YAML file
    if (!fileExists(configFilePath)) {
        error "[ERROR] Configuration file not found: ${configFilePath}"
    }

    def content = readYaml(file: configFilePath)

    if (content == null || !content.containsKey('applications')) {
        error "[ERROR] Invalid YAML structure or 'applications' key not found"
    }

    def appData = content.applications[repoName]

    if (appData == null) {
        error "[ERROR] No configuration found for application '${repoName}' in the YAML"
    }

    def agentLabel = appData.agent ?: 'default-agent'

    def solutionProject = appData.group ?: 'default-group'

    echo "[INFO] Parameters obtained: agent='${agentLabel}', group='${solutionProject}'"

    return [agentLabel, solutionProject]
}


def getBuildToolFromRepo() {
    echo "[INFO] Detecting build tool based on repository files..."

    if (fileExists('pom.xml')) {
        return 'maven'
    } else if (fileExists('build.gradle')) {
        return 'gradle'
    } else if (fileExists('go.mod')) {
        return 'golang'
    } else if (fileExists('package.json')) {
        return 'nodejs'
    } else if (fileExists('setup.py')) {
        return 'python'
    } else {
        // error "[ERROR] No recognized build tool found in repository."
        return null
    }
}

def executeBuildTools(String tools) {
    echo "[INFO] Selected build tool: ${tools}"
    def buildTools = [
            'maven' : 'mvn clean install',
            'gradle': './gradlew build',
            'golang': 'go build',
            'nodejs': 'npm install && npm run build',
            'python': 'python setup.py install'
    ]
    echo "[INFO] Build constains key tool: ${buildTools.containsKey(tools)}"
    if (buildTools.containsKey(tools)) {
        echo "[INFO] Running ${tools}..."
        sh buildTools[tools]
    } else {
        error "[ERROR] Unsupported build tool: ${tools}"
    }
}
