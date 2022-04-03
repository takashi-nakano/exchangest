# syntax=docker/dockerfile:1

FROM openjdk:11
WORKDIR /usr/src/exchangest
COPY gradle/ gradle
COPY gradlew build.gradle ./
RUN ./gradlew dependencies

COPY src ./src

CMD ["./gradlew", "bootRun"]