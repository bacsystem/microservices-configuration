package main.com.bacsystem.factory.flow


import static main.com.bacsystem.utils.Utility.console

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

    abstract void flow(def dsl, def type)

    static void commit(def dsl) {
        def logger = { param -> dsl.sh(returnStdout: true, script: param) }
        dsl.env.REPOSITORY_EMAIL = logger('git --no-pager show -s --format=\'%ae\' --ignore-cr-at-eol').trim()
        dsl.env.LAST_COMMIT = logger('if [ -d .github ] ; then if test -f .github/.lastCommit ; then cat .github/.lastCommit; else echo "0" > .github/.lastCommit; echo "0"; fi; else echo "0"; fi').trim()
        dsl.env.BUILD_GIT_NR = logger('git rev-list --count --all --skip $LAST_COMMIT').trim()
        dsl.env.COMMITID = logger('git log --format=\'%h\' -n1').take(4)
        dsl.env.PREFIX = ".B${dsl.env.COMMITID}"
        dsl.env.PREFIX_DISPLAY = ".B${dsl.env.COMMITID}"
        dsl.env.DISPLAY = ".B${dsl.env.COMMITID}"
        console("REPOSITORY_EMAIL: ${dsl.env.REPOSITORY_EMAIL}", dsl)
        console("LAST_COMMIT: ${dsl.env.LAST_COMMIT}", dsl)
        console("BUILD_GIT_NR: ${dsl.env.BUILD_GIT_NR}", dsl)
        console("COMMITID: ${dsl.env.COMMITID}", dsl)
        console("PREFIX: ${dsl.env.PREFIX}", dsl)
        console("PREFIX_DISPLAY: ${dsl.env.PREFIX_DISPLAY}", dsl)
        console("DISPLAY: ${dsl.env.DISPLAY}", dsl)
    }

    static void tags(def dsl) {
        def branchName = dsl.env.BRANCH_NAME
        if (branchName == "master" || branchName == "main") {
            dsl.env.PREFIX = ""
        }
        if (branchName == "uat") {
            dsl.env.PREFIX = "-beta"
        }
        if (branchName == "release" || branchName.startsWith("release")) {
            dsl.env.PREFIX = "-alpha" + "${dsl.env.PREFIX}"
        }
        if (branchName == "fix" || branchName.startsWith("hotfix")) {
            dsl.env.PREFIX = "-patch" + "${dsl.env.PREFIX}"
        }
        if (branchName == "test") {
            dsl.env.PREFIX = "-beta"
        }
        dsl.env.DISPLAY = ""
        console("[INFO] Tag display: [${dsl.env.DISPLAY}]")
        console("[INFO] Tag prefix: [${dsl.env.PREFIX}]")
    }
}
