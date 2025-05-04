package main.com.bacsystem.stages

import main.com.bacsystem.base.PipelineBase
import main.com.bacsystem.pipeline.ProcessPrepare

import static main.com.bacsystem.utils.Utility.console
import static main.com.bacsystem.utils.Utility.findCompiler
import static main.com.bacsystem.utils.Utility.findParams

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
    //private ProcessTest processTest

    Staging(def dsl) {
        super(dsl)
        dsl.echo "[INFO] Load stage process"
        this.processPrepare = new ProcessPrepare(dsl)
        //this.processTest = new ProcessTest(dsl)
    }

    def getParameters() {
        return findParams(this._dsl)
    }

    void getFlow(String flowType) {
        console("[INFO] Working with jenkins flow -> [${flowType}]", this._dsl)
        if (!flowType) {

            echo "[INFO] Using flow type: ${flowType} and solution project: ${solutionProject}"
        } else {
            echo "[WARN] Not found flow type: ${flowType} and solution project: ${solutionProject}"
        }
    }

    def getCompiler() {
        return findCompiler(this._dsl)
    }
/*
    def getSetting(String process = "", String solution = "", String compiler) {
        console("[INFO] Iniciando construcción: Proceso: ${process}, Solución: ${solution}, Compilador: ${compiler}", this._dsl)
        this.processPrepare.init(process, solution, compiler)
        console("[INFO] Construcción completada para ${solution} utilizando ${compiler}", this._dsl)
    }

 */
/*
    def test(Map param = [:]) {
        console("[INFO] Init test process: env ${this._dsl.env.BRANCH_NAME}", this._dsl)
        console("[INFO] Init test process: env ${this._dsl.BRANCH_NAME}", this._dsl)
        console("[INFO] Init test process: env ${param}", this._dsl)
        //this.processTest.init(param., "solution")
    }

 */

    def image() {}

    def deploy() {}

}
