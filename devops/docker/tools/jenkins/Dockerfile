#/app /urs /lib
######STAGE: DEV #######
# Utilizar la imagen base de Jenkins LTS (Long-Term Support)
FROM jenkins/jenkins:slim

# Deshabilitar el asistente de configuración inicial de Jenkins
ENV JAVA_OPTS="-Xmx2g -Djenkins.install.runSetupWizard=false"

# Instalar plugins desde archivo cacheado
COPY plugins.txt /tmp/plugins.txt

# Instalar plugins de Jenkins utilizando jenkins-plugin-cli
RUN jenkins-plugin-cli --plugins $(cat /tmp/plugins.txt) \
 && chown -R jenkins:jenkins /var/jenkins_home

# Copiar archivos de configuración personalizados
#COPY config.xml /var/jenkins_home/config.xml

# Configuración adicional (si es necesario)
# COPY otra_configuracion.xml /var/jenkins_home/otra_configuracion.xml

# Cambiar el límite de la memoria Java de Jenkins (opcional)
ENV JAVA_OPTS="-Xmx8g"

# Ejemplo: Cambiar la ubicación de la carpeta de inicio de Jenkins (opcional)
# ENV JENKINS_HOME=/path/to/custom/jenkins_home

# Definir el usuario jenkins para que tenga permisos de escritura en el sistema de archivos (opcional)
#USER root
#RUN chown -R jenkins:jenkins /var/jenkins_home
USER jenkins