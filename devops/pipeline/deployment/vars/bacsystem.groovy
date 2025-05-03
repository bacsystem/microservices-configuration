import main.flow.builder.Builder

//def call(Builder builder, boolean wout,boolean smc,String process, boolean opt) {
def call(Map params = [:]) {
    echo "process ${params}"
    echo "process ${params.value}"

    def builder = new Builder(params.instance)

    //def agentLabel = "principal"
    // def solutionProject = ""

    pipeline {
        agent none

        tools {
            maven 'maven'
            gradle 'gradle'
        }

        environment {
            DEPLOYMENT_VERSION = "v1"
        }
        options {
            disableConcurrentBuilds()
        }

        stages {
            stage('Setting Up & Initialising Env') {
                agent {
                    label "principal"
                }
                steps {
                    script {
                        // Call deployParams to get agent and solution project
                        def (agentLabel, solutionProject) = utils.deployParams()

                        // Set fallback values if null
                        if (agentLabel == null) {
                            agentLabel = "principal"
                        }
                        echo "[INFO] Using agent: ${agentLabel} and solution project: ${solutionProject}"


                        // Directory listing (for debugging)
                        sh "ls -la"

                        def buildTool = utils.getBuildToolFromRepo()
                        if (buildTool != null) {
                            echo "[INFO] Detected build tool: ${buildTool}"
                            utils.executeBuildTools(buildTool)
                        } else {
                            echo "No recognized build tool found. Skipping build process."
                        }
                    }
                }
            }

            stage('Unit Test') {
                steps {
                    echo 'Unit Test...'
                }
            }

            stage('Dependencies, Code Scan & Bugs') {
                steps {
                    echo 'Dependencies, Code Scan & Bugs...'
                }
            }

            stage('SonarQube Quality Gate') {
                steps {
                    echo 'SonarQube Quality Gate...'
                }
            }

            stage('Build Image') {
                steps {
                    echo 'Build Image...'
                }
            }

            stage('deploy to Dev') {
                steps {
                    echo 'deploy to Dev...'
                }
            }

            stage('deploy to Test') {
                steps {
                    echo 'deploy to Test...'
                }
            }
            stage('deploy to UAT') {
                steps {
                    echo 'deploy to UAT...'
                }
            }
            stage('deploy to Prod') {
                steps {
                    echo 'deploy to Prod...'
                }
            }
            stage('Release') {
                steps {
                    echo 'Release...'
                }
            }
            stage('Archive Build') {
                steps {
                    echo 'Archive Build...'
                }
            }
        }

        post {
            always {
                script {
                    echo "[INFO] Always executed: Build Jenkins"
                }
            }
            failure {
                script {
                    echo "[ERROR] Build failed: Jenkins"
                }
            }
        }
    }

}