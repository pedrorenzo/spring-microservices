package com.pedrorenzo.currencyexchangeservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrorenzo.currencyexchangeservice.entities.ExchangeValue;
import com.pedrorenzo.currencyexchangeservice.services.ExchangeValueService;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	private final Environment environment;
	private final ExchangeValueService exchangeValueService;

	@Autowired
	public CurrencyExchangeController(final Environment environment, final ExchangeValueService exchangeValueService) {
		this.environment = environment;
		this.exchangeValueService = exchangeValueService;
	}

	@GetMapping("/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable final String from, @PathVariable final String to) {
		final ExchangeValue exchangeValue = exchangeValueService.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

		logger.info("Exchange: {}", exchangeValue.getId());
		return exchangeValue;
	}

}
