package main.flow.base

import main.flow.constants.IConstants

class PipelineBase implements IConstants {

    @Serial
    static final long serialVersionUID = 1

    def scriptInstance

    PipelineBase(def scriptInstance) {
        this.scriptInstance = scriptInstance
    }

    def getInstance() {
        return this.scriptInstance
    }
}
