import main.com.bacsystem.stages.Staging

import static main.com.bacsystem.utils.Utility.flowType

def call(Map params = [:]) {

    def staging = new Staging(params.dsl)

    def agentLabel = "principal"
    def solutionProject = ""

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
                    label agentLabel
                }
                steps {
                    script {
                        echo "[INFO] Searching parameters in config deploy file."

                        (agentLabel, solutionProject) = staging.getParameters()
                        // Set fallback values if null
                        if (agentLabel == null) {
                            agentLabel = "principal"
                        }
                        echo "[INFO] Found parameter -> { agent: [${agentLabel}] and solution project: [${solutionProject}] }"

                        // Directory listing (for debugging)
                        sh "ls -la"

                        //obtains flow type
                        echo "[INFO] Work with flow type [${params.process}]"
                        String flowType = flowType("${params.process}")

                        if (flowType == null) {
                            echo "[INFO] Using flow type: ${flowType} and solution project: ${solutionProject}"
                        } else {
                            echo "[WARN] Not found flow type: ${flowType} and solution project: ${solutionProject}"
                        }
/*
                        String compiler = staging.getCompiler()

                        if (compiler != null) {
                            echo "[INFO] Detected build tool: ${compiler}"
                            staging.getSetting("${params.value}", solutionProject, compiler)
                        } else {
                            echo "No recognized build tool found. Skipping build process."
                        }

 */

                    }
                }
            }
/*
            stage('Unit Test') {
                steps {
                    script {

                        echo "Unit Branch... ${BRANCH_NAME}"
                        echo "Unit Test... ${ENVIRONMENT}"
                        // staging.test(params)
                    }


                }
            }

 */
/*
            stage('Dependencies, Code Scan & Bugs') {
                steps {
                    echo 'Dependencies, Code Scan & Bugs...'
                }
            }

 */
/*
            stage('SonarQube Quality Gate') {
                steps {
                    echo 'SonarQube Quality Gate...'
                }
            }

 */
/*
            stage('Build Image') {
                steps {
                    echo 'Build Image...'
                }
            }

 */
/*
            stage('deploy to Dev') {
                steps {
                    echo 'deploy to Dev...'
                }
            }

 */
/*
            stage('deploy to Test') {
                steps {
                    echo 'deploy to Test...'
                }
            }

 */
            /*
            stage('deploy to UAT') {
                steps {
                    echo 'deploy to UAT...'
                }
            }

             */
            /*
            stage('deploy to Prod') {
                steps {
                    echo 'deploy to Prod...'
                }
            }

             */
            /*
            stage('Release') {
                steps {
                    echo 'Release...'
                }
            }

             */

            /*
            stage('Archive Build') {
                steps {
                    echo 'Archive Build...'
                }
            }

             */
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