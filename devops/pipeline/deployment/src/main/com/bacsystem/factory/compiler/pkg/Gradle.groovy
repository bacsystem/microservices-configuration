package main.com.bacsystem.factory.compiler.pkg


import main.com.bacsystem.factory.compiler.ICompilerFactory

import static main.com.bacsystem.utils.Utility.console
import static main.com.bacsystem.utils.Utility.exist

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


class Gradle extends ICompilerFactory {

    @Override
    void compiler(Object dsl) {

        dsl.env.COMPILER = "gradle"
        dsl.env.IS_MAVEN_COMPILER = false
        dsl.env.COMPILER_BASE = "sh gradlew"

        //dsl.env.GRADLE_USER_HOME = "${dsl.env.JENKINS_HOME}/.gradle"

        if (!exist("./gradle/wrapper/gradle-wrapper.jar")) {
            dsl.sh "gradle wrapper"
        }

        console("[INFO] [gradle] Starting the construction of the component.", dsl)
        // Prepare gradlew
        def propertyFile = 'properties.tmp'
        dsl.sh "chmod +x ./gradlew"
        //dsl.sh "./gradlew -q properties"
        dsl.sh "./gradlew -q properties > ${propertyFile}"

        readParameter(dsl)
        //validar la version y el agente de jdk  y docker
    }

    void readParameter(def dsl) {
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
        console("[INFO] [gradle] Version the component. ${version}", dsl)
        console("[INFO] [gradle] Group the component. ${group}", dsl)
        console("[INFO] [gradle] Type the component. ${type}", dsl)
        console("[INFO] [gradle] Solution the component. ${solution}", dsl)
        console("[INFO] [gradle] Name the component. ${name}", dsl)
        console("[INFO] [gradle] Module the component. ${module}", dsl)
        console("[INFO] [gradle] Pipe the component. ${pipe}", dsl)
        console("[INFO] [gradle] Successfully complete the construction of the component.", dsl)

    }
}
