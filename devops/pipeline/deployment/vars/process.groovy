import main.com.bacsystem.enums.PipelineProcess


def call(Map params = [:]) {
    if (PipelineProcess.PROCESS.value() == params.process) {
        echo "[INFO] Init process pipeline jenkins."
        bacsystem(params)
    } else {
        warnError "[WARN] No recognized build tool found. Skipping build process."
    }
}