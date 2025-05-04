package main.flow.factory

/**
 * <b>Golang</b>
 * <p>
 * This class specifies the requirements for the {@link Golang} component,
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


class Golang extends BuildFactory {

    @Override
    void build(def dsl) {
        dsl.COMPILER
    }

    @Override
    void deploy(Object dsl) {

    }
}
