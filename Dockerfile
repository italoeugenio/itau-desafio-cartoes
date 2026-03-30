FROM maven:3.9.11 AS build
COPY src app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn clean install -DskipTests

LABEL maintainer="Itaú Desafio"
LABEL description="API de Elegibilidade de Cartões"

FROM eclipse-temurin:17
COPY --from=build /app/target/itau-desafio-cartoes-0.0.1-SNAPSHOT.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "app.jar"]