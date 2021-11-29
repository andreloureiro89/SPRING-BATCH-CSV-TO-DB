package com.learn.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class BatchProcessor implements ItemProcessor<CountriCurrency, CountriCurrency> {

	private static final Logger log = LoggerFactory.getLogger(BatchProcessor.class);
	
	@Override
	public CountriCurrency process(final CountriCurrency countriCurrency) throws Exception {
		
		final String countryName = countriCurrency.getCountryName().toUpperCase();
		final String countryCoinName = countriCurrency.getCountryCoinName().toUpperCase();
		
		final CountriCurrency dataTransformed = new CountriCurrency(countryName,countryCoinName,countriCurrency.getCountrySigla(),countriCurrency.getCountryCoinValue());
	
		log.info("Convert data to UPPERCASE =============");
		log.info("OLD: " + countriCurrency);
		log.info("TO: " + dataTransformed);
		log.info("Convert data to UPPERCASE =============");
		
		return dataTransformed;
	}
}
