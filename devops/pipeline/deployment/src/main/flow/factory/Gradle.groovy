package main.flow.factory

/**
 * <b>Gradle</b>
 * <p>
 * This class specifies the requirements for the {@link Gradle} component,
 * developed in accordance with the development standards established by christian.
 * Collaboration is encouraged for the enhancement and expansion of the deployment.
 * </p>
 * <p>
 * <b>Description:</b>
 * </p>Here!</p>
 *
 * @author christian
 * @author dbacilio88@outlook.es
 * @since 3/05/2025
 */


class Gradle extends BuildFactory {
    @Override
    void build(def dsl) {
        dsl.COMPILER = "gradle"
        if (!dsl.fileExists("./gradle/wrapper/gradle-wrapper.jar")) {
            dsl.sh "gradle wrapper"
        }
        dsl.echo "[INFO] [gradle] Starting the construction of the component."
        dsl.sh "chmod +x ./gradlew"
        dsl.echo "[INFO] [gradle] Successfully complete the construction of the component."
    }
}
