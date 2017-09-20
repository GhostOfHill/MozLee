package com.mx.login.service;

import org.springframework.stereotype.Service;

import com.mx.core.base.BaseService;
import com.mx.core.session.UserSessionUtil;
import com.mx.login.model.Login;


@SuppressWarnings("rawtypes")
@Service
public class LoginService extends BaseService{
		

	@SuppressWarnings("unused")
	public Login login(Login login){
		Login loginUser = (Login) this.getDao().queryForObject("queryUser",login);//正式时候需要查库，查用户
		if(!login.getUserName().equals(loginUser.getUserName())){
			loginUser.setErrCode("1");
			loginUser.setErrMsg("用户名错误");
		}else if(!login.getPassword().equals(loginUser.getPassword())){//判断密码
			loginUser.setErrCode("2");
			loginUser.setErrMsg("密码错误");
		}else if(false){
			loginUser.setErrCode("3");
			loginUser.setErrMsg("用户已被锁定");
		}else{
			loginUser.setErrCode("0");
			//此用户能访问子系统权限参数，根据此参数页面判断能跳那个系统,临时
			loginUser.setSysState("admin".equals(login.getUserName()) ? "00" : "01");//临时
			UserSessionUtil.buildLoginUserSession(login);//用户信息绑定到session ,临时，正常使用loginUser
		}
		return loginUser;
	}
}
