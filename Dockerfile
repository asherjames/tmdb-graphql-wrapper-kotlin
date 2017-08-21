FROM openjdk:8

ADD . /opt/tmdb-gql-kotlin/

WORKDIR /opt/tmdb-gql-kotlin

RUN ./gradlew clean build

ENTRYPOINT ["/bin/sh", "-c", "java -jar build/libs/*.jar"]