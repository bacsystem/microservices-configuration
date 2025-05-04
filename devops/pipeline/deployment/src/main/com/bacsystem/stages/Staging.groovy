package main.com.bacsystem.stages

import main.com.bacsystem.base.PipelineBase
import main.com.bacsystem.pipeline.ProcessPrepare

import static main.com.bacsystem.utils.Utility.*

/**
 * <b>Staging</b>
 * <p>
 * This class specifies the requirements for the {@link Staging} component,
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


class Staging extends PipelineBase {

    @Serial
    static final long serialVersionUID = 1

    private ProcessPrepare processPrepare

    Staging(def dsl) {
        super(dsl)
        dsl.echo "[INFO] Load stage process"
        this.processPrepare = new ProcessPrepare(dsl)
    }

    def parameters() {
        return findParams(this._dsl)
    }

    def settings(Map param = [:], String solution = "") {
        console("[INFO] Lookup jenkins workflow type -> [${param.workflow}] ", this._dsl)
        try {
            def flow = workflow("${param.workflow}")
            this.processPrepare.init(flow, solution)
        } catch (err) {
            error "[ERROR] Process failed with error [${err}]"
        } finally {
            console("[INFO] Setting load successfully for jenkins flow", this._dsl)
        }
    }
}
