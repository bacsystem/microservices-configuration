package main.com.bacsystem.enums

/**
 * <b>PullRequestStatus</b>
 * <p>
 * This class specifies the requirements for the {@link PullRequestStatus} component,
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