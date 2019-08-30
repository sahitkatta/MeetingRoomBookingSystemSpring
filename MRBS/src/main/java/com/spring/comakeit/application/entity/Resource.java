package com.spring.comakeit.application.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Resource")
public class Resource {
	@Id
	private Integer id;
	private String resourceName;

	public Integer getId() {
		return id;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
