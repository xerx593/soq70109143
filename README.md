# soq70109143
Answer code for https://stackoverflow.com/q/70109143/592355.

Demonstrates how to obtain & assert on the HTTP protocol version, used by spring-boot:

1. With `java.net.http` client.
2. With `org.apache.http` client.

Execute Tests:

    mvn clean test
    
Inspect: [The tests](src/test/java/com/example/test/http/version/IntegrationTests.java).
