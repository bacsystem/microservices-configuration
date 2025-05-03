import main.flow.constants.IConstants


def call(Map params = [:]) {
    echo "echo value ${params.value}."
    echo "echo instance ${params.dsl}."
    echo "echo wout ${params.wout}."
    echo "echo smc ${params.smc}."
    echo "echo opt ${params.opt}."

    if (IConstants.PipelineProcess.PROCESS.value() == params.value) {
        echo "[INFO] Init process pipeline jenkins."
        bacsystem(params)
    } else {
        echo "[WARN] No recognized build tool found. Skipping build process."
    }

}