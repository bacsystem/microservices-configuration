#/apps /usr /lib
###### DOCKERFILE ######
FROM postgres:alpine3.21 AS base
USER root
LABEL autor="Christian Bacilio De La Cruz"
LABEL description="database postgres"
LABEL version="latest"

##### ADD SCRIPT ####

FROM base AS dev

COPY postgresql.conf /data/postgresql/postgresql.conf

EXPOSE 5432

ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["postgres","-c", "wal_level=logical", "-c", "max_wal_senders=1" , "-c", "max_replication_slots=1" ]