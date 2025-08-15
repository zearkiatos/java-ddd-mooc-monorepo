# Use specific version instead of latest for security and reproducibility
FROM mysql:8.0.39

RUN groupadd -r dbgroup && useradd -r dbuser -g dbgroup

# Copy security configuration
COPY ./docker/mysql-security.cnf /etc/mysql/conf.d/security.cnf

# Copy SQL scripts for initialization
COPY ./src/mooc/main/resources/mooc.sql /docker-entrypoint-initdb.d/mooc.sql
COPY ./docker/mysql-entrypoint.sh /docker/

# Set proper permissions
RUN chmod 755 /docker/mysql-entrypoint.sh \
    && chmod 644 /docker-entrypoint-initdb.d/mooc.sql \
    && chmod 644 /etc/mysql/conf.d/security.cnf

RUN chown -R dbuser:dbgroup /var/lib/mysql /var/run/mysqld /var/log

USER dbuser

EXPOSE 3306

# Health check for container monitoring
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
    CMD mysqladmin ping -h localhost -u root -p${MYSQL_ROOT_PASSWORD} || exit 1
