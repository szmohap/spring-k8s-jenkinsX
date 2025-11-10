FROM openjdk:17
EXPOSE 8087
ADD target/spring-k8s-jenkinsx.jar spring-k8s-jenkinsx.jar
ENTRYPOINT ["java","-jar","/spring-k8s-jenkinsx.jar"]