package com.mx.ipole.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.core.base.BaseController;
import com.mx.ipole.model.User;
import com.mx.ipole.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LogManager.getLogger("mylogs");


	
	@RequestMapping("/showUser")
	@ResponseBody
	public void toIndex(HttpServletRequest request,Model model){
		User u = this.getModel();
		logger.debug("=========showUser:"+u.getId());
		List<User> user = userService.getUserById(u.getId());
		this.renderJson4List(user);
	}
	
	public static void main(String[] args) {
		
	}
}
