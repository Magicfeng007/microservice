package com.magic.microservice.spring_consumer.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.magic.microservice.spring_consumer.entity.MobilePhone;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class MobileContorller {
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${mobile.queryMobileUrl}")
	private String queryMobileUrl;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("getMobileById/{id}")
	public MobilePhone queryMobileById(@PathVariable String id) {//@PathVariable注解可以通过url路径获取参数
		System.out.println(queryMobileUrl);
		System.out.println(id);
		return restTemplate.getForObject(queryMobileUrl, MobilePhone.class, id);
	}
	
	@GetMapping("serviceInfo/{hostname}")
	public String serviceInfo(@PathVariable String hostname) {
		InstanceInfo info = eurekaClient.getNextServerFromEureka(hostname, false);
		return "getHomePageUrl:" + info.getHomePageUrl() + "  getInstanceId:" + info.getInstanceId();
	}
	
	@GetMapping("showLocalServiceInfo")
	public ServiceInstance showLocalServiceInfo() {
		ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
		return serviceInstance;
	}
	
	@GetMapping("showServiceInfo/{hostname}")
	public List<ServiceInstance> showServiceInfo(@PathVariable String hostname) {
		List<ServiceInstance> serviceInstance = discoveryClient.getInstances(hostname);
		return serviceInstance;
	}
}
