package main.com.bacsystem.utils

import main.com.bacsystem.constants.IConstants

/**
 * <b>Utility</b>
 * <p>
 * This class specifies the requirements for the {@link Utility} component,
 * developed in accordance with the development standards established by christian.
 * Collaboration is encouraged for the enhancement and expansion of the deployment.
 * </p>
 * <p>
 * <b>Description:</b>
 * </p>Here!</p>
 *
 * @author christian
 * @author dbacilio88@outlook.es
 * @since 3/05/2025
 */

/**
 * method to get repository name
 * @param repository
 * @see <a href="https://javadoc.jenkins.io/plugin/git/hudson/plugins/git/GitSCM.html">GitSCM</a>
 * @see <a href="https://javadoc.jenkins.io/plugin/git/hudson/plugins/git/UserRemoteConfig.html">getUserRemoteConfigs</a>
 * @see <a href="https://www.jenkins.io/doc/pipeline/steps/workflow-basic-steps/#unstable-set-stage-result-to-unstable">Pipeline: Basic Steps</a>
 */


class Utility {

    static String repository(def dsl) {
        dsl.echo "[INFO] SCM object: ${dsl.scm}"
        if (dsl.scm == null || !dsl.scm.getUserRemoteConfigs()) {
            error "[ERROR] The object SCM is invalid o is empty 'userRemoteConfigs'"
        }
        // Extract the URL from the first remote config
        def repoUrl = dsl.scm.getUserRemoteConfigs()[0]?.getUrl()
        if (!repoUrl) {
            error "[ERROR] No se encontró la URL del repositorio en la configuración SCM"
        }
        // Tokenize and extract repository name
        def repoName = repoUrl.tokenize('/').last().split("\\.")[0]
        dsl.echo "[INFO] Extracted repository name: ${repoName}"
        return repoName
    }

    static def readYaml(String file, def dsl) {
        return dsl.readYaml(file: file)
    }

    static def compiler(def dsl) {
        dsl.echo "[INFO] Detecting build tool based on repository files..."
        def tools = [
                'pom.xml'     : 'maven',
                'build.gradle': 'gradle',
                'go.mod'      : 'golang',
                'package.json': 'nodejs',
                'setup.py'    : 'python'
        ]
        def detected = null
        tools.each { key, value ->
            if (existObject(key, dsl)) {
                dsl.echo "[INFO] Build tool detected: ${value}"
                detected = value
                return
            }
        }
        if (detected == null) {
            dsl.echo "[WARN] No recognized build tool found in repository."
        }
        return detected
    }

    static def flowType(String process) {
        return IConstants.PipelineProcess.get(process)
    }

    static def params(def dsl) {
        dsl.echo "[INFO] Get parameters for deployment."
        def deployDir = 'deploy-config'
        def configRepo = 'https://github.com/dbacilio88/microservices-configuration.git'
        def credentialsId = 'github-jenkins-ssh'
        def configFilePath = "${deployDir}/deploy/helm/environment/config/deploy-config.yml"
        dsl.dir(deployDir) {
            dsl.checkout([
                    $class           : 'GitSCM',
                    branches         : [[name: 'master']],
                    userRemoteConfigs: [[
                                                url          : configRepo,
                                                credentialsId: credentialsId
                                        ]]
            ])
        }

        String repoName = repository(dsl)
        dsl.echo "[INFO] Detected repository name: ${repoName}"

        // Read the YAML file
        if (!existObject(configFilePath, dsl)) {
            error "[ERROR] Configuration file not found: ${configFilePath}"
        }

        def content = readYaml(configFilePath, dsl)

        if (content == null || !content.containsKey('applications')) {
            error "[ERROR] Invalid YAML structure or 'applications' key not found"
        }

        def appData = content.applications[repoName]

        if (appData == null) {
            error "[ERROR] No configuration found for application '${repoName}' in the YAML"
        }

        def agentLabel = appData.agent ?: 'default-agent'

        def solutionProject = appData.group ?: 'default-group'

        dsl.echo "[INFO] Parameters obtained: agent='${agentLabel}', group='${solutionProject}'"

        return [agentLabel, solutionProject]
    }

    static boolean existObject(String path, def dsl) {
        if (!path) return false
        return dsl?.fileExists(path) ?: false
    }


}
