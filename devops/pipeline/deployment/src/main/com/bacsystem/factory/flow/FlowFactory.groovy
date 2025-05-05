package main.com.bacsystem.factory.flow

import main.com.bacsystem.enums.WorkFlow
import main.com.bacsystem.factory.flow.scm.GitFlow
import main.com.bacsystem.factory.flow.scm.GithubFlow
import main.com.bacsystem.factory.flow.scm.GitlabFlow

/**
 * <b>FlowFactory</b>
 * <p>
 * This class specifies the requirements for the {@link FlowFactory} component,
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


class FlowFactory {

    static IFlowFactory getFlowFactory(String process, def dsl) {
        switch (process) {
            case WorkFlow.GIT_FLOW.getValue():
                return new GitFlow()
            case WorkFlow.GITHUB_FLOW.getValue():
                return new GithubFlow()
            case WorkFlow.GITLAB_FLOW.getValue():
                return new GitlabFlow()
            default:
                dsl.error "[ERROR] Not process get flow factory"
        }
    }
}
