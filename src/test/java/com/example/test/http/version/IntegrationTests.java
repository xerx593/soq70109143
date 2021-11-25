package com.example.test.http.version;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IntegrationTests {

    @LocalServerPort
    private int port;

    @Test
    public void contextLoads() {
        assertTrue(port > 0 && port <= 2 * Short.MAX_VALUE);
    }

    @Test
    public void testJavaDotNet() throws IOException, InterruptedException {
        // Given:
        String uri = getUriString("foo");
        java.net.http.HttpClient client = java.net.http.HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder().uri(URI.create(uri)).build();

        // When:
        java.net.http.HttpResponse<String> response = client.send(request,
                java.net.http.HttpResponse.BodyHandlers.ofString());

        // Then:
        assertNotNull(response);
        assertEquals(java.net.http.HttpClient.Version.HTTP_2, response.version());
    }

    private String getUriString(String path) {
        return String.format("http://localhost:%d/%s", port, path);
    }

}
