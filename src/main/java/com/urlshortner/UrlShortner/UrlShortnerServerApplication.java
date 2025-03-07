package com.urlshortner.UrlShortner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
//@EnableMongoRepositories(basePackages = "com.urlshortner.UrlShortner.Repository")
public class UrlShortnerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortnerServerApplication.class, args);
	}

//	@Bean
//	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
//		return new MongoTransactionManager(dbFactory);
//	}

	@Bean
	public RestTemplate restTemplate (){
		return new RestTemplate();
	}


}
