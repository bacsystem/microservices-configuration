package main.flow.stages

import main.flow.base.PipelineBase
import main.flow.pipeline.ProcessPrepare
import main.flow.utils.Utility

/**
 * <b>Staging</b>
 * <p>
 * This class specifies the requirements for the {@link Staging} component,
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


class Staging extends PipelineBase {

    @Serial
    static final long serialVersionUID = 1

    private ProcessPrepare processPrepare


    Staging(def dsl) {
        super(dsl)
        dsl.echo "[INFO] load stage process"
        this.processPrepare = new ProcessPrepare(dsl)
    }

    def deployParams() {
        def deployDir = 'deploy-config'
        def configRepo = 'https://github.com/dbacilio88/microservices-configuration.git'
        def credentialsId = 'github-jenkins-ssh'
        def configFilePath = "${deployDir}/deploy/helm/environment/config/deploy-config.yml"
        this._dsl.dir(deployDir) {
            this._dsl.checkout([
                    $class           : 'GitSCM',
                    branches         : [[name: 'master']],
                    userRemoteConfigs: [[
                                                url          : configRepo,
                                                credentialsId: credentialsId
                                        ]]
            ])
        }

        String repoName = Utility.repository(this._dsl)
        Console("[INFO] Detected repository name: ${repoName}")

        // Read the YAML file
        if (!ExistFile(configFilePath)) {
            error "[ERROR] Configuration file not found: ${configFilePath}"
        }

        def content = this._dsl.readYaml(file: configFilePath)

        if (content == null || !content.containsKey('applications')) {
            error "[ERROR] Invalid YAML structure or 'applications' key not found"
        }

        def appData = content.applications[repoName]

        if (appData == null) {
            error "[ERROR] No configuration found for application '${repoName}' in the YAML"
        }

        def agentLabel = appData.agent ?: 'default-agent'

        def solutionProject = appData.group ?: 'default-group'

        Console("[INFO] Parameters obtained: agent='${agentLabel}', group='${solutionProject}'")

        return [agentLabel, solutionProject]
    }

    def getCompiler() {

        Console("[INFO] Detecting build tool based on repository files...")

        def buildToolMap = [
                'pom.xml'     : 'maven',
                'build.gradle': 'gradle',
                'go.mod'      : 'golang',
                'package.json': 'nodejs',
                'setup.py'    : 'python'
        ]

        def detectedCompiler = null

        buildToolMap.each { key, value ->
            if (ExistFile(key)) {
                Console("[INFO] Build tool detected: ${value}")
                detectedCompiler = value
                return
            }

        }

        if (detectedCompiler == null) {
            Console("[WARN] No recognized build tool found in repository.")
        }
        return detectedCompiler
    }

    def getBuild(String process = "", String solution = "", String compiler) {
        Console("[INFO] Iniciando construcción: Proceso: ${process}, Solución: ${solution}, Compilador: ${compiler}")
        Console("[INFO] Construcción completada para ${solution} utilizando ${compiler}")
        this.processPrepare.init(process, solution, compiler)
    }

    def test() {}

    def image() {}

    def deploy() {}

}
