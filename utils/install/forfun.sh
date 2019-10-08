#!/bin/sh
# cd /srv
JAR=`ls forfun-*.jar`

## Oracle
# DRIVER=ojdbc8.jar
## PostgreSQL
DRIVER=postgresql-42.1.1.jar
## MSSQL
# DRIVER=sqljdbc42.jar
JMX="-Xms256m -Xmx512m"
#DEBUG="-Xdebug -Xrunjdwp:transport=dt_socket,address=8455,server=y,suspend=n"
#DEBUG=java -Dfile.encoding=UTF-8 $JMX $DEBUG -Dloader.path=$DRIVER,$DATA,$INTEGRATOR,$MANAGER -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5050 $JAR
java -Dfile.encoding=UTF-8 $JMX $DEBUG  -jar $JAR
