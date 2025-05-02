def call(String version, scriptInstance) {
    echo "version ${version}"
    echo "scriptInstance ${scriptInstance}"

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

        stages {
            stage('Setting Up & Initialising Env') {
                agent {
                    label agentLabel
                }
                steps {
                    script {
                        (agentLabel, solutionProject) = utils.deployParams()
                        if (agentLabel == null) {
                            agentLabel = "principal"
                        }
                        sh "ls -la"
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
                    echo "always Build jenkins"
                }
            }
            failure {
                script {
                    echo "failure Build jenkins"
                }
            }
        }
    }

}