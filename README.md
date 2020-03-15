# jfrog-exercise

This project consists of retrieving finding the most popular and the 2nd most popular jar (binary) file in a repository that’s part of JFrog Artifactory.

This is a Spring BOOT REST API

Two apis are available:

1. Retrieves the two most downloaded Jar artifacts on a specific repository.
```shell script
curl -X GET "http://localhost:8080/api/result?repoName=jcenter-cache" -H "accept: */*"
```

2. Retrieves all jar artifacts on a specific repository.
```shell script
curl -X GET "http://localhost:8080/api/all?repoName=jcenter-cache" -H "accept: */*"
```

You can access to the Swagger api via this [url](http://localhost:8080/swagger-ui.html)

> http://localhost:8080/swagger-ui.html


# To build the docker image

```bash
docker build -t jfrog_image .
```

# To run the application

```bash
docker run -it -p 8080:8080 -e JFROG_URL=http://url -e JFROG_PASSWORD=password -e JFROG_LOGIN=admin jfrog_image
```

Make sure that you specify the credential to query the Artifactory server.
