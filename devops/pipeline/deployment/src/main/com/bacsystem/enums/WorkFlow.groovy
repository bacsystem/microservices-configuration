package main.com.bacsystem.enums
/**
 * <b>PipelineProcess</b>
 * <p>
 * This class specifies the requirements for the {@link WorkFlow} component,
 * developed in accordance with the development standards established by christian.
 * Collaboration is encouraged for the enhancement and expansion of the deployment.
 * </p>
 * <p>
 * <b>Description:</b>
 * </p>Here!</p>
 *
 * @author christian
 * @author dbacilio88@outlook.es
 * @since 4/05/2025
 */


enum WorkFlow {
    GIT_FLOW("Gitflow"),
    GITHUB_FLOW("GithubFlow"),
    GITLAB_FLOW("GitlabFlow");

    private String value

    WorkFlow(String value) {
        this.value = value
    }

    String value() {
        return this.value
    }

    static String get(String val) {
        def v = values()
                .find { it.value == val }
        return v?.value()
    }
}