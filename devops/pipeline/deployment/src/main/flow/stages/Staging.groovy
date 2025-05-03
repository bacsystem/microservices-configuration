package main.flow.stages

import main.flow.base.PipelineBase
import main.flow.global.Configuration
import main.flow.pipeline.ProcessPrepare

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

    private Configuration configuration
    private ProcessPrepare processPrepare

    Staging(def dsl) {
        super(dsl)
        dsl.echo "[INFO] load stage process"
       // this.configuration = new Configuration(dsl)
        //this.processPrepare = new ProcessPrepare(dsl, configuration)
    }

    def getCompiler() {
        dsl.echo "[INFO] Detecting build tool based on repository files..."
        def buildToolMap = [
                'pom.xml'     : 'maven',
                'build.gradle': 'gradle',
                'go.mod'      : 'golang',
                'package.json': 'nodejs',
                'setup.py'    : 'python'
        ]

        for (entry in buildToolMap) {
            if (dsl.fileExists(entry.key)) {
                dsl.echo "[INFO] Build tool detected: ${entry.value}"
                return entry.value
            }
        }
        dsl.echo "[WARN] No recognized build tool found in repository."
        return null
    }

    def build() {}

    def test() {}

    def image() {}

    def deploy() {}

}
