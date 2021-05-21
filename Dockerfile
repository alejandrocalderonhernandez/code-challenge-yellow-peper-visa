FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
EXPOSE 8080
ADD ./target/code-challenge-0.0.1-SNAPSHOT.jar code-chaallenge.jar
ENTRYPOINT ["java", "-jar", "/code-chaallenge.jar"]