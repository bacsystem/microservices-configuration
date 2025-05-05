package main.com.bacsystem.factory.flow


import main.com.bacsystem.enums.WorkFlow

import static main.com.bacsystem.utils.Utility.console
import static main.com.bacsystem.utils.Utility.displayNames

/**
 * <b>BuildFlow</b>
 * <p>
 * This class specifies the requirements for the {@link IFlowFactory} component,
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


abstract class IFlowFactory {

    abstract void flow(String flow, def dsl)

    static void commit(def dsl) {
        def logger = { param -> dsl.sh(returnStdout: true, script: param) }
        dsl.env.REPOSITORY_EMAIL = logger('git --no-pager show -s --format=\'%ae\' --ignore-cr-at-eol').trim()
        dsl.env.LAST_COMMIT = logger('if [ -d .github ] ; then if test -f .github/.lastCommit ; then cat .github/.lastCommit; else echo "0" > .github/.lastCommit; echo "0"; fi; else echo "0"; fi').trim()
        dsl.env.BUILD_GIT_NR = logger('git rev-list --count --all --skip $LAST_COMMIT').trim()
        dsl.env.COMMITID = logger('git log --format=\'%h\' -n1').take(4)
        dsl.env.PREFIX = ".B${dsl.env.COMMITID}"
        dsl.env.PREFIX_DISPLAY = ".B${dsl.env.COMMITID}"
        dsl.env.DISPLAY = ".B${dsl.env.COMMITID}"
    }

    static void tags(def dsl) {
        def branchName = dsl.env.BRANCH_NAME

        if (branchName == "master" || branchName == "main") {
            dsl.env.PREFIX = ""
        }

        if (branchName == "uat") {
            dsl.env.PREFIX = "-beta" + "${dsl.env.PREFIX}"
        }

        if (branchName == "release" || branchName.startsWith("release")) {
            dsl.env.PREFIX = "-alpha" + "${dsl.env.PREFIX}"
        }

        if (branchName == "fix" || branchName.startsWith("hotfix")) {
            dsl.env.PREFIX = "-patch" + "${dsl.env.PREFIX}"
        }

        if (branchName == "test") {
            dsl.env.PREFIX = "-beta" + "${dsl.env.PREFIX}"
        }

        if (branchName == "develop") {
            dsl.env.PREFIX = "-dev" + "${dsl.env.PREFIX}"
        }

        dsl.env.DISPLAY = ""
    }

    static void environments(String flow, def dsl) {
        def env = dsl.env
        env.IMAGE_TAG = "${env.VERSION_NUM}${env.PREFIX}"
        env.DISPLAY_NAME = "${env.VERSION_NUM}${env.PREFIX_DISPLAY}"
        env.ENVIRONMENT = "dev"
        env.JOB_NAME_F = displayNames(dsl.currentBuild)
        console("JOB_NAME_F '${env.JOB_NAME_F}'", dsl)
        dsl.currentBuild.displayName = env.DISPLAY_NAME
        console("dsl.currentBuild.displayName '${dsl.currentBuild.displayName}'", dsl)

        def branch = env.BRANCH_NAME
        switch (flow) {
            case WorkFlow.GIT_FLOW.getValue():
                if (branch == "master" || branch == "main") {
                    env.ENVIRONMENT = "prod"
                } else if (branch == "test") {
                    env.ENVIRONMENT = "test"
                } else if (branch == "uat") {
                    env.ENVIRONMENT = "uat"
                }
                break
            default:
                console("Branching flow '${flow}' is not configured", dsl)
        }
    }

    static void nexus(def dsl) {
        def env = dsl.env
        env.REGISTRY_IMAGE = "${env.REGISTRY_URL}/" + env.IMAGE + ":" + env.IMAGE_TAG
    }
}
