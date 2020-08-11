FROM openjdk:13
VOLUME /tmp
EXPOSE 8010
ADD ./target/microservice-security-0.0.1-SNAPSHOT.jar micro-security.jar
ENTRYPOINT ["java","-jar","/micro-security.jar"]