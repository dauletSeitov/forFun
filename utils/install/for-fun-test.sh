#!/bin/sh
# cd /srv
JAR=`ls for-fun-*.jar`

## Oracle
# DRIVER=ojdbc8.jar
## PostgreSQL
DRIVER=postgresql-42.1.1.jar
## MSSQL
# DRIVER=sqljdbc42.jar
JMX="-Xms256m -Xmx512m"
#DEBUG="-Xdebug -Xrunjdwp:transport=dt_socket,address=8455,server=y,suspend=n"
DEBUG=
java -Dfile.encoding=UTF-8 $JMX $DEBUG  -jar $JAR
