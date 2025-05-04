/**
 * method to get repository name
 * @param repository
 * @see <a href="https://javadoc.jenkins.io/plugin/git/hudson/plugins/git/GitSCM.html">GitSCM</a>
 * @see <a href="https://javadoc.jenkins.io/plugin/git/hudson/plugins/git/UserRemoteConfig.html">getUserRemoteConfigs</a>
 * @see <a href="https://www.jenkins.io/doc/pipeline/steps/workflow-basic-steps/#unstable-set-stage-result-to-unstable">Pipeline: Basic Steps</a>
 */


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

