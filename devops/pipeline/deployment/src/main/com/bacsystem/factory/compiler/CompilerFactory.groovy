package main.com.bacsystem.factory.compiler

import main.com.bacsystem.enums.Compiler
import main.com.bacsystem.factory.compiler.pkg.Gradle
import main.com.bacsystem.factory.compiler.pkg.Maven

/**
 * <b>CompilerFactory</b>
 * <p>
 * This class specifies the requirements for the {@link CompilerFactory} component,
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


class CompilerFactory {

    static ICompilerFactory getCompiler(String type, def dsl) {
        switch (type.toLowerCase()) {
            case Compiler.GRADLE.GetMgn():
                return new Gradle()
            case Compiler.MAVEN.GetMgn():
                return new Maven()
            default:
                dsl.error "[ERROR] Not compiler factory"
        }
    }
}
