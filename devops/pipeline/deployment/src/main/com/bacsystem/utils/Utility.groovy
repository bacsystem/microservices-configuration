package main.com.bacsystem.utils

import main.com.bacsystem.enums.Compiler
import main.com.bacsystem.enums.WorkFlow

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
        if (dsl.scm == null || !dsl.scm.getUserRemoteConfigs()) {
            error "[ERROR] The object SCM is invalid o is empty 'userRemoteConfigs'"
        }
        // Extract the URL from the first remote config
        def repoUrl = dsl.scm.getUserRemoteConfigs()[0]?.getUrl()
        if (!repoUrl) {
            error "[ERROR] Not found URL of repository in the configuration SCM"
        }
        // Tokenize and extract repository name
        def repoName = repoUrl.tokenize('/').last().split("\\.")[0]
        console("[INFO] Extracted repository name: ${repoName}", dsl)
        return repoName
    }

    static def readYaml(String file, def dsl) {
        return dsl.readYaml(file: file)
    }

    static String findCompiler(def dsl) {
        console("[INFO] find compiler to run the process", dsl)
        String detected = null

        Compiler.compilerFiles().each { key, value ->
            if (exist(key, dsl)) {
                detected = value.GetMgn()
                return
            }
        }

        if (!detected) {
            console("[WARN] No recognized build tool found in repository.", dsl)
        }

        console("[INFO] Compiler was found to execute the process", dsl)
        return detected
    }

    static def workflow(String wf) {
        return WorkFlow.get(wf)
    }

    static def findParams(def dsl) {
        console("[INFO] Searching for configuration files in the repository", dsl)

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

        // Read the YAML file
        if (!exist(configFilePath, dsl)) {
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

        def agent = appData.agent ?: 'default-agent'

        def solution = appData.group ?: 'default-group'
        console("[INFO] Configuration files found in the repository", dsl)
        return [agent, solution]
    }

    static boolean exist(String path, def dsl) {
        if (!path) return false
        return dsl?.fileExists(path) ?: false
    }

    static void console(String msg, def dsl) {
        dsl.echo msg
    }
}
