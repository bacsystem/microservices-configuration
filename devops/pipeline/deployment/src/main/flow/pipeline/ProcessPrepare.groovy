package main.flow.pipeline

import main.flow.base.PipelineBase
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
        dsl.echo "[INFO] load prepare stage process"
    }

    def init(String process, String solution, String compiler) {
        Console("[INFO] Init process prepare compiler factory with [${compiler}]")
        def factory = CompilerFactory.getCompiler(compiler)
        Console("[INFO] Process prepare compiler factory with [${factory}]")
        factory.build(this._dsl)
    }
}
