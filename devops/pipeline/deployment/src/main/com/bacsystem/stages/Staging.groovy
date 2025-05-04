package main.com.bacsystem.stages

import main.com.bacsystem.base.PipelineBase
import main.com.bacsystem.utils.Utility

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

    //private ProcessPrepare processPrepare
    //private ProcessTest processTest

    Staging(def dsl) {
        super(dsl)
        dsl.echo "[INFO] load stage process"
        // this.processPrepare = new ProcessPrepare(dsl)
        //this.processTest = new ProcessTest(dsl)
    }

    def getParameters() {
        return Utility.params(this._dsl)
    }

    def getCompiler() {
        return Utility.compiler(this._dsl)
    }

    def getSetting(String process = "", String solution = "", String compiler) {
        Console("[INFO] Iniciando construcción: Proceso: ${process}, Solución: ${solution}, Compilador: ${compiler}")
        this.processPrepare.init(process, solution, compiler)
        Console("[INFO] Construcción completada para ${solution} utilizando ${compiler}")
    }

    def test(Map param = [:]) {
        Console("[INFO] Init test process: env ${this._dsl.env.BRANCH_NAME}")
        Console("[INFO] Init test process: env ${this._dsl.BRANCH_NAME}")
        Console("[INFO] Init test process: env ${param}")
        //this.processTest.init(param., "solution")
    }

    def image() {}

    def deploy() {}

}
