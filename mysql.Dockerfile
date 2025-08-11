FROM mysql:latest

COPY ./src/mooc/main/resources/mooc.sql /docker/mysql/mysql-script.sql
COPY ./docker/mysql-entrypoint.sh /docker/

EXPOSE 3306
