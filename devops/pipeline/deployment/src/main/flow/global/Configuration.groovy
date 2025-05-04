package main.flow.global


import main.flow.base.PipelineBase

/**
 * <b>Configuration</b>
 * <p>
 * This class specifies the requirements for the {@link Configuration} component,
 * developed in accordance with the development standards established by christian.
 * Collaboration is encouraged for the enhancement and expansion of the deployment.
 * </p>
 * <p>
 * <b>Description:</b>
 * </p>Here!</p>
 *
 * @author christian
 * @author dbacilio88@outlook.es
 * @since 2/05/2025
 */


class Configuration extends PipelineBase {
    @Serial
    static final long serialVersionUID = 1

    // Base path for configuration resources
    private static final String CONFIG_BASE_PATH = "config"
    // Configuration file name
    private String configName

    Configuration(def dsl) {
        super(dsl)
        dsl.echo "[INFO] load configuration process"
    }

    /**
     * Set the configuration file name to be used.
     * @param configName the name of the config file inside /config
     * @return this for method chaining
     */
    Configuration withConfig(String configName) {
        Console("[INFO] File environment configuration: ${configName}")
        this.configName = configName
        return this
    }

    /**
     * Loads the configuration content and writes it into a target file.
     * @param targetFile Target file name in the workspace. If null, uses configName.
     * @return this
     */
    Configuration loadToFile(String target) {
        def fileName = target ?: configName
        writeToFile(fileName, getResourceContent())
        return this
    }
    /**
     * Set the configuration file name to be used.
     * @param configName the name of the config file inside /config
     * @return this for method chaining
     */
    def loadConfiguration(String path, String target = null) {
        if (!target) {
            target = path
        }
        def content = dsl.libraryResource("${CONFIG_BASE_PATH}/${configName}")
        dsl.writeFile(file: target, text: content)
    }
    /**
     * Returns the raw content of the configuration file.
     * @return String with file content
     */
    String getContent() {
        return getResourceContent()
    }

    /**
     * Returns the raw contents of the configuration file.
     * @param path logical config path (unused here)
     * @return string content of config file
     */
    def getConfiguration(String path) {
        return dsl.libraryResource("${CONFIG_PATH_BASE}/${configName}")
    }

    /**
     * Returns the raw contents of the configuration file.
     * @param path logical config path (unused here)
     * @return string content of config file
     */
    void execute() {

        writeToFile(configName, getResourceContent())
        this._dsl.sh "cat ./${configName}"
        this._dsl.build("./$configName")
        //this._dsl.println("configTest=${this._dsl.TEST_CONFIG}")
    }

    // ========== Private Methods ==========

    private String getResourceContent() {
        Console("[INFO] Get resource content")
        assert configName: "configName must be set before accessing content."
        return this._dsl.libraryResource("${CONFIG_BASE_PATH}/${configName}")
    }

    private void writeToFile(String fileName, String content) {
        Console("[INFO] Write file ${fileName}.")
        this._dsl.writeFile(file: fileName, text: content)
    }
}
