package main.com.bacsystem.enums


import java.util.stream.Collectors

/**
 * <b>Compiler</b>
 * <p>
 * This class specifies the requirements for the {@link Compiler} component,
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


enum Compiler {
    MAVEN("pom.xml"),
    GOLANG("go.mod"),
    NPM("package.json"),
    PYTHON("setup.py"),
    GRADLE("build.gradle");
    private String _value

    private static final Map<String, Compiler> MAP

    Compiler(String file) {
        this._value = file
    }

    static {
        MAP = new HashMap<>()
        for (Compiler c : values()) {
            MAP.put(c.value(), c)
        }
        //Stream.of(values()).collect(Collectors.toMap(a->))
    }

    String value() {
        return this._value
    }

    String files() {
        return this._value
    }

    static String get(String val) {
        def v = values().find { it._value == val }
        return v?.value()
    }

    static Compiler from(String file) {
        return MAP.get(file)
    }

    static List<String> list() {
        return List.of(values()).stream()
                .map(i -> i.files())
                .collect(Collectors.toList())
    }
}