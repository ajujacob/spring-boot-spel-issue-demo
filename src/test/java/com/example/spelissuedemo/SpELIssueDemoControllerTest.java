package com.example.spelissuedemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.UseMainMethod;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(useMainMethod = UseMainMethod.ALWAYS, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@WithMockUser
public class SpELIssueDemoControllerTest {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void testEndpointAllowAccess() {
		this.webTestClient
			.get().uri("/?allowAccess=true")
			.exchange()
			.expectStatus().isOk();
	}

	@Test
	void testEndpointDenyAccess() {
		this.webTestClient
			.get().uri("/?allowAccess=false")
			.exchange()
			.expectStatus().isForbidden();
	}

}