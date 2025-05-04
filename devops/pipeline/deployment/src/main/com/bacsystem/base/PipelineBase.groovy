package main.com.bacsystem.base

import main.com.bacsystem.constants.IConstants

class PipelineBase implements IConstants {

    @Serial
    static final long serialVersionUID = 1

    def _dsl

    PipelineBase(def dsl) {
        this._dsl = dsl
    }
}
