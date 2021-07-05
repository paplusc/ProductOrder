FROM postgres
ENV POSTGRES_PASSWORD 1234
ENV POSTGRES_DB pcremades
COPY initDB.sql /docker-entrypoint-initdb.d/