package main.com.bacsystem.factory.flow.scm

import main.com.bacsystem.factory.flow.IFlowFactory

/**
 * <b>GitFlow</b>
 * <p>
 * This class specifies the requirements for the {@link GitFlow} component,
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


class GitFlow extends IFlowFactory {

    @Override
    void flow(def dsl, def type) {
        commit(dsl)
        tags(dsl)
    }
}
