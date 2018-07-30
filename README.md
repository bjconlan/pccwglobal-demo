# PCCW Global demo

The code presented here represents a coding task requested by PCCW Global as
part of the Java Developer recruitment process.


## Task 1

As a software engineer, I would like to implement a API server based on the
documentation:

```json:swagger.json
{
  "swagger": "2.0",
  "host": "localhost:8080",
  "info": {
    "title": "DCCPGlobal demo",
    "version": "0.0.1"
  },
  "basePath": "/",
  "tags": [
    {
      "name": "User",
      "description": "User Management"
    }
  ],
  "paths": {
    "/users": {
      "get": {
        "tags": [
          "User"
        ],
        "summary": "list all users",
        "operationId": "listUsingGET_1",
        "consumes": [
          "application/json"
        ],
        "produces": [
          "application/json;charset=UTF-8"
        ],
        "parameters": [
          {
            "name": "page",
            "in": "query",
            "description": "page",
            "required": false,
            "type": "integer",
            "format": "int32"
          },
          {
            "name": "size",
            "in": "query",
            "description": "size",
            "required": false,
            "type": "integer",
            "format": "int32"
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "schema": {
              "type": "array",
              "items": {
                "$ref": "#/definitions/User"
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "User"
        ],
        "summary": "Register a new user",
        "operationId": "createUsingPUT",
        "consumes": [
          "application/json"
        ],
        "produces": [
          "application/json;charset=UTF-8"
        ],
        "parameters": [
          {
            "in": "body",
            "name": "request",
            "description": "request",
            "required": true,
            "schema": {
              "$ref": "#/definitions/CreateUserRequest"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "schema": {
              "$ref": "#/definitions/User"
            }
          }
        }
      }
    },
    "/users/{id}": {
      "get": {
        "tags": [
          "User"
        ],
        "summary": "Find a user via id",
        "operationId": "getUsingGET",
        "consumes": [
          "application/json"
        ],
        "produces": [
          "application/json;charset=UTF-8"
        ],
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "description": "id",
            "required": true,
            "type": "string"
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "schema": {
              "$ref": "#/definitions/User"
            }
          }
        }
      },
      "post": {
        "tags": [
          "User"
        ],
        "summary": "Update a user",
        "operationId": "updateUsingPOST_1",
        "consumes": [
          "application/json"
        ],
        "produces": [
          "application/json;charset=UTF-8"
        ],
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "description": "id",
            "required": true,
            "type": "string"
          },
          {
            "in": "body",
            "name": "request",
            "description": "request",
            "required": true,
            "schema": {
              "$ref": "#/definitions/UpdateUserRequest"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "schema": {
              "$ref": "#/definitions/User"
            }
          }
        }
      },
      "delete": {
        "tags": [
          "User"
        ],
        "summary": "Delete a user",
        "operationId": "deleteUsingDELETE",
        "consumes": [
          "application/json"
        ],
        "produces": [
          "application/json;charset=UTF-8"
        ],
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "description": "id",
            "required": true,
            "type": "string"
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "schema": {
              "$ref": "#/definitions/User"
            }
          }
        }
      }
    }
  },
  "definitions": {
    "CreateUserRequest": {
      "type": "object",
      "required": [
        "email",
        "name",
        "password",
        "username"
      ],
      "properties": {
        "email": {
          "type": "string"
        },
        "name": {
          "type": "string"
        },
        "password": {
          "type": "string"
        },
        "username": {
          "type": "string"
        }
      }
    },
    "UpdateUserRequest": {
      "type": "object",
      "properties": {
        "email": {
          "type": "string"
        },
        "name": {
          "type": "string"
        },
        "password": {
          "type": "string"
        },
        "username": {
          "type": "string"
        }
      }
    },
    "User": {
      "type": "object",
      "properties": {
        "email": {
          "type": "string"
        },
        "id": {
          "type": "string"
        },
        "name": {
          "type": "string"
        },
        "password": {
          "type": "string"
        },
        "username": {
          "type": "string"
        }
      }
    }
  }
}
```

