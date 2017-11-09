# contacts_webapp
Web application for contact accounting


Before start:


SQL file for creating database is allocated in directory src/main/sql. Its name - create_mysql_db.sql

If it's necessary to create database with some values, use file create_mysql_db_with_values.sql in the same directory

Database settings are allocated in resources/db.properties. Here you can define databse user and password



Set up your email properties in file src/resources/smtp.properties. Here you should define your email and its password


To configure Tomcat you need to define artifact for deployment. This artifact is allocated in target directory with name contacts_webapp. Its name contacts_webapp.war
