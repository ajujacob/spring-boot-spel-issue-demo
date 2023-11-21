package com.example.spelissuedemo;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

public class SPELIssueDemoUnitTest {
	
	@Test
	void testMethodSecuritySPEL() {
		new ReactiveWebApplicationContextRunner()
			.withUserConfiguration(ReactiveMethodSecuritySPELTestConfiguration.class)
			.run(context -> {
				assertThrows(
						AccessDeniedException.class,
						() -> context.getBean(ReactiveMethodSecuritySPELTestConfiguration.MyService.class).securedMethod(false).block());
			});
	}
	
	@TestConfiguration
	@EnableReactiveMethodSecurity
	static class ReactiveMethodSecuritySPELTestConfiguration {
		
		@Service("myService")
		class MyService {
			
			@PreAuthorize("@myService.evaluateAccess(#allowAccess)")
			public Mono<?> securedMethod(boolean allowAccess) {
				return Mono.empty();
			}
			
			public boolean evaluateAccess(boolean allowAccess) {
				return allowAccess;
			}
			
		}
		
	}
}
