package com.magic.microservice.spring_consumer.entity;

import java.math.BigDecimal;

/**
 * @Author: Magicfeng007
 * @Description:
 * @Date: Created in 2018-06-10---下午1:09
 */
public class MobilePhone {
    private Integer id;
    private String brand;
    private String model;
    private BigDecimal price;
    private String configuration;

    public MobilePhone() {
    }

    public MobilePhone(Integer id, String brand, String model, BigDecimal price, String configuration) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.configuration = configuration;
    }

    public MobilePhone(String brand, String model, BigDecimal price, String configuration) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.configuration = configuration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

	@Override
	public String toString() {
		return "MobilePhone [id=" + id + ", brand=" + brand + ", model=" + model + ", price=" + price
				+ ", configuration=" + configuration + "]";
	}
    
    


}
