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
schema errors outlined when running the code though the [Swagger editor](http://editor.swagger.io).
This also includes the removal of the 'id' parameter on the GET:/users endpoint.

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

For implementation I've decided to use the most current stable version of spring
boot (2.0.3) but use the older java version (8/1.8) due to minor issues regarding
classpath loading (java.se.ee) and 'Illegal reflective access' warnings when
using java 9 & 10. (These can be overcome using command line flags and the
introduction of the appropriate maven dependencies but decided it keep it simple)

As the requirements of the task are quite small I've implemented the application
using a familiar package structure and focused on brevity and comments over
completeness (that being the omission of explicit request/response payloads,
domain/model conversion & business logic layers) making the application easier to
read for the evaluator. (Naturally in a larger application design things would be
scoped as appropriate).

The application also doesn't use well versioned schemas as such it simply creates
the schema on initial run (a more maintainable solution would be to make use of
the liquibase/flyway functionality but is beyond the scope of this task)

Additional spring boot dependencies used:
- [Spring Web](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)
- [Spring Data JPA](https://projects.spring.io/spring-data-jpa/)
- [Spring Boot Actuator](https://github.com/spring-projects/spring-boot/tree/v2.0.3.RELEASE/spring-boot-project/spring-boot-actuator)

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

## Performance Metrics

Metrics actuator web endpoints have been enabled allowing the user to view
micrometer based stats for particular routes/requests. Details of how this works
can be found in the [Spring boot reference](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-metrics.html#production-ready-metrics-spring-mvc)

After the endpoint has been hit it should be visible in the [metrics listing](http://localhost:8080/actuator/metrics/)
where you can look at the specific request details by simply appending the name
to the url. The following metric endpoints are provided:

- GET: /users = http://localhost:8080/actuator/metrics/web.rest.user.findAll
- GET: /users/{id} = http://localhost:8080/actuator/metrics/web.rest.user.findById
- PUT: /users = http://localhost:8080/actuator/metrics/web.rest.user.create
- POST: /users/{id} = http://localhost:8080/actuator/metrics/web.rest.user.update
- DELETE: /users/{id} = http://localhost:8080/actuator/metrics/web.rest.user.delete

NOTE that no caching has been implemented (unless time permits) through e-tagging
     or otherwise (jsr-107) this would perhaps be the next step in optimizing the
     performance of the application. (along with the inclusion of JMeter tests)

## JUnit Report

The JUnit reports submitted with this assessment were generated from the maven
surefire plugin and can be re-executed simply by performing executing the `mvnw
test` command (generated documents located in the target/surefire-reports folder).

The output of said report is included below for convenience

```xml:TEST-com.github.bjconlan.pccwglobaldemo.web.UserControllerTests.xml
<?xml version="1.0" encoding="UTF-8"?>
<testsuite xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="https://maven.apache.org/surefire/maven-surefire-plugin/xsd/surefire-test-report.xsd" name="com.github.bjconlan.pccwglobaldemo.web.UserControllerTests" time="6.404" tests="5" errors="0" skipped="0" failures="0">
  <properties>
    <property name="sun.desktop" value="windows"/>
    <property name="awt.toolkit" value="sun.awt.windows.WToolkit"/>
    <property name="file.encoding.pkg" value="sun.io"/>
    <property name="java.specification.version" value="1.8"/>
    <property name="sun.cpu.isalist" value="amd64"/>
    <property name="sun.jnu.encoding" value="Cp1252"/>
    <property name="java.class.path" value="C:\Users\bjcon\Workspace\pccwglobal-demo\target\test-classes;C:\Users\bjcon\Workspace\pccwglobal-demo\target\classes;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-web\2.0.3.RELEASE\spring-boot-starter-web-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter\2.0.3.RELEASE\spring-boot-starter-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-logging\2.0.3.RELEASE\spring-boot-starter-logging-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;C:\Users\bjcon\.m2\repository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;C:\Users\bjcon\.m2\repository\org\apache\logging\log4j\log4j-to-slf4j\2.10.0\log4j-to-slf4j-2.10.0.jar;C:\Users\bjcon\.m2\repository\org\apache\logging\log4j\log4j-api\2.10.0\log4j-api-2.10.0.jar;C:\Users\bjcon\.m2\repository\org\slf4j\jul-to-slf4j\1.7.25\jul-to-slf4j-1.7.25.jar;C:\Users\bjcon\.m2\repository\javax\annotation\javax.annotation-api\1.3.2\javax.annotation-api-1.3.2.jar;C:\Users\bjcon\.m2\repository\org\yaml\snakeyaml\1.19\snakeyaml-1.19.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-json\2.0.3.RELEASE\spring-boot-starter-json-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.9.6\jackson-databind-2.9.6.jar;C:\Users\bjcon\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.9.0\jackson-annotations-2.9.0.jar;C:\Users\bjcon\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.9.6\jackson-core-2.9.6.jar;C:\Users\bjcon\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.9.6\jackson-datatype-jdk8-2.9.6.jar;C:\Users\bjcon\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.9.6\jackson-datatype-jsr310-2.9.6.jar;C:\Users\bjcon\.m2\repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.9.6\jackson-module-parameter-names-2.9.6.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-tomcat\2.0.3.RELEASE\spring-boot-starter-tomcat-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\apache\tomcat\embed\tomcat-embed-core\8.5.31\tomcat-embed-core-8.5.31.jar;C:\Users\bjcon\.m2\repository\org\apache\tomcat\embed\tomcat-embed-el\8.5.31\tomcat-embed-el-8.5.31.jar;C:\Users\bjcon\.m2\repository\org\apache\tomcat\embed\tomcat-embed-websocket\8.5.31\tomcat-embed-websocket-8.5.31.jar;C:\Users\bjcon\.m2\repository\org\hibernate\validator\hibernate-validator\6.0.10.Final\hibernate-validator-6.0.10.Final.jar;C:\Users\bjcon\.m2\repository\javax\validation\validation-api\2.0.1.Final\validation-api-2.0.1.Final.jar;C:\Users\bjcon\.m2\repository\org\jboss\logging\jboss-logging\3.3.2.Final\jboss-logging-3.3.2.Final.jar;C:\Users\bjcon\.m2\repository\com\fasterxml\classmate\1.3.4\classmate-1.3.4.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-web\5.0.7.RELEASE\spring-web-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-beans\5.0.7.RELEASE\spring-beans-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-webmvc\5.0.7.RELEASE\spring-webmvc-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-aop\5.0.7.RELEASE\spring-aop-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-context\5.0.7.RELEASE\spring-context-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-expression\5.0.7.RELEASE\spring-expression-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-data-jpa\2.0.3.RELEASE\spring-boot-starter-data-jpa-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-aop\2.0.3.RELEASE\spring-boot-starter-aop-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\aspectj\aspectjweaver\1.8.13\aspectjweaver-1.8.13.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-jdbc\2.0.3.RELEASE\spring-boot-starter-jdbc-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\com\zaxxer\HikariCP\2.7.9\HikariCP-2.7.9.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-jdbc\5.0.7.RELEASE\spring-jdbc-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\hibernate\hibernate-core\5.2.17.Final\hibernate-core-5.2.17.Final.jar;C:\Users\bjcon\.m2\repository\org\hibernate\javax\persistence\hibernate-jpa-2.1-api\1.0.2.Final\hibernate-jpa-2.1-api-1.0.2.Final.jar;C:\Users\bjcon\.m2\repository\org\javassist\javassist\3.22.0-GA\javassist-3.22.0-GA.jar;C:\Users\bjcon\.m2\repository\antlr\antlr\2.7.7\antlr-2.7.7.jar;C:\Users\bjcon\.m2\repository\org\jboss\jandex\2.0.3.Final\jandex-2.0.3.Final.jar;C:\Users\bjcon\.m2\repository\dom4j\dom4j\1.6.1\dom4j-1.6.1.jar;C:\Users\bjcon\.m2\repository\org\hibernate\common\hibernate-commons-annotations\5.0.1.Final\hibernate-commons-annotations-5.0.1.Final.jar;C:\Users\bjcon\.m2\repository\javax\transaction\javax.transaction-api\1.2\javax.transaction-api-1.2.jar;C:\Users\bjcon\.m2\repository\org\springframework\data\spring-data-jpa\2.0.8.RELEASE\spring-data-jpa-2.0.8.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\data\spring-data-commons\2.0.8.RELEASE\spring-data-commons-2.0.8.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-orm\5.0.7.RELEASE\spring-orm-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-tx\5.0.7.RELEASE\spring-tx-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-aspects\5.0.7.RELEASE\spring-aspects-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-actuator\2.0.3.RELEASE\spring-boot-starter-actuator-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-actuator-autoconfigure\2.0.3.RELEASE\spring-boot-actuator-autoconfigure-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-actuator\2.0.3.RELEASE\spring-boot-actuator-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\io\micrometer\micrometer-core\1.0.5\micrometer-core-1.0.5.jar;C:\Users\bjcon\.m2\repository\org\hdrhistogram\HdrHistogram\2.1.10\HdrHistogram-2.1.10.jar;C:\Users\bjcon\.m2\repository\org\latencyutils\LatencyUtils\2.0.3\LatencyUtils-2.0.3.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-devtools\2.0.3.RELEASE\spring-boot-devtools-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot\2.0.3.RELEASE\spring-boot-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-autoconfigure\2.0.3.RELEASE\spring-boot-autoconfigure-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\mysql\mysql-connector-java\5.1.46\mysql-connector-java-5.1.46.jar;C:\Users\bjcon\.m2\repository\com\h2database\h2\1.4.197\h2-1.4.197.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-test\2.0.3.RELEASE\spring-boot-starter-test-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-test\2.0.3.RELEASE\spring-boot-test-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-test-autoconfigure\2.0.3.RELEASE\spring-boot-test-autoconfigure-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\com\jayway\jsonpath\json-path\2.4.0\json-path-2.4.0.jar;C:\Users\bjcon\.m2\repository\net\minidev\json-smart\2.3\json-smart-2.3.jar;C:\Users\bjcon\.m2\repository\net\minidev\accessors-smart\1.2\accessors-smart-1.2.jar;C:\Users\bjcon\.m2\repository\org\ow2\asm\asm\5.0.4\asm-5.0.4.jar;C:\Users\bjcon\.m2\repository\junit\junit\4.12\junit-4.12.jar;C:\Users\bjcon\.m2\repository\org\assertj\assertj-core\3.9.1\assertj-core-3.9.1.jar;C:\Users\bjcon\.m2\repository\org\mockito\mockito-core\2.15.0\mockito-core-2.15.0.jar;C:\Users\bjcon\.m2\repository\net\bytebuddy\byte-buddy\1.7.11\byte-buddy-1.7.11.jar;C:\Users\bjcon\.m2\repository\net\bytebuddy\byte-buddy-agent\1.7.11\byte-buddy-agent-1.7.11.jar;C:\Users\bjcon\.m2\repository\org\objenesis\objenesis\2.6\objenesis-2.6.jar;C:\Users\bjcon\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;C:\Users\bjcon\.m2\repository\org\hamcrest\hamcrest-library\1.3\hamcrest-library-1.3.jar;C:\Users\bjcon\.m2\repository\org\skyscreamer\jsonassert\1.5.0\jsonassert-1.5.0.jar;C:\Users\bjcon\.m2\repository\com\vaadin\external\google\android-json\0.0.20131108.vaadin1\android-json-0.0.20131108.vaadin1.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-core\5.0.7.RELEASE\spring-core-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-jcl\5.0.7.RELEASE\spring-jcl-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-test\5.0.7.RELEASE\spring-test-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\xmlunit\xmlunit-core\2.5.1\xmlunit-core-2.5.1.jar;C:\Users\bjcon\.m2\repository\io\projectreactor\reactor-test\3.1.8.RELEASE\reactor-test-3.1.8.RELEASE.jar;C:\Users\bjcon\.m2\repository\io\projectreactor\reactor-core\3.1.8.RELEASE\reactor-core-3.1.8.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\reactivestreams\reactive-streams\1.0.2\reactive-streams-1.0.2.jar;"/>
    <property name="java.vm.vendor" value="Oracle Corporation"/>
    <property name="sun.arch.data.model" value="64"/>
    <property name="user.variant" value=""/>
    <property name="java.vendor.url" value="http://java.oracle.com/"/>
    <property name="user.timezone" value=""/>
    <property name="java.vm.specification.version" value="1.8"/>
    <property name="os.name" value="Windows 10"/>
    <property name="user.country" value="AU"/>
    <property name="sun.java.launcher" value="SUN_STANDARD"/>
    <property name="sun.boot.library.path" value="C:\Program Files\Java\jdk1.8.0_161\jre\bin"/>
    <property name="sun.java.command" value="C:\Users\bjcon\AppData\Local\Temp\surefire6590975121147800983\surefirebooter7348209081850986674.jar C:\Users\bjcon\AppData\Local\Temp\surefire6590975121147800983 2018-07-30T13-25-45_496-jvmRun1 surefire951189494678635953tmp surefire_0535414371492245612tmp"/>
    <property name="surefire.test.class.path" value="C:\Users\bjcon\Workspace\pccwglobal-demo\target\test-classes;C:\Users\bjcon\Workspace\pccwglobal-demo\target\classes;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-web\2.0.3.RELEASE\spring-boot-starter-web-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter\2.0.3.RELEASE\spring-boot-starter-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-logging\2.0.3.RELEASE\spring-boot-starter-logging-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;C:\Users\bjcon\.m2\repository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;C:\Users\bjcon\.m2\repository\org\apache\logging\log4j\log4j-to-slf4j\2.10.0\log4j-to-slf4j-2.10.0.jar;C:\Users\bjcon\.m2\repository\org\apache\logging\log4j\log4j-api\2.10.0\log4j-api-2.10.0.jar;C:\Users\bjcon\.m2\repository\org\slf4j\jul-to-slf4j\1.7.25\jul-to-slf4j-1.7.25.jar;C:\Users\bjcon\.m2\repository\javax\annotation\javax.annotation-api\1.3.2\javax.annotation-api-1.3.2.jar;C:\Users\bjcon\.m2\repository\org\yaml\snakeyaml\1.19\snakeyaml-1.19.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-json\2.0.3.RELEASE\spring-boot-starter-json-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.9.6\jackson-databind-2.9.6.jar;C:\Users\bjcon\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.9.0\jackson-annotations-2.9.0.jar;C:\Users\bjcon\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.9.6\jackson-core-2.9.6.jar;C:\Users\bjcon\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.9.6\jackson-datatype-jdk8-2.9.6.jar;C:\Users\bjcon\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.9.6\jackson-datatype-jsr310-2.9.6.jar;C:\Users\bjcon\.m2\repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.9.6\jackson-module-parameter-names-2.9.6.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-tomcat\2.0.3.RELEASE\spring-boot-starter-tomcat-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\apache\tomcat\embed\tomcat-embed-core\8.5.31\tomcat-embed-core-8.5.31.jar;C:\Users\bjcon\.m2\repository\org\apache\tomcat\embed\tomcat-embed-el\8.5.31\tomcat-embed-el-8.5.31.jar;C:\Users\bjcon\.m2\repository\org\apache\tomcat\embed\tomcat-embed-websocket\8.5.31\tomcat-embed-websocket-8.5.31.jar;C:\Users\bjcon\.m2\repository\org\hibernate\validator\hibernate-validator\6.0.10.Final\hibernate-validator-6.0.10.Final.jar;C:\Users\bjcon\.m2\repository\javax\validation\validation-api\2.0.1.Final\validation-api-2.0.1.Final.jar;C:\Users\bjcon\.m2\repository\org\jboss\logging\jboss-logging\3.3.2.Final\jboss-logging-3.3.2.Final.jar;C:\Users\bjcon\.m2\repository\com\fasterxml\classmate\1.3.4\classmate-1.3.4.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-web\5.0.7.RELEASE\spring-web-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-beans\5.0.7.RELEASE\spring-beans-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-webmvc\5.0.7.RELEASE\spring-webmvc-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-aop\5.0.7.RELEASE\spring-aop-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-context\5.0.7.RELEASE\spring-context-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-expression\5.0.7.RELEASE\spring-expression-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-data-jpa\2.0.3.RELEASE\spring-boot-starter-data-jpa-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-aop\2.0.3.RELEASE\spring-boot-starter-aop-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\aspectj\aspectjweaver\1.8.13\aspectjweaver-1.8.13.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-jdbc\2.0.3.RELEASE\spring-boot-starter-jdbc-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\com\zaxxer\HikariCP\2.7.9\HikariCP-2.7.9.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-jdbc\5.0.7.RELEASE\spring-jdbc-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\hibernate\hibernate-core\5.2.17.Final\hibernate-core-5.2.17.Final.jar;C:\Users\bjcon\.m2\repository\org\hibernate\javax\persistence\hibernate-jpa-2.1-api\1.0.2.Final\hibernate-jpa-2.1-api-1.0.2.Final.jar;C:\Users\bjcon\.m2\repository\org\javassist\javassist\3.22.0-GA\javassist-3.22.0-GA.jar;C:\Users\bjcon\.m2\repository\antlr\antlr\2.7.7\antlr-2.7.7.jar;C:\Users\bjcon\.m2\repository\org\jboss\jandex\2.0.3.Final\jandex-2.0.3.Final.jar;C:\Users\bjcon\.m2\repository\dom4j\dom4j\1.6.1\dom4j-1.6.1.jar;C:\Users\bjcon\.m2\repository\org\hibernate\common\hibernate-commons-annotations\5.0.1.Final\hibernate-commons-annotations-5.0.1.Final.jar;C:\Users\bjcon\.m2\repository\javax\transaction\javax.transaction-api\1.2\javax.transaction-api-1.2.jar;C:\Users\bjcon\.m2\repository\org\springframework\data\spring-data-jpa\2.0.8.RELEASE\spring-data-jpa-2.0.8.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\data\spring-data-commons\2.0.8.RELEASE\spring-data-commons-2.0.8.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-orm\5.0.7.RELEASE\spring-orm-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-tx\5.0.7.RELEASE\spring-tx-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-aspects\5.0.7.RELEASE\spring-aspects-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-actuator\2.0.3.RELEASE\spring-boot-starter-actuator-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-actuator-autoconfigure\2.0.3.RELEASE\spring-boot-actuator-autoconfigure-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-actuator\2.0.3.RELEASE\spring-boot-actuator-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\io\micrometer\micrometer-core\1.0.5\micrometer-core-1.0.5.jar;C:\Users\bjcon\.m2\repository\org\hdrhistogram\HdrHistogram\2.1.10\HdrHistogram-2.1.10.jar;C:\Users\bjcon\.m2\repository\org\latencyutils\LatencyUtils\2.0.3\LatencyUtils-2.0.3.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-devtools\2.0.3.RELEASE\spring-boot-devtools-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot\2.0.3.RELEASE\spring-boot-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-autoconfigure\2.0.3.RELEASE\spring-boot-autoconfigure-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\mysql\mysql-connector-java\5.1.46\mysql-connector-java-5.1.46.jar;C:\Users\bjcon\.m2\repository\com\h2database\h2\1.4.197\h2-1.4.197.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-starter-test\2.0.3.RELEASE\spring-boot-starter-test-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-test\2.0.3.RELEASE\spring-boot-test-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\boot\spring-boot-test-autoconfigure\2.0.3.RELEASE\spring-boot-test-autoconfigure-2.0.3.RELEASE.jar;C:\Users\bjcon\.m2\repository\com\jayway\jsonpath\json-path\2.4.0\json-path-2.4.0.jar;C:\Users\bjcon\.m2\repository\net\minidev\json-smart\2.3\json-smart-2.3.jar;C:\Users\bjcon\.m2\repository\net\minidev\accessors-smart\1.2\accessors-smart-1.2.jar;C:\Users\bjcon\.m2\repository\org\ow2\asm\asm\5.0.4\asm-5.0.4.jar;C:\Users\bjcon\.m2\repository\junit\junit\4.12\junit-4.12.jar;C:\Users\bjcon\.m2\repository\org\assertj\assertj-core\3.9.1\assertj-core-3.9.1.jar;C:\Users\bjcon\.m2\repository\org\mockito\mockito-core\2.15.0\mockito-core-2.15.0.jar;C:\Users\bjcon\.m2\repository\net\bytebuddy\byte-buddy\1.7.11\byte-buddy-1.7.11.jar;C:\Users\bjcon\.m2\repository\net\bytebuddy\byte-buddy-agent\1.7.11\byte-buddy-agent-1.7.11.jar;C:\Users\bjcon\.m2\repository\org\objenesis\objenesis\2.6\objenesis-2.6.jar;C:\Users\bjcon\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;C:\Users\bjcon\.m2\repository\org\hamcrest\hamcrest-library\1.3\hamcrest-library-1.3.jar;C:\Users\bjcon\.m2\repository\org\skyscreamer\jsonassert\1.5.0\jsonassert-1.5.0.jar;C:\Users\bjcon\.m2\repository\com\vaadin\external\google\android-json\0.0.20131108.vaadin1\android-json-0.0.20131108.vaadin1.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-core\5.0.7.RELEASE\spring-core-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-jcl\5.0.7.RELEASE\spring-jcl-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\springframework\spring-test\5.0.7.RELEASE\spring-test-5.0.7.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\xmlunit\xmlunit-core\2.5.1\xmlunit-core-2.5.1.jar;C:\Users\bjcon\.m2\repository\io\projectreactor\reactor-test\3.1.8.RELEASE\reactor-test-3.1.8.RELEASE.jar;C:\Users\bjcon\.m2\repository\io\projectreactor\reactor-core\3.1.8.RELEASE\reactor-core-3.1.8.RELEASE.jar;C:\Users\bjcon\.m2\repository\org\reactivestreams\reactive-streams\1.0.2\reactive-streams-1.0.2.jar;"/>
    <property name="sun.cpu.endian" value="little"/>
    <property name="user.home" value="C:\Users\bjcon"/>
    <property name="user.language" value="en"/>
    <property name="java.specification.vendor" value="Oracle Corporation"/>
    <property name="java.home" value="C:\Program Files\Java\jdk1.8.0_161\jre"/>
    <property name="basedir" value="C:\Users\bjcon\Workspace\pccwglobal-demo"/>
    <property name="file.separator" value="\"/>
    <property name="line.separator" value="&#10;"/>
    <property name="java.vm.specification.vendor" value="Oracle Corporation"/>
    <property name="java.specification.name" value="Java Platform API Specification"/>
    <property name="java.awt.graphicsenv" value="sun.awt.Win32GraphicsEnvironment"/>
    <property name="surefire.real.class.path" value="C:\Users\bjcon\AppData\Local\Temp\surefire6590975121147800983\surefirebooter7348209081850986674.jar"/>
    <property name="sun.boot.class.path" value="C:\Program Files\Java\jdk1.8.0_161\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_161\jre\lib\rt.jar;C:\Program Files\Java\jdk1.8.0_161\jre\lib\sunrsasign.jar;C:\Program Files\Java\jdk1.8.0_161\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_161\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_161\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_161\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_161\jre\classes"/>
    <property name="user.script" value=""/>
    <property name="sun.management.compiler" value="HotSpot 64-Bit Tiered Compilers"/>
    <property name="java.runtime.version" value="1.8.0_161-b12"/>
    <property name="user.name" value="bjcon"/>
    <property name="path.separator" value=";"/>
    <property name="os.version" value="10.0"/>
    <property name="java.endorsed.dirs" value="C:\Program Files\Java\jdk1.8.0_161\jre\lib\endorsed"/>
    <property name="java.runtime.name" value="Java(TM) SE Runtime Environment"/>
    <property name="file.encoding" value="Cp1252"/>
    <property name="java.vm.name" value="Java HotSpot(TM) 64-Bit Server VM"/>
    <property name="localRepository" value="C:\Users\bjcon\.m2\repository"/>
    <property name="java.vendor.url.bug" value="http://bugreport.sun.com/bugreport/"/>
    <property name="java.io.tmpdir" value="C:\Users\bjcon\AppData\Local\Temp\"/>
    <property name="idea.version" value="2018.2"/>
    <property name="java.version" value="1.8.0_161"/>
    <property name="user.dir" value="C:\Users\bjcon\Workspace\pccwglobal-demo"/>
    <property name="os.arch" value="amd64"/>
    <property name="java.vm.specification.name" value="Java Virtual Machine Specification"/>
    <property name="java.awt.printerjob" value="sun.awt.windows.WPrinterJob"/>
    <property name="sun.os.patch.level" value=""/>
    <property name="java.library.path" value="C:\Program Files\Java\jdk1.8.0_161\jre\bin;C:\WINDOWS\Sun\Java\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v9.0\bin;C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v9.0\libnvvp;C:\Program Files (x86)\Common Files\Oracle\Java\javapath;C:\ProgramData\Oracle\Java\javapath;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\Program Files\Git\cmd;C:\Program Files\Git\mingw64\bin;C:\Program Files\Git\usr\bin;C:\Program Files\nodejs\;C:\Program Files (x86)\tcc\;C:\Program Files\erl10.0\bin;C:\Users\bjcon\.mix\escripts;C:\Program Files (x86)\NVIDIA Corporation\PhysX\Common;C:\Program Files\foundationdb\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\WINDOWS\System32\OpenSSH\;C:\Program Files\LLVM\bin;C:\Program Files (x86)\Common Files\Adobe\AGL;C:\Program Files (x86)\fossil\;C:\Program Files\Java\jdk-10.0.1\bin;C:\Program Files\cmake\bin;;C:\Program Files\Microsoft VS Code\bin;C:\Program Files (x86)\Elixir\bin;C:\WINDOWS\system32\config\systemprofile\.mix\escripts;C:\Users\bjcon\AppData\Local\Microsoft\WindowsApps;C:\Program Files\Microsoft VS Code\bin;C:\Users\bjcon\AppData\Roaming\npm;C:\Users\bjcon\Workspace\mx;;."/>
    <property name="java.vm.info" value="mixed mode"/>
    <property name="java.vendor" value="Oracle Corporation"/>
    <property name="java.vm.version" value="25.161-b12"/>
    <property name="java.ext.dirs" value="C:\Program Files\Java\jdk1.8.0_161\jre\lib\ext;C:\WINDOWS\Sun\Java\lib\ext"/>
    <property name="sun.io.unicode.encoding" value="UnicodeLittle"/>
    <property name="java.class.version" value="52.0"/>
  </properties>
  <testcase name="create" classname="com.github.bjconlan.pccwglobaldemo.web.UserControllerTests" time="0.468"/>
  <testcase name="delete" classname="com.github.bjconlan.pccwglobaldemo.web.UserControllerTests" time="0.06"/>
  <testcase name="findAll" classname="com.github.bjconlan.pccwglobaldemo.web.UserControllerTests" time="0.203"/>
  <testcase name="update" classname="com.github.bjconlan.pccwglobaldemo.web.UserControllerTests" time="0.023"/>
  <testcase name="findById" classname="com.github.bjconlan.pccwglobaldemo.web.UserControllerTests" time="0.013"/>
</testsuite>
```