package main.flow

class PipelineBase implements Serializable, IConstants {
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
