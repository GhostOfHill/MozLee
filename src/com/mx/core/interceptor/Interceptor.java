package com.mx.core.interceptor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mx.core.session.UserSessionUtil;

/**
 * @Description: 公共拦截器
 * @date 2016-11-27
 * @author 申龙赫
 */
public class Interceptor implements HandlerInterceptor{
	
	private static final Logger logger = LogManager.getLogger("mylogs");
	
	private static final String LOGINURL = "/jsp/login/login.jsp";//登录页面url

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,Object handler, ModelAndView arg3) throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        if (logger.isInfoEnabled()) {
        	System.out.println("【请求时长】: " + request.getRequestURI() + " " + executeTime + "ms");
        }
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
		String url = request.getRequestURI();
		if(!url.contains("/loginController/login") && UserSessionUtil.sessionTimeout(request)){
			System.out.println(url);
			System.out.println("interceptor:session过期");
			response.sendRedirect(LOGINURL);
			return false;
		}
		long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        // 打印信息
 		String referer = request.getHeader("Referer");
 		if (referer == null) {
 			 referer = "new";
 		}
 		referer = referer.replaceAll("^[^/]+//[^/]+", "");
		System.out.println("【请求地址】: " + request.getRequestURI() + "  " + request.getMethod());
		// 排序输出参数[名称:值]
		Iterator<Map.Entry> it = new TreeMap(request.getParameterMap()).entrySet().iterator();
		int i = 1;
		while (it.hasNext()) {
			Map.Entry<String, String[]> me = it.next();
			System.out.println("【请求参数" + i + "】: " + "[" + me.getKey() + "]" + " 【值】 : " + Arrays.asList(me.getValue()));
			i++;
		}
		return true;
	}
	
}
