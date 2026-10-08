FROM maven:8.14.4-jdk21-alpine AS builder

WORKDIR /jbuild

COPY . .

RUN gradle --no-daemon build


FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /jbuild/target/FluxCapacitor-1.0-SNAPSHOT.jar .

ENTRYPOINT [ "java", "-jar", "FluxCapacitor-1.0-SNAPSHOT.jar" ]
