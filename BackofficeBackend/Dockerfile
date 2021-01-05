FROM openjdk:14

VOLUME /tmp
COPY target/backofficeapi-0.0.1-SNAPSHOT.jar app.jar
ARG name=TransportsysteemAPI
ENTRYPOINT ["java", "-jar","app.jar"]

EXPOSE 8080