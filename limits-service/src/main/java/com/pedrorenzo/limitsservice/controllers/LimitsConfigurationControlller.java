package com.pedrorenzo.limitsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pedrorenzo.limitsservice.config.Configuration;
import com.pedrorenzo.limitsservice.entities.LimitConfiguration;

@RestController
@RequestMapping("/limits")
public class LimitsConfigurationControlller {

	private final Configuration configuration;
	
	@Autowired
	public LimitsConfigurationControlller(final Configuration configuration) {
		this.configuration = configuration;
	}
	
	@GetMapping
	@HystrixCommand(fallbackMethod = "fallbackRetrieveConfiguration")
	public LimitConfiguration retrieveLimitsFromConfigurations() {
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}

	public LimitConfiguration fallbackRetrieveConfiguration() {
		return new LimitConfiguration(9999, 9999);
	}
	
}
