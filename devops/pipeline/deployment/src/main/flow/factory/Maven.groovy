package main.flow.factory

/**
 * <b>Maven</b>
 * <p>
 * This class specifies the requirements for the {@link Maven} component,
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


class Maven extends BuildFactory {
    @Override
    void build(def dsl) {
        def pom = dsl.readMavenPom()

    }

    @Override
    void registry(Object dsl) {

    }
}
