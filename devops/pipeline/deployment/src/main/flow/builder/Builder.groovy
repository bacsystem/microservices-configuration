package main.flow.builder

import main.flow.base.PipelineBase

/**
 * <b>Builder</b>
 * <p>
 * This class specifies the requirements for the {@link Builder} component,
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


class Builder extends PipelineBase {
    @Serial
    static final long serialVersionUID = 1

    Builder(def scriptInstance) {
        super(scriptInstance)
        echo "[INFO] load builder process"
    }
}
