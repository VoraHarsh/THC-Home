FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD out/artifacts/thc_home_jar/thc-home.jar thc-homemysqldocker.jar
ENTRYPOINT ["java", "-jar", "thc-homemysqldocker.jar"]