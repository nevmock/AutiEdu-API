FROM openjdk:23-ea-jdk
EXPOSE 8080
ADD target/autiedu-api.jar autiedu-api.jar
ENTRYPOINT ["java","-jar","/autiedu-api.jar"]