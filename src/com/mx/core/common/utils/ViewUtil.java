package com.mx.core.common.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @Description: 与页面表格、表格树、树等待相关展示构件相关的工具类
 */
public class ViewUtil {
	/**
	 * 生成data数据格式为"data":{"name":"d","age":"20","aa":"d"}
	 * @param obj
	 * @param attributes
	 * @return
	 */
	public static String json4model(Object obj, String[] attributes) {
		if (obj == null || attributes == null) return "";
		StringBuffer json = new StringBuffer();
		json.append("data").append(":{");
		BeanWrapper model = new BeanWrapperImpl(obj);
		int len = attributes.length;
		for (int j = 0; j < len; j++) {
			String attributeName = attributes[j];
			Object attributeValue = model.getPropertyValue(attributeName);
			attributeValue = toString(attributeValue);
			String attrVal = (attributeValue == null) ? "" : attributeValue.toString();
			json.append("\"" + attributeName + "\"").append(":");
			json.append("\"" + attrVal + "\"");
			if (j < len - 1) {
				json.append(",");
			}
		}
		json.append("}");
		return json.toString();
	}

	/**
	 * 生成data数据格式为[{"name":"d","age":"20","aa":"d"},{"name":"aa","age":"10","aa"
	 * :"ee"}]
	 * @param obj
	 * @param attributes
	 * @return
	 */
	public static <T> String json4list(List<T> list, String[] attributes) {
		if (list == null || list.size() == 0 || attributes == null) return "";
		StringBuffer json = new StringBuffer();
		json.append("[");
		for (int i = 0; i < list.size(); i++) {
			json.append("{");
			BeanWrapper model = new BeanWrapperImpl(list.get(i));
			int len = attributes.length;
			for (int j = 0; j < len; j++) {
				String attributeName = attributes[j];
				Object attributeValue = model.getPropertyValue(attributeName);
				attributeValue = attributeValue == null ? "" : attributeValue.toString();
				String attrVal = (attributeValue == null) ? "" : attributeValue.toString();
				json.append("\"" + attributeName + "\"").append(":");
				if (model.getPropertyType(attributeName) == Boolean.class) {
					json.append("" + attrVal + "");
				} else {
					json.append("\"" + attrVal + "\"");
				}
				if (j < len - 1) {
					json.append(",");
				}
			}
			json.append("}");
			if (i < list.size() - 1) {
				json.append(",");
			}
		}
		json.append("]");
		return json.toString();
	}
	/**
	 * 把任何类型的value转成String 0 null则返回空字符串 "" 1 时间按照 yyyy-MM-dd格式 2 数字按照
	 * #,##0.00格式 3 其他返回 value.toString()
	 */
	public static String toString(Object value) {
		if (value == null) {// null
			return "";
		}
		if (value instanceof Date) {// 时间 yyyy-MM-dd
			return new SimpleDateFormat("yyyy-MM-dd").format((Date) value);
		}
		// 整数之间返回
		if (value instanceof Integer || value instanceof Short || value instanceof Long) {
			return value.toString();
		}
		if (value instanceof Number) {// 数字 #,##0.0,若是0,显示0.00,否则显示每三位用,分隔
			value = new DecimalFormat("#,##0.00").format(value);
		}
		// 其他类型
		return value.toString();
	}
	/**
	 * 用来在页面中显示form表单时使用 * 生成json数据格式为 {
	 * "data":{"name":"d","age":"20","aa":"d"}, "ext":{"ch":[1,2,1],"re":[1,3]}
	 * }
	 * @param obj
	 *            model对象
	 * @param attributes
	 *            需要显示的字段
	 * @param map
	 *            用来扩展的map对象
	 * @return
	 */
	public static String form4object(Object obj, String[] attributes, Map<String, String> map) {
		String data = json4model(obj, attributes);
		String ext = json4ext(map);
		return "{" + data + "," + ext + "}";
	}
	
	/**
	 * 用来作为表单的扩展 比如 多选框中名字为ch，值为1 、2 的复选框选时 key："ch" ,value:"1,2"
	 * 组织数据为"ext":{"ch":[1,2],"re":[1,3]}
	 * @param obj
	 * @param attributes
	 * @return
	 */
	public static String json4ext(Map<String, String> map) {
		StringBuffer json = new StringBuffer();
		json.append("ext").append(":{");
		Set<Map.Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		int i = 0;
		int len = set.size();
		while (it.hasNext()) {
			Map.Entry<String, String> en = it.next();
			String key = en.getKey();
			String value = en.getValue();
			json.append("\"" + key + "\"").append(":");
			json.append("[" + value + "]");
			if (i < len - 1) {
				json.append(",");
			}
			i++;
		}
		json.append("}");
		return json.toString();
	}

}
