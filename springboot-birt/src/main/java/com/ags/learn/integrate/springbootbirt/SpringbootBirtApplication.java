package com.ags.learn.integrate.springbootbirt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
//(exclude = {MongoAutoConfiguration.class, 
//			MongoDataAutoConfiguration.class})
public class SpringbootBirtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBirtApplication.class, args);
		
		String classpath = System.getProperty("java.class.path");
		System.out.println(classpath);
	}

}
