package com.mx.login.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.core.base.BaseController;
import com.mx.core.session.UserSession;
import com.mx.core.session.UserSessionUtil;
import com.mx.login.model.Login;
import com.mx.login.service.LoginService;

@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseController<Login> {
	@Autowired
	private LoginService loginService;
	
	private static final Logger logger = LogManager.getLogger("mylogs");


	@RequestMapping("/login")
	@ResponseBody
	public void login(){
		Login login = this.getModel();
		Login loginUser = loginService.login(login);
		UserSession us = UserSessionUtil.getUserSession();
		this.renderJson4Model(loginUser);
	}
	
	@Scheduled(cron="0/5 * *  * * ? ")
	public void quartzTest(){  
       System.out.println("我是定时任务"); 
    } 
	
}
