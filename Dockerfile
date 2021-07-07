FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/thc-home-0.0.1-SNAPSHOT.jar thc-homemysqldocker.jar
ENTRYPOINT ["java", "-jar", "thc-homemysqldocker.jar"]