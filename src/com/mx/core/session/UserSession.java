package com.mx.core.session;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserSession implements Serializable {
	private String menu; // 菜单
	private String treePath; // 左侧树的路径
	private String userId;// 用户编号
	private String companyName;//企业名称
	
	private String firstName;// 名称
	private String lastName;// 姓氏
	private String companyCode;//企业编码
	private String organName;// 组织名称
	private String organCode;// 组织编码
	private String loginName;// 登录名称
	private String systemSessionSign;// 系统Session标志
	private Boolean updateUserCache;// 通知页面更新用户缓存
	private Boolean updatePubCache;// 通知页面更新公共缓存
	private String loginState;// 登录状态
	private String systemType;// 系统类型
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getTreePath() {
		return treePath;
	}
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getOrganCode() {
		return organCode;
	}
	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getSystemSessionSign() {
		return systemSessionSign;
	}
	public void setSystemSessionSign(String systemSessionSign) {
		this.systemSessionSign = systemSessionSign;
	}
	public Boolean getUpdateUserCache() {
		return updateUserCache;
	}
	public void setUpdateUserCache(Boolean updateUserCache) {
		this.updateUserCache = updateUserCache;
	}
	public Boolean getUpdatePubCache() {
		return updatePubCache;
	}
	public void setUpdatePubCache(Boolean updatePubCache) {
		this.updatePubCache = updatePubCache;
	}
	public String getLoginState() {
		return loginState;
	}
	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}
	public String getSystemType() {
		return systemType;
	}
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

}
