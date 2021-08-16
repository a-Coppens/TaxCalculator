FROM openjdk:16
ADD target/CodingTest-0.0.1-SNAPSHOT.jar coding-test-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","coding-test-app.jar"]
COPY . .