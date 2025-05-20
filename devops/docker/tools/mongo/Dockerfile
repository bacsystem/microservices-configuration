# Imagen base de MongoDB
FROM mongo:8.0.8
LABEL authors="christian"
# Establece zona horaria
ENV TZ=America/Lima
# Instala herramientas útiles (curl, nano, etc.)
RUN apt-get update && apt-get install -y curl nano tzdata   && \
    ln -snf /usr/share/zoneinfo/"$TZ" /etc/localtime && echo "$TZ" > /etc/timezone && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* \

# Creamos un usuario no-root (opcional, Mongo se ejecuta como root por defecto)
RUN useradd -ms /bin/bash mongo_user

# Copia scripts de inicialización
COPY init-mongo.js /docker-entrypoint-initdb.d/
# Asigna permisos adecuados
RUN chown -R mongo_user:mongo_user /docker-entrypoint-initdb.d

# Usuario por defecto para el contenedor (opcional)
# USER mongo_user
# Puerto expuesto
EXPOSE 27017
ENTRYPOINT ["top", "-b"]