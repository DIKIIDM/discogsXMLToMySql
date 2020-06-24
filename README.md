# What is it?
This is java program for importing the discogs data dumps to mySql database.

## Options 
* input: -f / --pathToXMLFolder = path to the directory, containing XML files;
* input: -p / --databaseUserPassword = database user password;
* input: -u / --databaseUsername = database username;
* input: -url / --databaseURL = database url.

## Examples
    java -jar discogsXMLToMySql.jar -f C:\discogs -u root -p root -url "jdbc:mysql://localhost/discogs"
    java -jar discogsXMLToMySql.jar -f C:\discogs -u root -p root -url "jdbc:mysql://localhost/discogs?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC&rewriteBatchedStatements=true"

## How to use it?
Steps to import the data-dumps into mySql:
1. download the discogs data dumps from http://www.discogs.com/data/;
2. unzip the dumps to some directory;
3. import the database schema by executiong discogs_schema.sql:  
    `mysql -u root -p`  
    `create database discogs;`  
    `use discogs;`  
    `source /path/to/discogs_schema.sql;`  
4. execute jar file:  
`java -jar discogsXMLToMySql.jar -f /path/to/xml/directory -u 'db username' -p 'db user password' -url "jdbc:mysql://localhost/discogs"`

## Schema
![diagram](https://user-images.githubusercontent.com/8464572/85536904-2c7dc180-b61c-11ea-9387-fcaf9e6637d6.png)
