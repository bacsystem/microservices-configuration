package main.flow.base

import main.flow.constants.IConstants

class PipelineBase implements IConstants {


    @Serial
    static final long serialVersionUID = 1

    def _dsl

    PipelineBase(def dsl) {
        this._dsl = dsl

    }

    def Console(String msg) {
        this._dsl.echo msg
    }

    boolean ExistFile(String value) {
        if (!value) return false
        return _dsl?.fileExists(value) ?: false
    }
}
