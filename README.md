# soq70109143
Answer code for [Spring Boot. How to test http protocol version?](https://stackoverflow.com/q/70109143/592355).

Demonstrates how to obtain & assert on the HTTP protocol version, used by spring-boot:

1. With `java.net.http` client.
2. With `org.apache.http` client.

(Set JAVA_HOME to jdk-17):
 
    export JAVA_HOME=/path/to/jdk-17/

Execute Tests:

    mvn clean test
    
Inspect: [The tests](src/test/java/com/example/test/http/version/IntegrationTests.java).
