package com.pedrorenzo.currencyexchangeservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedrorenzo.currencyexchangeservice.entities.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

	ExchangeValue findByFromAndTo(final String from, final String to);
	
}
