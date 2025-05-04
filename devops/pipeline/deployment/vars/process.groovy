def call(Map params = [:]) {
    echo "[INFO] Init process pipeline jenkins."
    bacsystem(params)
    /*
    if (PipelineProcess.GIT_FLOW.value() == params.workflow) {
    } else {
        warnError "[WARN] No recognized build tool found. Skipping build process."
    }

     */
}