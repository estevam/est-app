FROM openjdk:21
VOLUME /app
EXPOSE 8080
ARG JAR_FILE=target/est-app.jar
ADD ${JAR_FILE} est-app.jar
ENTRYPOINT ["java","-jar","/est-blog.jar"]