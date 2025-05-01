def call(String message) {
    echo "Hello world: ${message}"
    pipeline {
        agent any
        environment {
            ENTORNO = 'dev'
        }
        stages {
            stage('Initialize') {
                steps {
                    echo "Desplegando en entorno ${ENTORNO}"
                }
            }
        }
    }
}