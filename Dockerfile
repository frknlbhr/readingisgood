FROM openjdk:11-jre-slim
ADD target/readingisgood-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
