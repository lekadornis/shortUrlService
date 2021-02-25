FROM openjdk:13-jdk-alpine
COPY --from=build /usr/src/app/target/urlShortener-0.0.1-SNAPSHOT.jar /usr/src/app/urlShortener-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/src/app/urlShortener-0.0.1-SNAPSHOT.jar"]