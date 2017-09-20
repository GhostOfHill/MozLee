package com.mx.core.session;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import com.common.session.UserSession;
import com.mx.core.common.Config;
import com.mx.core.common.utils.SpringMVCUtil;
import com.mx.login.model.Login;
//import com.web.model.Common;


/**
 * 用户session工具类
 */
public class UserSessionUtil {
	/**
	 * usersession在HttpSession中的键
	 */
	public static final String USER_SESSION_KEY = "_mx_user_session_";

	/**
	 * 根据SID获取session
	 * 必须开启session redis共享
	 * @param sid 预览器SID
	 * @return
	 */
//	public static UserSession getUserSession(String sid) {
//		Map<byte[], byte[]> session = RedisPool.getMapFromRedis(sid.getBytes());
//		byte[] _user_session_ = session.get("_user_session_".getBytes());
//		return (UserSession)SerilizableUtils.unserialize(_user_session_);
//	}
	
	
//	/**
//	 * 为非本地项目制作SESSION
//	 * @param user
//	 * @param sid
//	 */
//	public static void buildZdphLoginUserSession(String user, String sid) {
//		createUserSession(getZdphUserSession(user), sid);// 把登陆人员信息放入session
//	}
	
//	private static UserSession getZdphUserSession(String user){
//		UserSession userSession = new UserSession();
//		userSession.setUserId(JsonUtil.getJsonValue(user, "userId"));
//		userSession.setFirstName(JsonUtil.getJsonValue(user, "firstName"));
//		userSession.setLastName(JsonUtil.getJsonValue(user, "lastName"));
//		userSession.setOrganCode(JsonUtil.getJsonValue(user, "organCode"));
//		userSession.setOrganName(JsonUtil.getJsonValue(user, "organName"));
//		userSession.setCompanyCode(JsonUtil.getJsonValue(user, "companyCode"));
//		userSession.setCompanyName(JsonUtil.getJsonValue(user, "companyName"));
//		userSession.setSystemSessionSign(Config.getString("systemSessionSign"));
//		return userSession;
//	}
	

	/**
	 * 模拟session在DebugRequestParameterFilter42行调用 开发时使用防止session空报错
	 */
	public static void buildTestSession(HttpSession session) {
		UserSession userSession = new UserSession();
//		if ("zdph".equals(Config.getString("systemType"))) {
			userSession.setUserId("superadmin");
			userSession.setFirstName("superadmin");
			userSession.setLastName("superadmin");
			userSession.setOrganName("组织名称");
			userSession.setOrganCode("1000001");
			userSession.setCompanyCode("1000001");
			userSession.setSystemSessionSign("test");
//		} else {
//			userSession.setRybh("testRybh");
//			userSession.setRymc("testRymc");
//			userSession.setSjbmbh("testSjbmbh");
//			userSession.setQybh("testQybh");
//			userSession.setQymc("testQymc");
//		}
		session.setAttribute(UserSessionUtil.USER_SESSION_KEY, userSession);
	}

	/**
	 * 从HttpSession中获取用户Session
	 * @return Session
	 */
	public static UserSession getUserSession() {
		HttpSession httpSession = SpringMVCUtil.getSession(true);
		return getUserSession(httpSession);
	}

	/**
	 * 从HttpSession中获取用户Session
	 * @return Session
	 */
	public static UserSession getUserSession(HttpSession httpSession) {
		Object obj = httpSession.getAttribute(USER_SESSION_KEY);
		if (obj == null) {
			return null;
		}
		return (UserSession) obj;
	}

	/**
	 * 创建用户session，放入HttpSession中并返回用户Session对象
	 * @return UserSession
	 */
	public static void createUserSession(UserSession userSession) {
		if (userSession == null) userSession = new UserSession();
		HttpSession httpSession = SpringMVCUtil.getSession();
		httpSession.setAttribute(USER_SESSION_KEY, userSession);
	}
	
	/**
	 * 创建用户session，放入HttpSession中并返回用户Session对象
	 * @return UserSession
	 */
//	public static void createUserSession(UserSession userSession, String sid) {
//		if (userSession == null) userSession = new UserSession();
//		HttpSessionSidWrapper httpSession = (HttpSessionSidWrapper) SpringMVCUtil.getSession();
//		httpSession.setAttribute(USER_SESSION_KEY, userSession, sid);
//	}

	/**
	 * 从HttpSession中删除用户Session
	 */
	public static void removeUserSession() {
		HttpSession httpSession = SpringMVCUtil.getSession(true);
		httpSession.removeAttribute(USER_SESSION_KEY);
	}
	
	/**
	 * 绑定登录人session
	 * @param user 用户数据
	 */
	public static void buildLoginUserSession(Login user) {
		UserSession userSession = new UserSession();
		userSession.setLoginName(user.getUserName());
		userSession.setUserId("testUserId"+String.valueOf(new Date().getTime()));//临时
		createUserSession(userSession);// 把登陆人员信息放入session
	}
	
	/**
	 *	判断session是否过期
	 * @param request
	 */
	public static Boolean sessionTimeout(HttpServletRequest request){
		if ("true".equals(Config.getString("login"))) {
			UserSession userSession = getUserSession(request.getSession());
			String referer = request.getHeader("Referer");
			if(userSession == null && referer == null){
				return true;
			}
		}
		return false;
	}
}
