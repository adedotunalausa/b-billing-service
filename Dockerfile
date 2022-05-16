FROM adoptopenjdk/openjdk11:alpine-jre

VOLUME /tmp

EXPOSE 8890

ADD target/*.jar app.jar

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]