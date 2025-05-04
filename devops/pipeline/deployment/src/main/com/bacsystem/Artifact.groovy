package main.com.bacsystem

import main.com.bacsystem.base.PipelineBase

/**
 * <b>Artifact</b>
 * <p>
 * This class specifies the requirements for the {@link Artifact} component,
 * developed in accordance with the development standards established by christian.
 * Collaboration is encouraged for the enhancement and expansion of the deployment.
 * </p>
 * <p>
 * <b>Description:</b>
 * </p>Here!</p>
 *
 * @author christian
 * @author dbacilio88@outlook.es
 * @since 1/05/2025
 */


class Artifact extends PipelineBase {
    @Serial
    static final long serialVersionUID = 1

    Artifact(Object scriptInstance) {
        super(scriptInstance)
    }

    def archive(String path, boolean isEmpty= false){

    }
}
