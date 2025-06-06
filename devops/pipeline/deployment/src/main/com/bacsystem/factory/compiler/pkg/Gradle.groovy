package main.com.bacsystem.factory.compiler.pkg

import main.com.bacsystem.factory.compiler.ICompilerFactory

import static main.com.bacsystem.utils.Utility.*

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
    final def COMPILER_BASE = "sh gradlew"
    final def TASKS = "test"
    final def DEBUG = "--debug"
    def args
    String gradleHome
    boolean debug

    @Override
    void compiler(def dsl) {
        console("[INFO] Starting the preparation process for gradle in jenkins", dsl)
        dsl.env.COMPILER = "gradle"
        dsl.env.IS_MAVEN_COMPILER = false
        dsl.env.COMPILER_BASE = "sh gradlew"
        //dsl.env.GRADLE_USER_HOME = "${dsl.env.JENKINS_HOME}/.gradle"
        if (!exist("./gradle/wrapper/gradle-wrapper.jar", dsl)) {
            dsl.sh "gradle wrapper"
        }
        // Prepare gradlew
        String propertyFile = 'properties.tmp'
        dsl.sh "chmod +x ./gradlew"
        dsl.sh "./gradlew -q properties > ${propertyFile}"
        readParameter(propertyFile, dsl)
        console("[INFO] Finished preparation process for gradle in jenkins", dsl)
        //validar la version y el agente de jdk  y docker
    }

    @Override
    void sdk(Object dsl) {


    }

    @Override
    void test(Object dsl) {

    }

    @Override
    void arguments() {
        args = ""
        if (debug) {
            args += " ${DEBUG}"
        }

        if (gradleHome) {
            args += " -g ${gradleHome}"
        }

    }

    @Override
    void build(Object dsl) {
        arguments()
        dsl.echo "cmd: ${COMPILER_BASE} ${args} ${TASKS}"
        dsl.sh "${COMPILER_BASE} ${args} ${TASKS}"
    }

    static void readParameter(String propertyFile, def dsl) {
        def readProperties = { file, property ->
            return dsl.sh(script: "cat ${file} | grep '${property}:' | awk '{print \$2}'", returnStdout: true).trim()
        }
        dsl.env.VERSION_NUM = readProperties(propertyFile, "version") ?: readProperties(propertyFile, "sdk_version_number")
        dsl.env.FOLDER = readProperties(propertyFile, 'pipelineFolderModule')
        dsl.env.PIPE_VERSION = readProperties(propertyFile, 'pipelineVersion')
        dsl.env.JDK = readProperties(propertyFile, 'sourceCompatibility') ?: readProperties(propertyFile, 'targetCompatibility')
        dsl.env.APP_NAME = repository(dsl)
        dsl.env.IMAGE = dsl.env.APP_NAME
        dsl.env.APP_TYPE = readProperties(propertyFile, "type")
        dsl.env.SOLUTION = readProperties(propertyFile, "solution") ?: readProperties(propertyFile, "solution") ?: readProperties(propertyFile, "app.solution")
        dsl.env.SONAR_KEY = "${readProperties(propertyFile, 'group')}:${dsl.env.IMAGE}"
    }
}
