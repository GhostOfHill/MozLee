package com.mx.ipole.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mx.core.base.BaseService;
import com.mx.ipole.model.User;


@SuppressWarnings("rawtypes")
@Service
public class UserService extends BaseService{
		

	@SuppressWarnings("unchecked")
	public List<User> getUserById(int userId){
		System.out.println("service我进来了"+userId);
		
//		System.out.println(this.getRedisDao().set("testKey", "testValue"));
//		System.out.println(this.getRedisDao().get("testKey"));
		
		return this.getDao().queryForList("selectByPrimaryKey",userId);
	}
}
