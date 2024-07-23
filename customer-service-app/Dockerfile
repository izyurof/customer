FROM maven:3.9.8-amazoncorretto-21-al2023 AS build

WORKDIR /build

COPY customer-service-app/src customer-service-app/src
COPY customer-service-app/pom.xml customer-service-app/pom.xml
COPY pom.xml .

RUN mvn -B dependency:resolve
RUN mvn -B dependency:resolve-plugins

RUN mvn -B clean package -Dcheckstyle.skip && \
    ARTIFACT_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout) && \
    cp customer-service-app/target/customer-service-app-${ARTIFACT_VERSION}.jar customer-service-app.jar

FROM openjdk:21-jdk-slim

RUN adduser --system customer-service

USER customer-service

WORKDIR /customer-service-app

COPY --from=build build/customer-service-app.jar customer-service-app.jar

EXPOSE 8080
EXPOSE 8043

ENTRYPOINT ["java", "-jar", "customer-service-app.jar"]
