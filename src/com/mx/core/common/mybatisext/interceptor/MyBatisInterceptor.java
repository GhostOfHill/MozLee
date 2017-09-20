package com.mx.core.common.mybatisext.interceptor;

public interface MyBatisInterceptor {
	public Object invoke(MyBatisInvocation handler) throws Throwable;
}
