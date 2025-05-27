## Docker: Cómo instalar Postgresql usando Docker Compose

Postgresql es un popular sistema de gestión de bases de datos relacionales que se utiliza ampliamente en la comunidad de
desarrollo.

## Crear archivo .env:

Crear el archivo .env en dentro del directorio database `/database/.env`

```dotenv
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_DB=postgres
```

### Ejecutar docker compose

```yaml
services:
  pg-db:
    build:
      context: pg
      dockerfile: Dockerfile
      target: dev
    container_name: pg-bd-app
    env_file:
      - .env
    ports:
      - "5432:5432"
    restart: always
    volumes:
      - ./volumes/16.3.20:/var/lib/postgresql/data
    networks:
      - bacsystem

networks:
  bacsystem:
    name: bacsystem-net-db
    driver: bridge
```

Iniciar la creación del contenedor y la red:

`- d : en modo independiente`

```bash
docker compose up -d
```

Eliminar la creación del contenedor y la red:

```bash
docker compose down
```

# Crear database y esquema

Paso 1: Conectar a PostgreSQL

Abre tu terminal y conéctate a PostgreSQL como superusuario (postgres):

```bash
psql -U postgres
```

Paso 2: Crear la Base de Datos

Crea una nueva base de datos. Puedes llamarla, por ejemplo, mi_workspace:

```bash
create database bs000000;
```

Paso 3: Conectarte a la Nueva Base de Datos

Ahora, conéctate a la base de datos que acabas de crear:

```bash
\c bs000000
```

Paso 4: Crear el Esquema

Crea un esquema dentro de la base de datos. Vamos a llamarlo data:

```bash
create schema bxcode;
```

Paso 5: Crear Nuevos Usuarios

Ahora, crea los usuarios que necesitarás. Aquí tienes ejemplos para crear dos usuarios: user1 y user2.
Reemplaza user1 y user2 con contraseñas seguras.

```bash
create user user1 with password 'user1';
create user user2 with password 'user2';
```

Paso 6: Asignar Permisos al Esquema

Ahora, asigna permisos a los usuarios sobre el esquema data. Esto les permitirá usar el esquema:

```bash
grant usage on schema bxcode to user1;

```

Paso 7: Crear Tablas en el Esquema

Ahora, crea una tabla dentro del esquema data. Por ejemplo, una tabla llamada `data`:

```bash
create table bxcode.data(id serial primary key);
```

Paso 8: Asignar Permisos a las Tablas

Otorga permisos a los usuarios para que puedan realizar operaciones en la tabla `data`:

```bash
grant select, insert, update, delete on table bxcode.data TO user1;
```

Paso 9: Proteger las Nuevas Tablas

Si planeas crear más tablas en el futuro, asegúrate de que los usuarios tengan permisos por defecto sobre nuevas tablas
en el esquema:

```bash
ALTER DEFAULT PRIVILEGES IN SCHEMA bxcode
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO user1;
```

Paso 10: Probar la Conexión

Prueba conectarte como uno de los nuevos usuarios para verificar que todo funcione correctamente. Usa el siguiente
comando en la terminal:

```bash
psql -U user1 -d bxcode
```

# Crear usuarios owner pára cada base de datos;

Paso 1: Crear directorio para el tablespace y cambiar de owner

```bash
mkdir /var/lib/postgresql/data/tablespace/tbs000000
chown postgres:postgres var/lib/postgresql/data/tablespaces/tbs000000
```

Paso 2: Conectarte a PostgreSQL

Abre tu terminal y conéctate a PostgreSQL como superusuario (postgres):

```bash
psql -U postgres
```

Paso 3: Crear tablespace en PostgreSQL

```bash
create tablespace tbs000000 location 'var/lib/postgresql/data/tablespaces/tbs000000'
```

Paso 4: Crear Usuarios

Primero, crea los usuarios. Aquí vamos a crear el usuario bs000000. Asegúrate de usar contraseñas seguras:

```bash
create user bs000000 with password 'bs000000';
```

Paso 5: Crear Bases de Datos

Crea una base de datos para cada usuario. Vamos a llamar a las bases de datos db000000:

```bash
create database db000000 owner bs000000 tablespace tbs000000;
```

Paso 6: Conectar a Cada Base de Datos y Crear Esquemas

Ahora, conecta a cada base de datos y crea un esquema para cada usuario.

Para bs000000:
Conéctate a la base de datos db000000:

```bash
\c db000000
```

Crea un esquema llamado sc000000:

```bash
create schema sc000000 authorization bs000000;
set search_path = "sc000000"
```

Paso 7: Asignar Permisos

Ahora asigna permisos a cada usuario sobre su esquema.

Para bs000000:
Conéctate a db000000 (si no estás ya en esa base de datos):

```bash
grant usage on schema sc000000 to bs000000;
grant create on schema sc000000 to bs000000;
grant select, insert, update, delete on all tables in schema sc000000 to bs000000;
```

Paso 8: Verificar Permisos

Para verificar que los permisos se han otorgado correctamente, puedes usar el siguiente comando para ver los permisos
del esquema:

```bash
SELECT nspname, pg_catalog.pg_get_userbyid(nspowner) AS owner
FROM pg_catalog.pg_namespace
WHERE nspname = 'sc000000';
```