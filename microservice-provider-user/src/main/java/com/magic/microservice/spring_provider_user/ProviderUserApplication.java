package com.magic.microservice.spring_provider_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient //@EnableEurekaClient 专属于Eureka  @EnableDiscoveryClient通用的，比如Zookeeper
public class ProviderUserApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProviderUserApplication.class, args);
	}
	
	@Bean
    public RestTemplate RestTemplate(RestTemplateBuilder restTemplateBuilder) {
    	return restTemplateBuilder.build();
    }

}
