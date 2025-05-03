def executeBuildTools(String tools) {
    echo "[INFO] Selected build tool: ${tools}"
    def buildTools = [
            'maven' : 'mvn clean install',
            'gradle': './gradlew build',
            'golang': 'go build',
            'nodejs': 'npm install && npm run build',
            'python': 'python setup.py install'
    ]
    echo "[INFO] Build constains key tool: ${buildTools.containsKey(tools)}"
    if (buildTools.containsKey(tools)) {
        echo "[INFO] Running ${tools}..."
        sh buildTools[tools]
    } else {
        error "[ERROR] Unsupported build tool: ${tools}"
    }
}
