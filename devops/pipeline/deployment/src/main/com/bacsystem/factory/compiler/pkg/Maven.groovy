package main.com.bacsystem.factory.compiler.pkg


import main.com.bacsystem.factory.compiler.ICompilerFactory

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


class Maven extends ICompilerFactory {
    @Override
    void compiler(Object dsl) {
        def pom = dsl.readMavenPom()
    }
}
