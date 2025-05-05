package main.com.bacsystem.pipeline

import main.com.bacsystem.base.PipelineBase
import main.com.bacsystem.factory.compiler.CompilerFactory
import main.com.bacsystem.factory.flow.FlowFactory
import main.com.bacsystem.global.Configuration

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
    private Configuration configuration

    ProcessPrepare(def dsl) {
        super(dsl)
        dsl.echo "[INFO] load prepare process"
        this.configuration = new Configuration(dsl)
    }

    def init(String process, String solution) {
        console("[INFO] Starting jenkins preparation process", this._dsl)
        this._dsl.env.LAST_STEP = this._dsl.env.STAGE_NAME
        this.configuration.withConfig(STATIC_CONFIG).execute()
        String compiler = findCompiler(this._dsl)
        console("[INFO] Init process prepare compiler factory with [${compiler}]", this._dsl)
        def compilerFactory = CompilerFactory.getCompiler(compiler, this._dsl)
        console("[INFO] Process prepare compiler factory with [${compilerFactory}]", this._dsl)
        compilerFactory.compiler(this._dsl)
        def flowFactory = FlowFactory.getFlowFactory(process, this._dsl)
        console("[INFO] Process flow factory with [${flowFactory}]", this._dsl)
        flowFactory.flow(process, this._dsl)

        //factory.build(this._dsl)
        //BuildFactory.commit(this._dsl)
        // BuildFactory.gitflow(this._dsl, process)
        // BuildFactory.image(this._dsl, process)
        console("[INFO] Process started to run jenkins", this._dsl)
    }

}
