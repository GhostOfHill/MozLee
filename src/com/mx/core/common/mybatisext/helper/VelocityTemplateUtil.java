package com.mx.core.common.mybatisext.helper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityTemplateUtil {
	static {
		Properties p = new Properties();
		p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");
		p.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
		p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
		p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
		p.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
		try {
			Velocity.init(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getConfiguration(Map<Object, Object> map, String templateName) {
		VelocityContext context = new VelocityContext(map);
		StringWriter writer = new StringWriter();
		try {
			Velocity.getTemplate(templateName).merge(context, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer.toString();
	}

	/**
	 * 使用velocity 替换模板字符串中的变量
	 * <p>
	 * @param templateStr
	 * @param map
	 * @return
	 */
	public static String getTemplateString(String templateStr, Map<Object, Object> map) throws IOException {
		StringWriter write = new StringWriter();
//		try {
//			Velocity.evaluate(new VelocityContext(map), write, "", templateStr);
//		} catch (ParseErrorException | MethodInvocationException | ResourceNotFoundException e) {
//			e.printStackTrace();
//		}
		return write.toString();
	}
}
