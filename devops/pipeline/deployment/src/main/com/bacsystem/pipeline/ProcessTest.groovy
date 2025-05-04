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
    }


    def init(String process, String solution) {
        console("[INFO] Init process prepare compiler factory with [${process}] and [${solution}]",this._dsl)
    }
}
