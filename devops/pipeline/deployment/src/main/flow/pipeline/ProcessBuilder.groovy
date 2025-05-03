package main.flow.pipeline

import main.flow.base.PipelineBase
import main.flow.global.Configuration

/**
 * <b>Builder</b>
 * <p>
 * This class specifies the requirements for the {@link ProcessBuilder} component,
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


class ProcessBuilder extends PipelineBase {
    @Serial
    static final long serialVersionUID = 1
    private boolean isPrepare = false
    private boolean isCustom = false

    private Configuration configuration

    ProcessBuilder(def dsl) {
        super(dsl)
        dsl.echo "[INFO] load builder process"
        this.configuration = new Configuration(dsl)
    }

    def prepare(String process, String solution) {
        if (!isPrepare && !isCustom) {

        }
    }


}
