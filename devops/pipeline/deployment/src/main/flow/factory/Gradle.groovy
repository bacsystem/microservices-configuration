package main.flow.factory

import main.flow.utils.Utility

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
        dsl.IS_MAVEN_COMPILER = false
        if (!dsl.fileExists("./gradle/wrapper/gradle-wrapper.jar")) {
            dsl.sh "gradle wrapper"
        }
        dsl.echo "[INFO] [gradle] Starting the construction of the component."
        def propertyFile = 'properties.tmp'
        dsl.sh "chmod +x ./gradlew"
        dsl.sh "./gradlew -q properties"
        dsl.sh "./gradlew -q properties > ${propertyFile}"
        String version = readProperties(propertyFile, "version", dsl)
        String group = readProperties(propertyFile, "group", dsl)
        String type = readProperties(propertyFile, "type", dsl)
        String solution = readProperties(propertyFile, "solution", dsl) ? readProperties(propertyFile, "solution", dsl) : readProperties(propertyFile, "app.solution", dsl)
        String name = Utility.repository(dsl)

        dsl.echo "[INFO] [gradle] Version the component. ${version}"
        dsl.echo "[INFO] [gradle] Group the component. ${group}"
        dsl.echo "[INFO] [gradle] Type the component. ${type}"
        dsl.echo "[INFO] [gradle] Solution the component. ${solution}"
        dsl.echo "[INFO] [gradle] Name the component. ${name}"
        dsl.echo "[INFO] [gradle] Successfully complete the construction of the component."
    }

    static String readProperties(String file, String property, def dsl) {
        return dsl.sh(script: "cat ${file} | grep '${property}:' | awk '{print \$2}'", returnStdout: true).trim()
    }
}
