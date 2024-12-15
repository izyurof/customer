FROM maven:3.9.9-amazoncorretto-21-al2023 AS build

WORKDIR /build

COPY customer-service-app/src customer-service-app/src
COPY customer-service-app/pom.xml customer-service-app/pom.xml
COPY customer-service-app/logstash-configs customer-service-app/logstash-configs
COPY openapi openapi

COPY e2e/pom.xml e2e/pom.xml
COPY pom.xml .

RUN mvn -B -pl customer-service-app dependency:resolve
RUN mvn -B -pl customer-service-app dependency:resolve-plugins

RUN mvn -B -pl customer-service-app clean package -Dcheckstyle.skip && \
    ARTIFACT_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout) && \
    cp customer-service-app/target/customer-service-app-${ARTIFACT_VERSION}.jar customer-service-app.jar

FROM openjdk:21-jdk-slim

RUN adduser --system customer-service

USER customer-service

WORKDIR /customer-service-app

COPY --from=build build/customer-service-app.jar customer-service-app.jar
COPY --from=build build/customer-service-app/logstash-configs logstash-configs

EXPOSE 8080
EXPOSE 8043

ENTRYPOINT ["java", "-jar", "customer-service-app.jar"]
