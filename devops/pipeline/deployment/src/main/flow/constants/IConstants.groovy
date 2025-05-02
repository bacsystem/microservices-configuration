package main.flow.constants

interface IConstants extends Serializable {

    String BRANCHING_VERSION_GITFLOW = "GitFlowBase"

    enum PullRequestStatus {

        PENDING("pending")
        private String version

        PullRequestStatus(String version) {
            this.version = version
        }

        String value() {
            return this.version
        }
    }

    enum PipelineProcess {
        PROCESS("v1")
        private String value

        PipelineProcess(String value) {
            this.value = value
        }

        String value() {
            return this.value
        }
    }
}