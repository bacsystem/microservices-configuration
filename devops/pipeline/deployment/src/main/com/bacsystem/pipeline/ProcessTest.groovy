package main.com.bacsystem.pipeline

import main.com.bacsystem.base.PipelineBase

import static main.com.bacsystem.utils.Utility.console

/**
 * <b>ProcessTest</b>
 * <p>
 * This class specifies the requirements for the {@link ProcessTest} component,
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


class ProcessTest extends PipelineBase {
    @Serial
    static final long serialVersionUID = 1

    ProcessTest(def dsl) {
        super(dsl)
        dsl.echo "[INFO] load test process"
    }

    def init() {
        String result = ""
        String details = ""
        String CONTEXT_GH_PR = "${this._dsl.CONTEXT_GH_PR_PREFIX} Unit Tests"
        String DESCRIPTION_GH_PR = 'Running Unit Tests'
        String URL_GH_PR = "${this._dsl.env.JOB_URL}${this._dsl.env.BUILD_NUMBER}/testReport"
        this._dsl.env.LAST_STEP = this._dsl.env.STAGE_NAME
        console("[INFO] result ${result}")
        console("[INFO] details ${details}")
        console("[INFO] CONTEXT_GH_PR ${CONTEXT_GH_PR}")
        console("[INFO] DESCRIPTION_GH_PR ${DESCRIPTION_GH_PR}")
        console("[INFO] URL_GH_PR ${URL_GH_PR}")
        console("[INFO] LAST_STEP ${this._dsl.env.LAST_STEP}")

    }
}
