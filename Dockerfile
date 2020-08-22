FROM openjdk:8
ADD target/movie_rest-0.0.1-SNAPSHOT.jar movie_rest.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","movie_rest.jar"]
