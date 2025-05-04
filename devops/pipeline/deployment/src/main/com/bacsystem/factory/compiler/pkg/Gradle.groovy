package main.com.bacsystem.factory.compiler.pkg

import main.com.bacsystem.factory.BuildFactory

import static main.com.bacsystem.utils.Utility.console

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
        dsl.COMPILER_BASE = "sh gradlew"
        //dsl.env.GRADLE_USER_HOME = "${dsl.env.JENKINS_HOME}/.gradle"

        if (!dsl.fileExists("./gradle/wrapper/gradle-wrapper.jar")) {
            dsl.sh "gradle wrapper"
        }

        console("[INFO] [gradle] Starting the construction of the component.", dsl)
        // Prepare gradlew
        def propertyFile = 'properties.tmp'
        dsl.sh "chmod +x ./gradlew"
        //dsl.sh "./gradlew -q properties"
        dsl.sh "./gradlew -q properties > ${propertyFile}"

        def readProperties = { file, property ->
            return dsl.sh(script: "cat ${file} | grep '${property}:' | awk '{print \$2}'", returnStdout: true).trim()
        }

        String version = readProperties(propertyFile, "version") ?: readProperties(propertyFile, "sdk_version_number")
        //dsl.env.VERSION_NUM    = dsl.env.VERSION?.replace("-SNAPSHOT", "")
        String name = Utility.repository(dsl)
        //dsl.env.IMAGE          = dsl.env.APP_NAME
        //dsl.env.FOLDER = readProperties(propertyFile, 'pipelineFolderModule')
        String module = readProperties(propertyFile, 'pipelineFolderModule')
        //dsl.env.PIPE_VERSION   = readProperties(propertyFile,'pipelineVersion')
        String pipe = readProperties(propertyFile, 'pipelineVersion')
        String group = readProperties(propertyFile, "group")
        String type = readProperties(propertyFile, "type")
        String solution = readProperties(propertyFile, "solution") ?: readProperties(propertyFile, "solution") ?: readProperties(propertyFile, "app.solution")
        //dsl.env.SONAR_KEY      = "${readProperties(propertyFile,'group')}:${dsl.env.IMAGE}"

        // dsl.echo "[INFO] [gradle] Version the component. ${version}"
        // dsl.echo "[INFO] [gradle] Group the component. ${group}"
        // dsl.echo "[INFO] [gradle] Type the component. ${type}"
        // dsl.echo "[INFO] [gradle] Solution the component. ${solution}"
        // dsl.echo "[INFO] [gradle] Name the component. ${name}"
        // dsl.echo "[INFO] [gradle] Module the component. ${module}"
        // dsl.echo "[INFO] [gradle] Pipe the component. ${pipe}"
        // dsl.echo "[INFO] [gradle] Successfully complete the construction of the component."

        //validar la version y el agente de jdk  y docker
    }

    @Override
    void registry(Object dsl) {

    }

}
