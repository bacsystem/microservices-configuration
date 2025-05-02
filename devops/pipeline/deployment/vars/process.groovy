import main.flow.builder.Builder
import main.flow.constants.IConstants

def call(Map params = [:]) {
    sh "echo value ${params.value}."
    sh "echo instance ${params.instance}."
    sh "echo wout ${params.wout}."
    sh "echo smc ${params.smc}."
    sh "echo opt ${params.opt}."

    def builder = new Builder(params.instance)
    params.builder = builder

    if (IConstants.PipelineProcess.PROCESS.value() == params.value) {
        bacsystem(params)
    } else {
        echo "No recognized build tool found. Skipping build process."
    }

}