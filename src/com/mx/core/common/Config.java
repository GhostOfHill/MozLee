package com.mx.core.common;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.mx.core.common.utils.FileUtils;

/**
 * @Description: config
 * @date 2016-09-25
 * @author 申龙赫
 */
public class Config {
	private static final String BUNDLE_NAME = "config";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	
	private static Map<String, String> configMap = new HashMap<String, String>();

	private Config() {}
	
	public static String getString(String key) {
		try {
			if(configMap.get(key) == null)
				configMap.put(key, RESOURCE_BUNDLE.getString(key));
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
		return configMap.get(key);
	}
	
	public static void setString(String key, String value) {
		configMap.put(key, value);
		FileUtils fileUtils = new FileUtils();
		String fileString = fileUtils.readTruePathFile(getString("projectPath")+"config.properties");
		fileUtils.write(fileString.replaceAll(key+"=.*", key+"="+value), getString("projectPath")+"config.properties");
	}
	
	
	public static void main(String[] args) {
		System.out.println(Config.getString("login"));
	}
}
