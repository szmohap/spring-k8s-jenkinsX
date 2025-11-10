FROM openjdk:17
EXPOSE 8087
ADD target/spring-k8s-jenkinsX.jar spring-k8s-jenkinsX.jar
ENTRYPOINT ["java","-jar","/spring-k8s-jenkinsX.jar"]