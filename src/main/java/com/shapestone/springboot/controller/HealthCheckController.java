package com.shapestone.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/static")
public class HealthCheckController {

	@GetMapping("/")
	public String greet() {
		return "Welcome to the springboot..";
	}
}
