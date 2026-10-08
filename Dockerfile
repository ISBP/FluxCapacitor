FROM maven:3.10.0-eclipse-temurin-17 AS builder

WORKDIR /jbuild

COPY . .

RUN mvn clean install


FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /jbuild/target/FluxCapacitor-1.0-SNAPSHOT.jar .

ENTRYPOINT [ "java", "-jar", "FluxCapacitor-1.0-SNAPSHOT.jar" ]
