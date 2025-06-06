import main.com.bacsystem.stages.Staging

def call(Map params = [:]) {

    def staging = new Staging(params.dsl)

    def agentLabel = "principal"
    def solutionProject = ""

    pipeline {
        agent none

        tools {
            maven 'maven'
            gradle 'gradle'
            jdk 'jdk-21'
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
                        (agentLabel, solutionProject) = staging.parameters()
                        if (agentLabel == null) {
                            agentLabel = "principal"
                        }
                        echo "[INFO] Found parameter -> { agent: [${agentLabel}] and solution project: [${solutionProject}] }"
                        sh "ls -la"
                        staging.settings(params, solutionProject)
                    }
                }
            }

            stage('Unit Test') {
                agent {
                    label agentLabel
                }
                steps {

                    script {
                        echo "Unit Branch... ${BRANCH_NAME}"
                        echo "Unit Test... ${ENVIRONMENT}"
                        staging.testing()

                    }
                }

                post {
                    always {
                        script {
                            echo "[INFO] Always save: Test Jenkins"
                            staging.saveReport()
                        }
                    }
                }
            }


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