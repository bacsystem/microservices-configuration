package main.com.bacsystem.enums
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
    MAVEN("pom.xml", "maven", "mvn clean install"),
    GOLANG("go.mod", "golang", "go build"),
    NPM("package.json", "npm", "npm install && npm run build"),
    PYTHON("setup.py", "python", "python setup.py install"),
    GRADLE("build.gradle", "gradle", "./gradlew build");
    private String _file
    private String _mgn
    private String _cmd

    private static final Map<String, Compiler> MAP

    Compiler(String file, String mgn, String cmd) {
        this._file = file
        this._mgn = mgn
        this._cmd = cmd
    }

    static {
        MAP = new HashMap<>()
        for (Compiler c : values()) {
            MAP.put(c._mgn, c)
        }
    }

    String getFile() {
        return this._file
    }

    static String get(String val) {
        def v = values().find { it._mgn == val }
        return v?.value()
    }

    static Compiler from(String file) {
        return MAP.get(file)
    }

    static List<String> compilers() {
        return values().collect { it?.getFile() }
    }
}