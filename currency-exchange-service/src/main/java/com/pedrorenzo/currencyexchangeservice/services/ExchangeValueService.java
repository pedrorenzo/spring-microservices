package com.pedrorenzo.currencyexchangeservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrorenzo.currencyexchangeservice.entities.ExchangeValue;
import com.pedrorenzo.currencyexchangeservice.repositories.ExchangeValueRepository;

@Service
public class ExchangeValueService {

	private final ExchangeValueRepository exchangeValueRepository;

	@Autowired
	public ExchangeValueService(final ExchangeValueRepository exchangeValueRepository) {
		this.exchangeValueRepository = exchangeValueRepository;
	}

	public ExchangeValue findByFromAndTo(final String from, final String to) {
		return exchangeValueRepository.findByFromAndTo(from, to);
	}

}
