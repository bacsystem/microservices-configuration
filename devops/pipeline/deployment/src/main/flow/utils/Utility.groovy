package main.flow.utils
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

    static def readYaml(String file, def dsl){
        return dsl.readYaml(file)
    }



}
