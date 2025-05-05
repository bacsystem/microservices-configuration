package main.com.bacsystem.factory.flow.scm

import main.com.bacsystem.factory.flow.IFlowFactory

/**
 * <b>GitlabFlow</b>
 * <p>
 * This class specifies the requirements for the {@link GitlabFlow} component,
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


class GitlabFlow extends IFlowFactory {
    @Override
    void flow(String flow, Object dsl) {
        commit(dsl)
        tags(dsl)
        environments(flow, dsl)
    }
}
