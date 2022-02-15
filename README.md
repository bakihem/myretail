# Read Me First
My Retail API

Table of Contents:
- [Description/Overview](#description-overview)
- [Local Setup](#local-setup)
- [Endpoints](#endpoints)
- [Testing](#testing)

## Description /Overview
Create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.
Build an application that performs the following actions:
- Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 

## Local Setup

- Clone project from git link in any preferred IDE (Intellij or Eclipse)
- Install Maven and Java to test the Api
- Install MongoDb for database
- Install postman tool to test the rest api endpoint
- Run 'mvn clean install' command on terminal to build the project
- To Run the Application use 'mvn spring-boot:run' command on terminal.
- Test the below endpoints in postman tool.

## Endpoints
- http:/localhost:8080/products/{id} (It is a GET Method which provides a product data as JSON where {id} parameter is an Integer)
- http:/localhost:8080/products/{id} (It is a PUT Method which saves product price of the provided {id} parameter )

## Testing

- Test cases are created at /src/test/java directory
- Run individual test cases by right-clicking on the test methods and select Run As -> Junit test.