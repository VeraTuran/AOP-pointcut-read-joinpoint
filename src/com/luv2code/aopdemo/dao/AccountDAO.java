package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {
	String name;
	String serviceCode;

	public void addAccount(Account theAccount, boolean vipFlag) {
		System.out.println(getClass() + ": DOING DB WORK: ADDING A VANILLA ACCOUNT");
	}

	public boolean doWork() {
		System.out.println(getClass() + ": è_é inside public boolean doWork() method è_é");
		return false;
	}

	public String getName() {
		System.out.println(getClass() + " in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + " in setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + " in getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + " in setServiceCode()");
		this.serviceCode = serviceCode;
	}

}
