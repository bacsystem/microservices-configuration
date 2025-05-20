FROM ubuntu:25.04
LABEL authors="cbacilio"

# Instala el servidor SSH y abre el puerto 22
RUN apt-get update && apt-get install -y iputils-ping openssh-server \
    && rm -rf /var/lib/apt/lists/* \
    && mkdir /var/run/sshd

# Copiar el archivo sshd_config personalizado
COPY sshd_config /etc/ssh/sshd_config

# Expone el puerto 22 para SSH
EXPOSE 22

# Añade un usuario 'user1' y establece una contraseña
RUN useradd -m user1 && echo 'user1:user1' | chpasswd

# Copia la clave pública SSH al contenedor
#COPY .ssh/id_rsa.pub /root/.ssh/authorized_keys
COPY id_rsa.pub /root/.ssh/authorized_keys

# Asegura los permisos del directorio .ssh
RUN chmod 700 /root/.ssh && chmod 600 /root/.ssh/authorized_keys

# Inicia el servicio SSH al ejecutar el contenedor
CMD ["/usr/sbin/sshd", "-D"]