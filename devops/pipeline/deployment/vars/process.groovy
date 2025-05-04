import main.com.bacsystem.constants.IConstants


def call(Map params = [:]) {
    if (IConstants.PipelineProcess.PROCESS.value() == params.process) {
        echo "[INFO] Init process pipeline jenkins."
        bacsystem(params)
    } else {
        warnError "[WARN] No recognized build tool found. Skipping build process."
    }
}