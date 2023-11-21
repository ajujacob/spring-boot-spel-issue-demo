package com.example.spelissuedemo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
@EnableReactiveMethodSecurity
public class SpELIssueDemoController {
	
	@GetMapping
	@PreAuthorize("@spELIssueDemoController.evaluateAccess(#allowAccess)")
	Mono<String> retriveMessage(@RequestParam(name = "allowAccess", required = false, defaultValue = "true") boolean allowAccess) {
		return Mono.just("HELLO");
	}
	
	public boolean evaluateAccess(boolean allowAccess) {
		return allowAccess;
	}

}