# Use an official MySQL image as the base
FROM mysql:latest

# Set the root password for MySQL
ENV MYSQL_ROOT_PASSWORD=ahmedsoltani

# Expose port 3306 for MySQL
EXPOSE 3306

# Copy custom configuration file, if needed
# COPY my.cnf /etc/mysql/my.cnf

# (Optional) Create a new database and user
ENV MYSQL_DATABASE=pfe
#ENV MYSQL_USER=root
#ENV MYSQL_PASSWORD=ahmedsoltani
# ADD init.sql /docker-entrypoint-initdb.d

# Start MySQL server when the container launches
CMD ["mysqld"]
