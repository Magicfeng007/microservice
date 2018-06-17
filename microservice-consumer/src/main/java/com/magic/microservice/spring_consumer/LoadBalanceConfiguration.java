package com.magic.microservice.spring_consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 这个RibbonClient配置不能被SpringBoot应用扫描到，否则此负载均衡配置将被应用到所有请求
 * @author magic
 *
 */
@Configuration
@ExcludeComponentScan
//通过@ExcludeComponentScan这个注解和ConsumerApplication类上面的
//@ComponentScan(value="com.magic.microservice.spring_consumer",excludeFilters= {@ComponentScan.Filter(type=FilterType.ANNOTATION,value=ExcludeComponentScan.class)})
//注解配置让LoadBalanceConfiguration不被SpringBoot应用扫描到
//如果在SpringBoot应用能扫描到的时候，使用了IClientConfig则会报错：Field config in com.magic.microservice.spring_consumer.LoadBalanceConfiguration required a bean of type 'com.netflix.client.config.IClientConfig' that could not be found.
public class LoadBalanceConfiguration {
	
	@Autowired
	IClientConfig config;
	
	@Bean
	@ConditionalOnMissingBean
	public IRule ribbonRule(IClientConfig config) {
		RandomRule randomRule = new RandomRule();
		return randomRule;
	}
}
