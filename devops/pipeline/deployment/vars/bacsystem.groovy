def call() {
    pipeline {
        agent any

        stages {
            stage('Clonar Repositorio') {
                steps {
                    echo 'Clonando el repositorio...'
                    sh "ls -la"
                    script{
                        (agentLabel, solutionProject) = utils.prepareDeploy()
                        if (agentLabel == null) {
                            agentLabel = "principal"
                        }
                        sh "ls -la"
                    }

                }
            }

            stage('Construir Proyecto') {
                steps {
                    echo 'Construyendo el proyecto...'
                }
            }

            stage('Pruebas Unitarias') {
                steps {
                    echo 'Ejecutando pruebas unitarias...'
                }
            }

            stage('Empaquetar Artefacto') {
                steps {
                    echo 'Empaquetando el artefacto...'
                }
            }

            stage('Despliegue') {
                steps {
                    echo 'Desplegando el artefacto...'
                }
            }
        }

        post {
            success {
                echo 'Pipeline completado exitosamente'
            }
            failure {
                echo 'Hubo un error en el pipeline'
            }
        }
    }

}