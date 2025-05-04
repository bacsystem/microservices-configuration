package main.com.bacsystem.factory

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

    static BuildFactory getCompiler(String type){
        switch (type.toLowerCase()){
            case "gradle":
                return new Gradle()
            default :
                error ""
        }
    }
}
