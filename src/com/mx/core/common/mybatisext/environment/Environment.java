package com.mx.core.common.mybatisext.environment;

public enum Environment {
	LOCAL("本地环境", "LOCAL"), PRODUCT("产品环境", "PRODUCT");
	String name;
	String code;

	private Environment(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public String getCode() {
		return this.code;
	}

	public boolean isLocal() {
		return EnvironmentDetect.LOCAL.equals(this.code);
	}

	public boolean isProduct() {
		return EnvironmentDetect.PRODUCT.equals(this.code);
	}
}
