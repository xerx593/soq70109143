package com.example.test.http.version;

import java.io.IOException;
import java.net.URI;
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
		String uri = getUriString("/foo");
		java.net.http.HttpClient client = java.net.http.HttpClient.newBuilder().build();
		java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder().uri(URI.create(uri)).build();

		// When:
		java.net.http.HttpResponse<String> response = client.send(request,
				java.net.http.HttpResponse.BodyHandlers.ofString());

		// Then:
		assertNotNull(response);
		assertEquals(java.net.http.HttpClient.Version.HTTP_1_1, response.version());
	}

	@Test
	public void testApache() throws IOException {
		// Given:
		String uri = getUriString("/foo");
		org.apache.http.client.HttpClient client = org.apache.http.impl.client.HttpClientBuilder.create().build();

		// When:
		org.apache.http.HttpResponse response = client.execute(new org.apache.http.client.methods.HttpGet(uri));

		// Then:
		assertNotNull(response);
		assertNotNull(response.getStatusLine());

		org.apache.http.ProtocolVersion protocolV = response.getStatusLine().getProtocolVersion();

		assertNotNull(protocolV);

		assertEquals("HTTP", protocolV.getProtocol());
		assertEquals(1, protocolV.getMajor());
		assertEquals(1, protocolV.getMinor());
	}

	private String getUriString(String path) {
		return String.format("http://localhost:%d/%s", port, path);
	}

}
