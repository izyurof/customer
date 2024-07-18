FROM maven:3.9.8-amazoncorretto-21-al2023 AS build

COPY customer-service-app/src src
COPY customer-service-app/pom.xml pom.xml

RUN mvn -B dependency:resolve
RUN mvn -B dependency:resolve-plugins

RUN mvn clean package && \
    VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout) && \
    cp target/customer-service-app-${VERSION}.jar customer-service-app.jar

FROM openjdk:21-jdk-slim

RUN adduser --system customer-service

USER customer-service

WORKDIR /customer-service-app

COPY --from=build ./customer-service-app.jar ./customer-service-app.jar

EXPOSE 8080
EXPOSE 8043

ENTRYPOINT ["java", "-jar", "customer-service-app.jar"]
