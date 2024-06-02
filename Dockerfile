FROM amazoncorretto:17
COPY /target/core-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar", "/usr/local/lib/demo.jar"]

