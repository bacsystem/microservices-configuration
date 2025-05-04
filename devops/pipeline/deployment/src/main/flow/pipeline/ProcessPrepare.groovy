package main.flow.pipeline

import main.flow.base.PipelineBase
import main.flow.factory.BuildFactory
import main.flow.factory.CompilerFactory
import main.flow.global.Configuration

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

    def init(String process, String solution, String compiler) {
        this._dsl.LAST_STEP = this._dsl.env.STAGE_NAME
        this.configuration.withConfig(STATIC_CONFIG).execute()
        Console("[INFO] Init process prepare compiler factory with [${compiler}]")
        def factory = CompilerFactory.getCompiler(compiler)
        Console("[INFO] Process prepare compiler factory with [${factory}]")
        factory.build(this._dsl)
        BuildFactory.commit(this._dsl)
        BuildFactory.gitflow(this._dsl, process)
        BuildFactory.image(this._dsl, process)
    }

}
