
FROM maven:3.8.5-openjdk-18 AS builder
LABEL stage=builder
COPY pom.xml .
COPY src /src
RUN mvn dependency:go-offline
RUN mvn package -DskipTests

LABEL stage = final
FROM openjdk:21-oracle
COPY --from=builder /target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENV TZ="Europe/Ukraine"

HEALTHCHECK --interval=30s --timeout=3s \
CMD wget -qO- http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]

LABEL author="Piotr Czajka"