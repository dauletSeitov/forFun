#!/bin/sh
#cd /srv
JAR=`ls digitalbank-core-*.jar`
DATA=`ls digitalbank-data-*.jar`
INTEGRATOR=`ls integrator-*.jar`
MANAGER=`ls manager-front-*.jar`
## Oracle
# DRIVER=ojdbc8.jar
## PostgreSQL
DRIVER=postgresql-42.1.1.jar
## MSSQL
# DRIVER=sqljdbc42.jar
JMX="-Xms256m -Xmx4096m"
#DEBUG="-Xdebug -Xrunjdwp:transport=dt_socket,address=8455,server=y,suspend=n"
DEBUG=
java -Dfile.encoding=UTF-8 $JMX $DEBUG -Dloader.path=$DRIVER,$DATA,$INTEGRATOR,$MANAGER -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5050 $JAR
