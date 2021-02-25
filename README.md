# shortUrlService
This service allows users to submit long url and retrieve short url links in return.

The application uses the base64 conversion logic to unique generate short url string mapped to a unique identifier on the service database.

The application is a java application with 2 major service endpoints.

1. GetShortUrl: To accept the full url string on the path "/api/v1/getshorturl" POST method.
2. Base endpoint: "/" (GET method) which process the generated short url link.

The application can be downloaded and build as a java service using the generated jar file or run on on a docker container using the DockerFile provision on the source code.

The application allows short base url configuration on the application.properties file i.e. 

#This represent the custom short dormain base url e.g. https://eofgp.ly/
app.serverDomainPath=http://localhost:8080/

The unit test code (on UrlShortenerTest) contains a sample request and expected response of the service. 

http://localhost:8080/v2/api-docs API documentation Url



