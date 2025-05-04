package main.com.bacsystem.pipeline

import main.com.bacsystem.base.PipelineBase

import static main.com.bacsystem.utils.Utility.console
import static main.com.bacsystem.utils.Utility.findCompiler

/**
 * <b>Prepare</b>
 * <p>
 * This class specifies the requirements for the {@link ProcessPrepare} component,
 * developed in accordance with the development standards established by christian.
 * Collaboration is encouraged for the enhancement and expansion of the deployment.
 * </p>
 * <p>
 * <b>Description:</b>
 * </p>Here!</p>
 *
 * @author christian
 * @author dbacilio88@outlook.es
 * @since 2/05/2025
 */


class ProcessPrepare extends PipelineBase {
    @Serial
    static final long serialVersionUID = 1

    private static final String STATIC_CONFIG = "static_environment.env"
    //private Configuration configuration

    ProcessPrepare(def dsl) {
        super(dsl)
        dsl.echo "[INFO] load prepare process"
        //  this.configuration = new Configuration(dsl)
    }

    def init(String process, String solution) {
        console("[INFO] Starting jenkins preparation process")
        // this._dsl.LAST_STEP = this._dsl.env.STAGE_NAME
        // this.configuration.withConfig(STATIC_CONFIG).execute()
        String compiler = findCompiler(this._dsl)
        console("[INFO] Init process prepare compiler factory with [${compiler}]", this._dsl)
        // def factory = CompilerFactory.getCompiler(compiler)
        // console("[INFO] Process prepare compiler factory with [${factory}]", this._dsl)
        //factory.build(this._dsl)
        //BuildFactory.commit(this._dsl)
        // BuildFactory.gitflow(this._dsl, process)
        // BuildFactory.image(this._dsl, process)
        console("[INFO] Process started to run jenkins")
    }

}
