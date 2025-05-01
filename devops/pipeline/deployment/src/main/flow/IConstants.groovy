package main.flow

interface IConstants extends Serializable {
    String BRANCHING_VERSION_GITFLOW = "GitFlowBase"

    enum PullRequestStatus {

        PENDING("pending")
        private String version

        PullRequestStatus(String version) {
            this.version = version
        }

        String Version() {
            return this.version
        }
    }

    enum PipelineVersion {
        Version("GitFlowBase")
        private String version

        PipelineVersion(String version) {
            this.version = version
        }

        String Version() {
            return this.version
        }
    }
}