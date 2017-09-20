package com.mx.core.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;



/**
 * @Description: http封装
 * @date 2016-8-27
 * @author 申龙赫
 */

public class HttpUtil {
	
	/**
	 *	@Description 
	 */
	public static String post(String url,String jsonStr) {
		StringBuffer sb = new StringBuffer();
		try {
				//创建链接
			  	URL urll = new URL(url);
	            HttpURLConnection connection = (HttpURLConnection) urll.openConnection();
	            connection.setDoOutput(true);
	            connection.setDoInput(true);
	            connection.setRequestMethod("POST");
	            connection.setUseCaches(false);
	            connection.setInstanceFollowRedirects(true);
	            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	            connection.connect();

	            //POST请求
	            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
	            out.write(jsonStr.getBytes("UTF-8"));

	            out.flush();
	            out.close();

	            //读取响应
	            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String lines;
	            while ((lines = reader.readLine()) != null) {
	                lines = new String(lines.getBytes(), "utf-8");
	                sb.append(lines);
	            }
	            reader.close();
	            // 断开连接
	            connection.disconnect();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
		return sb.toString();
	}
	
	/**
	 *	map转换url形式
	 *  @param map
	 */
	public static String getUrlParamsByMap(Map<String, String> map) {  
	    if (map == null) {  
	        return "";  
	    }  
	    StringBuffer sb = new StringBuffer();  
	    for (Entry<String, String> entry : map.entrySet()) {  
    		sb.append(entry.getKey() + "=" + entry.getValue());
	        sb.append("&");  
	    }  
	    String s = sb.toString();  
	    if (s.endsWith("&")) {  
	        s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");  
	    }  
	    return s;  
	}
}
