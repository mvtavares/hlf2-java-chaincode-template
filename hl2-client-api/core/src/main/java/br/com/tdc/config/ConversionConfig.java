package br.com.tdc.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class ConversionConfig {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	
	@Bean
	public ObjectMapper objectMapper() {
	    return new ObjectMapper()
	    	    .findAndRegisterModules()
	    	    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}
	
}
