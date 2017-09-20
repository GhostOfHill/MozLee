package com.mx.core.common.utils;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.core.common.Config;

public class SpringMVCUtil {
	// -- header 常量定义 --//
	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean DEFAULT_NOCACHE = true;
	private static ObjectMapper mapper = new ObjectMapper();
	/**
	 * @Description: 取得HttpSession的简化函数.
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}
	
	/**
	 * 获取session sid
	 * @return sid
	 */
	public static String getSessionSid() {
		if("true".equals(Config.getString("redisState"))) {
			return getSession().getId().replaceAll("\\..*", "");
		}
		return "";
	}

	/**
	 * @Description: 取得HttpSession的简化函数.
	 * @param isNew
	 * @return
	 */
	public static HttpSession getSession(boolean isNew) {
		return getRequest().getSession(isNew);
	}

	/**
	 * @Description: 取得HttpSession中Attribute的简化函数.
	 * @param name
	 *            属性名
	 */
	public static Object getSessionAttribute(String name) {
		HttpSession session = getSession(false);
		return (session != null ? session.getAttribute(name) : null);
	}

	/**
	 * @Description: 取得HttpRequest的简化函数.
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * @Description: 取得HttpRequest中Parameter的简化方法.
	 * @param name
	 *            参数名
	 * @return
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * @Description:取得HttpResponse的简化函数.
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	/**
	 * 直接输出内容的简便函数. eg. render("text/plain", "hello", "encoding:UTF-8");
	 * render("text/plain", "hello", "no-cache:false"); render("text/plain",
	 * "hello", "encoding:UTF-8", "no-cache:false");
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(final String contentType, final String content, final String... headers) {
		HttpServletResponse response = initResponseHeader(contentType, headers);
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * @Description: 直接输出文本.
	 * @param text
	 *            文本内容
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void renderText(final String text, final String... headers) {
		render(ServletUtils.TEXT_TYPE, text, headers);
	}

	/**
	 * @Description: 直接输出HTML.
	 * @param html
	 *            html片段
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void renderHtml(final String html, final String... headers) {
		render(ServletUtils.HTML_TYPE, html, headers);
	}

	/**
	 * @Description: 直接输出XML.
	 * @param xml
	 *            xml字符串
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void renderXml(final String xml, final String... headers) {
		render(ServletUtils.XML_TYPE, xml, headers);
	}

	/**
	 * @Description: 直接输出JSON.
	 * @param jsonString
	 *            json字符串.
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void renderJson(final String jsonString, final String... headers) {
		render(ServletUtils.JSON_TYPE, jsonString, headers);
	}

	/**
	 * @Description: 直接输出JSON,使用Jackson转换Java对象
	 * @param data
	 *            可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void renderJson(final Object data, final String... headers) {
		HttpServletResponse response = initResponseHeader(ServletUtils.JSON_TYPE, headers);
		try {
			mapper.writeValue(response.getWriter(), data);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @Description: 将文件发送到客户端
	 * @param file_path_prefix
	 *            下载文件的路径前缀
	 * @param filename
	 *            下载文件名
	 */
	public static void renderFile(String file_path_prefix, String filename, String ymc) {
		FileInputStream fis = null;
		OutputStream toClient = null;
		try {
			HttpServletResponse response = getResponse();
			// 设置response的Header
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(ymc.getBytes("gbk"), "ISO8859-1"));
			// response.addHeader( " Content-Length " , "" + file.length());
			response.setCharacterEncoding("utf8");
			toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			// 创建流对象
			fis = new FileInputStream(file_path_prefix + "/" + filename);
			// 创建字节数组，用来做流读取数据的缓冲区
			byte[] b = new byte[1024];
			int n = 0;
			// 循环读取流，每次读取1024字节
			while ((n = fis.read(b)) != -1) {
				// 输出流，使用wrtie方法，每次输出缓冲区里的数据
				toClient.write(b, 0, n);
			}
			toClient.flush();
			// 捕获异常信息
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			try {
				toClient.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @Description: 将字节发送到客户端
	 * @param b
	 *            发送的内容
	 * @param filename
	 *            文件名
	 */
	public static void renderByte(byte[] b, String filename) {
		OutputStream toClient = null;
		try {
			HttpServletResponse response = getResponse();
			// 设置response的Header
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=" + filename);
			// response.addHeader( " Content-Length " , "" + file.length());
			toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(b);
			toClient.flush();
		} catch (Exception e) {
			// 捕获异常信息
			e.printStackTrace();
		} finally {
			// 关闭流
			try {
				toClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Description:直接输出支持跨域Mashup的JSONP.
	 * @param callbackName
	 *            callback函数名.
	 * @param object
	 *            Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void renderJsonp(final String callbackName, final Object object, final String... headers) {
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		String result = new StringBuilder().append(callbackName).append("(").append(jsonString).append(");").toString();
		render(ServletUtils.JS_TYPE, result, headers);
	}

	/**
	 * @Description: 以String的方式返回对象(obj)的json
	 * @param obj
	 * @return
	 */
	public static String serializer(Object obj) {
		String text = null;
		try {
			text = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}

	/**
	 * @Description: 分析并设置contentType与headers.
	 * @param contentType
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 * @return
	 */
	private static HttpServletResponse initResponseHeader(final String contentType, final String... headers) {
		// 分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (String header : headers) {
			String headerName = org.apache.commons.lang3.StringUtils.substringBefore(header, ":");
			String headerValue = org.apache.commons.lang3.StringUtils.substringAfter(header, ":");
			if (org.apache.commons.lang3.StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (org.apache.commons.lang3.StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else {
				throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
			}
		}
		// 设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		HttpServletResponse response = getResponse();
		response.setContentType(fullContentType);
		if (noCache) {
			ServletUtils.setNoCacheHeader(response);
		}
		return response;
	}

	/**
	 * 获取当前语言类型
	 * @return
	 */
	public static String getNowLangType() {
		HttpSession session = getSession();
		HttpServletRequest request = getRequest();
		Locale userLang = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
		if (userLang != null) {
			return userLang.getLanguage() + "_" + userLang.getCountry().toLowerCase();
		} else {
			Locale navigatorLang = request.getLocale();
			return navigatorLang.getLanguage() + "_" + navigatorLang.getCountry().toLowerCase();
		}
	}
}