*Note* the swagger definition document was edited from the original to correct
schema outlined when running the code though the [Swagger editor]
(http://editor.swagger.io). This also includes the remove of the 'id' parameter
on the GET:/users url.

> Key requirements
  1. Package management: [Maven](https://maven.apache.org/)
  2. Application Framework: [Spring Boot](https://projects.spring.io/spring-boot/)
  3. Test framework: [Junit4](http://junit.org/junit4/)
  4. Database: H2 for unit test, MYSQL for production

## Task 2

As a software engineer, I would like to write a standalone application to
test the API server completed in Task 1

> Key requirements
  1. Package management: [Maven](https://maven.apache.org/)
  2. Application Framework: [Spring Boot](https://projects.spring.io/spring-boot/)
  3. Test report can be xml or json format (only need to support one of them)
  4. Performance test cases are a big bonus

# Development Execution

For implementation I've decided to use the most current stable versions of both
spring boot (2.0.3) but use the older java version (8/1.8) due to minor issues
regarding classpath loading (java.se.ee) and 'Illegal reflective  access'
warnings when using java 9 & 10. (these can be overcome using command line flags
and the introduction of the appropriate maven dependencies but decided it keep it
simple)

As the requirements of the task are quite small I've implemented the application
using a a fairly familiar package structure and focused on brevity and comments
over completeness (that being the omission of explicit request/response payloads,
domain/model conversion & business logic layers) making the application easier to
read for the evaluator. (Naturally in a larger application design things would be
scoped as appropriate).

The application also doesn't use well versioned schemas as such it simply creates
the schema on initial run (a more maintainable solution would be to make use of
the liquibase/flyway functionality but is beyond the scope of this task)

Additional spring boot dependencies used:
- [Spring Web](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)
- [Spring Data JPA](https://projects.spring.io/spring-data-jpa/)
- [Spring Boot Actuator](https://github.com/spring-projects/spring-boot/tree/v2.0.3.RELEASE/spring-boot-project/spring-boot-actuator).

*NOTE* these are all included by the 'Spring boot starters' hopefully adhering
to the outlined requirements of the Application Framework.

## Setup

The application requires that a mysql database is running and the database
'pccwglobal_demo' exists. This can be done by logging into your mysql instance
and performing a `CREATE DATABASE pccwglobal_demo`.

The application will create the appropriate table structure when it is first run.

It is also required that you update the application.properties file with the
appropriate `spring.datasource.username` and `spring.datasource.password` values
for your database.

## Metrics

Metrics actuator web endpoints have been enabled allowing the user to view
micrometer based stats for particular routes/requests. Details of how this works
can be found in the [Spring boot reference](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-metrics.html#production-ready-metrics-spring-mvc)

After the endpoint has been hit it should be visible in the [metrics listing]
(http://localhost:8080/actuator/metrics/) where you can look at the specific
request details by simply appending the name to the url. The following metric
endpoints are provided:

- GET: /users = http://localhost:8080/actuator/metrics/web.rest.user.findAll
- GET: /users/{id} = http://localhost:8080/actuator/metrics/web.rest.user.findById
- PUT: /users = http://localhost:8080/actuator/metrics/web.rest.user.create
- POST: /users/{id} = http://localhost:8080/actuator/metrics/web.rest.user.update
- DELETE: /users/{id} = http://localhost:8080/actuator/metrics/web.rest.user.delete

NOTE that no caching has been implemented (unless time permits) through e-tagging
     or otherwise (jsr-107) this would perhaps be the next step in optimizing the
     performance of the application.

The generated reports submitted with this assessment were generated from the maven
surefire plugin and can be re-executed simply by performing executing the `mvnw
test` command (generated documents located in the target/surefire-reports folder).