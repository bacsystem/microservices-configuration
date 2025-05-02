import main.flow.constants.IConstants

def call(Map params = [:]) {
    sh "echo process ${params.process}."
    sh "echo instance ${params.instance}."
    sh "echo wout ${params.wout}."
    sh "echo smc ${params.smc}."
    sh "echo opt ${params.opt}."
//String process = IConstants.PipelineProcess.PROCESS.value(), scriptInstance, boolean wout = false, boolean smc = false, boolean opt = false
}