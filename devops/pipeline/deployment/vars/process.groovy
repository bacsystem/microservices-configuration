import main.flow.builder.Builder
import main.flow.constants.IConstants

def call(Map params = [:]) {
    echo "echo value ${params.value}."
    echo "echo instance ${params.instance}."
    echo "echo wout ${params.wout}."
    echo "echo smc ${params.smc}."
    echo "echo opt ${params.opt}."

    def builder = new Builder(params.instance)
    params.builder = builder

    if (IConstants.PipelineProcess.PROCESS.value() == params.value) {
        bacsystem(params)
    } else {
        echo "No recognized build tool found. Skipping build process."
    }

}