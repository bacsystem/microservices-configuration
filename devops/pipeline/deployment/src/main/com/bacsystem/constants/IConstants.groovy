package main.com.bacsystem.constants

interface IConstants extends Serializable {

    String BRANCHING_VERSION_GITFLOW = "GitFlowBase"

    enum PullRequestStatus {

        PENDING("pending"),
        IN_PROGRESS("in_progress"),
        SUCCESS("success"),
        FAILURE("failure"),
        ERROR("error"),

        private String value

        PullRequestStatus(String value) {
            this.value = value
        }

        String value() {
            return this.value
        }

        static String get(String val) {
            def v = values()
                    .find { it.name() == val }
            return v.value()
        }
    }

    enum PipelineProcess {
        PROCESS("Gitflow")
        private String value

        PipelineProcess(String value) {
            this.value = value
        }

        String value() {
            return this.value
        }

        static String get(String val) {
            def v = values()
                    .find { it.name() == val }
            return v.value()
        }
    }
}