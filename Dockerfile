FROM openjdk:17-alpine
CMD ["./mvn", "claen", "package"]
ARG JAR_FILE_PATH=*.jar
COPY ${JAR_FILE_PATH} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]