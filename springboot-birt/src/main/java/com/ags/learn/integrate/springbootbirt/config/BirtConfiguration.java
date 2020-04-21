package com.ags.learn.integrate.springbootbirt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import java.util.logging.Level;

@Configuration
public class BirtConfiguration {
	@Value("${birt.log.location}")
	String logLocation;

	@Bean
	protected BirtEngineFactory engine(){ 
		BirtEngineFactory factory = new BirtEngineFactory() ;  
		//Enable BIRT Engine Logging
		factory.setLogLevel(Level.INFO);
		factory.setLogDirectory( new FileSystemResource(logLocation));
		return factory ; 
	}
}
