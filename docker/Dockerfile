FROM openjdk:8

ADD libs/ /opt/tmdb-gql-kotlin/

ADD server.yml /opt/tmdb-gql-kotlin/

WORKDIR /opt/tmdb-gql-kotlin

EXPOSE 8080

CMD ["java", "-jar", "tmdb-gql-kotlin-0.0.1-all.jar", "server", "server.yml"]