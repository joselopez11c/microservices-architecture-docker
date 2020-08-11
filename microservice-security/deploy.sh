#!/bin/bash
echo =================================================
echo Generate JAR
echo =================================================
mvn clean package -DskipTests
echo =================================================
echo BUILD and PUSH Proyect to Docker
echo =================================================
docker build -t jilopezc/microservice-security .
docker push jilopezc/microservice-security
echo =================================================
echo End Process
echo =================================================
