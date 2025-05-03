package main.flow.pipeline

import main.flow.base.PipelineBase

/**
 * <b>Prepare</b>
 * <p>
 * This class specifies the requirements for the {@link Prepare} component,
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


class Prepare extends PipelineBase {
    @Serial
    static final long serialVersionUID = 1

    private static final String STATIC_CONFIG = "static_environment.env"

    Prepare(def scriptInstance) {
        super(scriptInstance)
    }
}
