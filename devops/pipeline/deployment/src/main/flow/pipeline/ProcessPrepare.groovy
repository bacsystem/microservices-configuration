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

    ProcessPrepare(def dsl, Configuration configuration) {
        super(dsl)
        dsl.echo "[INFO] load prepare stage process"
        this.configuration = configuration
    }

    def init(String process, String solution, String compiler) {
        def factory = CompilerFactory.getCompiler(compiler)
        factory.build(dsl)
    }
}
