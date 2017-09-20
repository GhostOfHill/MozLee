package com.mx.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mx.core.common.utils.SpringContextUtil;

/**
 * 监听web服务启动和关闭
 */
public class SpringContextListener extends HttpServlet implements ServletContextListener {
	private static final long serialVersionUID = -5203457901482093858L;

	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getServletContext());
		SpringContextUtil.setAppContext(ctx);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
