package com.mx.core.listener;

import java.io.PrintStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mx.core.common.Config;

/**
 * 生产环境中把在程序中的System.out.print()的信息自动转成日志信息
 */
public class StdoutListener implements ServletContextListener {
	private static final Logger logger = LogManager.getLogger("mylogs");
	
	public void contextDestroyed(ServletContextEvent event) {
	}

	private void log(Object info) {
		logger.info(info);
	}

	public void contextInitialized(ServletContextEvent event) {
		if("false".equals(Config.getString("runningDebug"))){
			PrintStream printStream = new PrintStream(System.out) {
				public void println(boolean x) {
					log(Boolean.valueOf(x));
				}
				
				public void println(char x) {
					log(Character.valueOf(x));
				}
				
				public void println(char[] x) {
					log(x == null ? null : new String(x));
				}
				
				public void println(double x) {
					log(Double.valueOf(x));
				}
				
				public void println(float x) {
					log(Float.valueOf(x));
				}
				
				public void println(int x) {
					log(Integer.valueOf(x));
				}
				
				public void println(long x) {
					log(x);
				}
				
				public void println(Object x) {
					log(x);
				}
				
				public void println(String x) {
					log(x);
				}
			};
			System.setOut(printStream);
			System.setErr(printStream);
		}
	}
}
