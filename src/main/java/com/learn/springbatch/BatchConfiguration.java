package com.learn.springbatch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;	
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
	public FlatFileItemReader<CountriCurrency> reader(){
		return new FlatFileItemReaderBuilder<CountriCurrency>()
				.name("countryItemReader")
				.resource(new ClassPathResource("sample-data.csv"))
				.delimited()
				.names(new String[]{"countryName","countryCoinName","countrySigla","countryCoinValue"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<CountriCurrency>() {{
					setTargetType(CountriCurrency.class);
				}})
				.build();
	}
	
	@Bean
	public BatchProcessor processor() {
		return new BatchProcessor();
	}
	
	@Bean
	public JdbcBatchItemWriter<CountriCurrency> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<CountriCurrency>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO countryCurrencies (country_name, country_coin_name, country_coin_sigla, country_coin_value) VALUES (:countryName,:countryCoinName,:countrySigla,:countryCoinValue)")
				.dataSource(dataSource)
				.build();
	}
	
	@Bean
	public Job importCountryJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importCountryJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1)
				.end()
				.build();
	}
	
	@Bean
	public Step step1(JdbcBatchItemWriter<CountriCurrency> writer) {
		return stepBuilderFactory.get("step1")
				.<CountriCurrency, CountriCurrency> chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer)
				.build();
	}

}
