package main.com.bacsystem.factory
/**
 * <b>LoadFactory</b>
 * <p>
 * This class specifies the requirements for the {@link BuildFactory} component,
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


abstract class BuildFactory {

    abstract void build(def dsl)


    abstract void registry(def dsl)

    static void deploy(def application, def environment, def image, def dsl) {
        dsl.env.APP_NAME = application
        dsl.env.DISPLAY_NAME = "${environment}-${application}-${dsl.env.BUILD_NUMBER}"
        if (!image) {
            dsl.env.IMAGE = image
            dsl.env.REGISTRY_IMAGE = "${dsl.REGISTRY_URL}/${dsl.env.IMAGE}"
        } else {
            dsl.env.IMAGE = "default"
            dsl.env.REGISTRY_IMAGE = "default"
        }

        dsl.echo("APP_NAME: ${dsl.env.APP_NAME}")
        dsl.echo("DISPLAY_NAME: ${dsl.env.DISPLAY_NAME}")
        dsl.echo("IMAGE: ${dsl.env.IMAGE}")
        dsl.echo("REGISTRY_IMAGE: ${dsl.env.REGISTRY_IMAGE}")

    }

    static void undeploy(def application, def environment, def dsl) {
        dsl.env.APP_NAME = application
        dsl.echo("APP_NAME: ${dsl.env.APP_NAME}")
        dsl.env.DISPLAY_NAME = "${environment}-${application}-${dsl.env.BUILD_NUMBER}"
        dsl.echo("DISPLAY_NAME: ${dsl.env.DISPLAY_NAME}")
    }


}

/**
 * def branchName = scriptInstance.env.BRANCH_NAME

 switch (branchingVersion) {

 case [BRANCHING_GRAVITON, BRANCHING_VERSION_GITLABFLOW, BRANCHING_REACT_DEPLOY, BRANCHING_GO_DEPLOY]:
 if (branchName.startsWith("fix")) {
 scriptInstance.env.PREFIX = "-fix" + scriptInstance.env.PREFIX
 } else if (branchName.startsWith("release")) {
 scriptInstance.env.PREFIX = "-rls" + scriptInstance.env.PREFIX
 }
 scriptInstance.env.DISPLAY = ""
 break

 case BRANCHING_VERSION_GITFLOW:
 switch (branchName) {
 case "master":
 case "main":
 scriptInstance.env.PREFIX = ""
 break
 case "release":
 scriptInstance.env.PREFIX = "-alpha"
 break
 case "deploy-uat":
 scriptInstance.env.PREFIX = "-beta"
 break
 default:
 if (branchName.startsWith("fix/") || branchName.startsWith("bugfix/") || branchName.startsWith("hotfix/")) {
 scriptInstance.env.PREFIX = "-alpha"
 } else {
 scriptInstance.env.DISPLAY = ""
 }
 }
 break

 case BRANCHING_MOBILE_LIB:
 scriptInstance.env.PREFIX = ""
 break
 }
 -----
 def env = scriptInstance.env
 def branch = env.BRANCH_NAME

 // Inicialización común
 env.IMAGE_TAG = "${env.VERSION_NUM}${env.PREFIX}"
 env.DISPLAY_NAME = "${env.VERSION_NUM}${env.PREFIX_DISPLAY}"
 env.ENVIRONMENT = "dev" // valor por defecto

 // Lógica de selección de entorno
 switch (branchingVersion) {
 case BRANCHING_GRAVITON:
 case BRANCHING_VERSION_GITLABFLOW:
 case BRANCHING_GO_DEPLOY:
 if (branch == "master" || branch == "main") {
 env.ENVIRONMENT = "dev"
 } else if (branch.startsWith("release/")) {
 env.ENVIRONMENT = "test"
 }
 break

 case BRANCHING_VERSION_GITFLOW:
 case BRANCHING_REACT_DEPLOY:
 if (branch == "master" || branch == "main") {
 env.ENVIRONMENT = "prod"
 } else if (branch == "develop") {
 env.ENVIRONMENT = "dev"
 } else if (branch == "deploy-uat") {
 env.ENVIRONMENT = "uat"
 } else if (branch.startsWith("release")) {
 env.ENVIRONMENT = "test"
 }
 break

 case BRANCHING_VERSION_BACKEND:
 if (!branch.startsWith("release") && !env.VERSION.contains("-SNAPSHOT")) {
 try {
 error("La versión no tiene -SNAPSHOT")
 } catch (err) {
 scriptInstance.unstable(message: "Se identifica que la versión no tiene -SNAPSHOT")
 }
 }
 break

 case PipelineVersion.DEPLOY.value():
 prepareDeploy()
 break

 case PipelineVersion.UNDEPLOY.value():
 prepareUnDeploy()
 break

 default:
 scriptInstance.echo("Branching version '${branchingVersion}' is not configured")
 }

 // Solo construir imagen si no es deploy puro
 if (branchingVersion != PipelineVersion.DEPLOY.value()) {
 env.REGISTRY_IMAGE = "${scriptInstance.REGISTRY_URL}/${env.IMAGE}:${env.IMAGE_TAG}"
 }

 // Asignación de nombres
 env.JOB_NAME_F = scriptInstance.utils.getDisplayName(scriptInstance.currentBuild)
 scriptInstance.currentBuild.displayName = env.DISPLAY_NAME

 // Cargar archivo .env si aplica
 if (!(branchingVersion in [BRANCHING_REACT_DEPLOY, BRANCHING_GO_DEPLOY])) {
 def envFilePath = './.env'
 if (scriptInstance.fileExists(envFilePath)) {
 scriptInstance.echo "loading environment from file DEVELOP"
 scriptInstance.load envFilePath
 } else {
 scriptInstance.echo "NOT custom environment file present DEVELOP"
 }
 }

 // Mostrar variables si aplica
 if (branchingVersion != BRANCHING_MOBILE_LIB) {
 scriptInstance.echo "REGISTRY_IMAGE=${env.REGISTRY_IMAGE}"
 scriptInstance.echo "ENVIRONMENT=${env.ENVIRONMENT}"
 scriptInstance.echo "SOLUTION=${scriptInstance.SOLUTION}"
 scriptInstance.echo "VERSION_NUM=${env.VERSION_NUM}"
 scriptInstance.echo "VERSION=${env.VERSION}"
 }
 */
