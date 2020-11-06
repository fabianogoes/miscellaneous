package com.example.apidemo.config;

import javax.validation.Validator;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidatorConfig {

	@Bean("localValidatorFactoryBean")
	public Validator localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}

}