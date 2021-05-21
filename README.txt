Run using docker:

go into folder project

-  docker build -t code-challenge:latest .
-  docker run -p 8080:8080 code-challenge:latest

Run project 

go to the folder project

- mvn spring-boot:run

Documentation using openapi 3

 - go to next url http://localhost:8080/challenge/swagger-ui/index.html?configUrl=/challenge/api-docs/swagger-config
