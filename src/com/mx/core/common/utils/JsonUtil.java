package com.mx.core.common.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * Json工具类
 */
public class JsonUtil {
	public static Gson gson = new GsonBuilder().registerTypeAdapter(Timestamp.class,new TimestampTypeAdapter()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	/**
	 * 将json字符串转换为Map断
	 * @param jsonString
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map jsonStr2Map(String jsonString) {
		return gson.fromJson(jsonString, Map.class);
	}
	
	/**
	 * 将map转换成json
	 * @param map
	 * @return
	 */
	public static String map2JsonStr(Map<String, Object> map) {
		return gson.toJson(map);
	}
	
	/**
	 * 从一个JSON 对象字符格式中得到一个java对象
	 * @param jsonString
	 * @param pojoCalss
	 * @return
	 */
	public static Object getObject4JsonString(String jsonString, Class<?> modelCalss) {
		return gson.fromJson(jsonString, modelCalss);
	}

	/**
	 * 从json HASH表达式中获取一个map，改map支持嵌套功能
	 * @param jsonString
	 * @return
	 */
	public static Map<String, String> getMap4Json(String jsonString) {
		return gson.fromJson(jsonString, new TypeToken<Map<String, String>>() {}.getType());
	}

	/**
	 * 按KEY中获取JSON中值
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static String getJsonValue(String jsonStr, String key) {
		String data = new JsonParser().parse(jsonStr).getAsJsonObject().get(key).toString();
		return data.substring(1, data.length()-1);
	}

	/**
	 * @Description:将java对象转换成json字符串
	 * @param javaObj
	 * @return
	 */
	public static String getJsonString4JavaModel(Object javaObj) {
		return gson.toJson(javaObj);
	}
	
	/**
	 * @Description:从json对象集合表达式中得到一个java对象列表
	 * @param jsonString
	 * @param pojoClass
	 * @return
	 */
	public static List<Object> getList4Json(String jsonString, Class<?> pojoClass) {
		List<Object> list = new ArrayList<Object>();
		JsonArray jsonArray = gson.fromJson(jsonString, JsonArray.class);
		for (int i = 0; i < jsonArray.size(); i++)
			list.add(gson.fromJson(jsonArray.get(i), pojoClass));
		return list;
	}

	/**
	 * @Description:从json数组中解析出java字符串数组
	 * @param jsonString
	 * @return
	 */
//	public static String[] getStringArray4Json(String jsonString) {
//		JSONArray jsonArray = JSONArray.fromObject(jsonString);
//		String[] stringArray = new String[jsonArray.size()];
//		for (int i = 0; i < jsonArray.size(); i++) {
//			stringArray[i] = jsonArray.getString(i);
//		}
//		return stringArray;
//	}
	public static String[] getStringArray4Json(String jsonString) {
		JsonArray jsonArray = new JsonArray();
		String[] stringArray = new String[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++)
			stringArray[i] = jsonArray.getAsString();
		return stringArray;
	}
	
}
