package com.mx.ipole.model;

import java.math.BigDecimal;

public class User {
    private Integer id;

    private String name;

    private String password;

    private Integer sex;

    private BigDecimal age;
    
    private String myAddr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public BigDecimal getAge() {
		return age;
	}

	public void setAge(BigDecimal age) {
		this.age = age;
	}

	public String getMyAddr() {
		return myAddr;
	}

	public void setMyAddr(String myAddr) {
		this.myAddr = myAddr;
	}
    
    
}