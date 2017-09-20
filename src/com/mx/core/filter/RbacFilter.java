package com.mx.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mx.core.common.Config;
import com.mx.core.common.utils.RegexUtils;
import com.mx.core.session.UserSessionUtil;

/**
 * @Description: 权限过滤器
 * @date 2016-11-27
 * @author 申龙赫
 */
public class RbacFilter implements Filter {
	
	private String[] ignoreurl;
	
	private static final String LOGINURL = "/jsp/login/login.jsp";//登录页面url

	public RbacFilter() {
		super();
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		if("true".equals(Config.getString("login"))){
			this.ignoreurl = config.getInitParameter("ignoreurl").split(";");
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if ("true".equals(Config.getString("login"))) {
			String url = req.getRequestURI();
			// 判断是否直接通过地址栏访问
			// 去掉url中多余的'/'
			url = url.replaceAll("/{2,}", "/");
			for (String iurl : ignoreurl) {
				if (iurl.contains("*")) {
					String[] iurlArr = iurl.split("\\*");
					for (int i = 0; i < iurlArr.length; i++) {
						if (!"".equals(iurlArr[i].trim()) && "".equals(RegexUtils.findString(url, iurlArr[i]))) {
							break;
						}
						if (i + 1 == iurlArr.length) {
							chain.doFilter(request, response);
							return;
						}
					}
				} else if (iurl.trim().equals(url)) {
					chain.doFilter(request, response);
					return;
				}
			}
			
			if(UserSessionUtil.sessionTimeout(req)  && !"/".equals(url)){
				System.out.println("filter:session过期");
				resp.sendRedirect(LOGINURL);
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		if (Config.getString("login").equals("true")) {
			this.ignoreurl = null;
		}
	}
}
