package main.flow.base

import main.flow.constants.IConstants

class PipelineBase implements IConstants {


    @Serial
    static final long serialVersionUID = 1

    def dsl

    PipelineBase(def dsl) {
        this.dsl = dsl

    }

    def getDsl() {
        return this.dsl
    }
}
