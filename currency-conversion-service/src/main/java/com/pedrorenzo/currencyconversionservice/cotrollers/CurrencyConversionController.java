package com.pedrorenzo.currencyconversionservice.cotrollers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrorenzo.currencyconversionservice.entities.CurrencyConversion;
import com.pedrorenzo.currencyconversionservice.proxies.CurrencyExchangeServiceProxy;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

	private final CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

	@Autowired
	public CurrencyConversionController(final CurrencyExchangeServiceProxy currencyExchangeServiceProxy) {
		this.currencyExchangeServiceProxy = currencyExchangeServiceProxy;
	}

	@GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion convertCurrency(@PathVariable final String from, @PathVariable final String to,
			@PathVariable final BigDecimal quantity) {

		final CurrencyConversion currencyExchangeResponseBody = currencyExchangeServiceProxy.retrieveExchangeValue(from,
				to);

		return new CurrencyConversion(currencyExchangeResponseBody.getId(), from, to,
				currencyExchangeResponseBody.getConversionMultiple(), quantity,
				quantity.multiply(currencyExchangeResponseBody.getConversionMultiple()),
				currencyExchangeResponseBody.getPort());
	}

}
