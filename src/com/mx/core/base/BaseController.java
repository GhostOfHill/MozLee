package com.mx.core.base;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.mx.core.common.utils.EncodeUtils;
import com.mx.core.common.utils.JsonUtil;
import com.mx.core.common.utils.ReflectionUtils;
import com.mx.core.common.utils.SpringMVCUtil;
import com.mx.core.common.utils.ViewUtil;

/**
 * 泛型基类 、以及CRUD函数和返回值的命名.封装CRUD基本方法，可在子类进行覆盖重写
 * @author 申龙赫
 * @param <T> 实体类型
 */
public abstract class BaseController<T> {
	/**
	 * model类
	 */
	private Class<T> modelClass = ReflectionUtils.getSuperClassGenricType(getClass());
	
	/**
	 * 获取页面传来的JSON数组
	 * @return List<T>
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public List<T> getModelList() {
		String modelListStr = (String) SpringMVCUtil.getRequest().getParameter("modelList");
		return (List<T>)new JsonUtil().getList4Json(modelListStr, modelClass);
	}
	
	/**
	 * 利用它得到页面传递过来的参数
	 * @return 数据
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T getModel() {
		try {
			T model = modelClass.newInstance();
			BeanWrapper beanWrapper = new BeanWrapperImpl(model);
			Iterator<Map.Entry> it = new TreeMap(SpringMVCUtil.getRequest().getParameterMap()).entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String[]> me = it.next();
				try{
					if(beanWrapper.getPropertyType(me.getKey())==null){
						continue;
					}
					if(beanWrapper.getPropertyType(me.getKey()).equals(Timestamp.class)) {
						String timeValue = Arrays.asList(me.getValue()).get(0);
						Integer lenTimeValue = timeValue.length();
						SimpleDateFormat dateFormat;
						if(lenTimeValue == 19){
							dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						}
						else if(lenTimeValue==10){
							dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						}else{
							continue;
						}
						beanWrapper.setPropertyValue(me.getKey(), new Timestamp(dateFormat.parse(Arrays.asList(me.getValue()).get(0)).getTime()));
					} else {
						beanWrapper.setPropertyValue(me.getKey(), me.getValue());
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			return model;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 接收页面传递过来的数据范围
	 * @return 数据访问范围集合
	 * @throws Exception 初始化异常
	 */
	public List<String> getDataScope() throws Exception {
		String scope = SpringMVCUtil.getRequest().getParameter("dataScope");
		if (scope == null) {
			throw new Exception("请从页面传递数据访问范围！");
		}
		String scopeNew = new String(EncodeUtils.base64Decode(scope)); // 通过了EncodeUtils.urlEncode()编码后解码
		if ("".equals(scopeNew)) {
			throw new Exception("请从页面传递数据访问范围！");
		}
		String[] scopes = scopeNew.split(",");
		if (scopes == null || scopes.length == 0) {
			throw new Exception("请从页面传递数据访问范围！");
		}
		return Arrays.asList(scopes);
	}
	
	/**
	 * 得到向客户端输出的错误串
	 * @param msg 串
	 * @return 错误串
	 */
	protected String getError(final String msg) {
		return "{success: false, msg: \"" + msg + "\"}";
	}

	/**
	 * 得到向客户端输出的成功串
	 * @param msg 串
	 * @return 成功串
	 */
	protected String getSuccess(final String msg) {
		return "{success: true, msg: \"" + msg + "\"}";
	}

	/**
	 * 向客户端输出的消息串
	 * @param msg 串
	 */
	public void renderError(final String msg) {
		this.renderJson(getError(msg));
	}

	/**
	 * 向客户端输出的消息串
	 * @param msg 串
	 */
	public void renderSuccess(final String msg) {
		this.renderJson(getSuccess(msg));
	}
	
	/**
	 * 向客户端输出list中集合的json串
	 * @param list 数据
	 * @param attributes jsonKeys
	 */
	public void renderJson4List(final List<T> list, final String[] attributes) {
		if (list.size() > 0) {
			this.renderJson(ViewUtil.json4list(list, attributes));
		}
	}

	/**
	 * 向客户端输出list中集合的json串
	 * @param list 数据
	 */
	public void renderJson4List(final List<T> list) {
		if (list.size() > 0) {
			this.renderJson(JsonUtil.gson.toJson(list));
		}
	}
	
	/**
	 * 返回list中集合的json串
	 * @param list 数据
	 */
	public String returnJson4List(final List<T> list) {
		if (list.size() > 0) {
			return JsonUtil.gson.toJson(list);
		} else {
			return "";
		}
	}
	
	/**
	 *  向客户端表单输出实体的json串
	 * @param model 实体
	 * @param attributes 需要显示的字段
	 * @param map 用来扩展的map对象
	 */
	public void renderJson4Model(final T model, final String[] attributes, final Map<String, String> map) {
		this.renderJson(ViewUtil.form4object(model, attributes, map));
	}

	/**
	 *  向客户端表单输出实体的json串
	 * @param model 实体
	 * @param attributes 需要显示的字段
	 */
	public void renderJson4Model(final T model, final String[] attributes) {
		String data = ViewUtil.json4model(model, attributes);
		this.renderJson("{" + data + "}");
	}
	
	/**
	 *  向客户端表单输出实体的json串
	 * @param model 实体
	 */
	public void renderJson4Model(final T model) {
		this.renderJson(this.returnJson4Model(model));
	}
	
	/**
	 *  返回实体的json串
	 * @param model 实体
	 */
	public String returnJson4Model(final T model) {
		return JsonUtil.gson.toJson(model);
	}

	/**
	 * @param map 数据
	 */
	public void render4Map(final Map<String, String> map) {
		this.renderJson(JsonUtil.getJsonString4JavaModel(map));
	}
	
	/**
	 *  向客户端输出json数据
	 * @param json 文本
	 */
	public void renderJson(final String json) {
		SpringMVCUtil.renderJson(json);
	}

	/**
	 *  直接输出XML.
	 * @param xml 文本
	 */
	public void renderXML(final String xml) {
		SpringMVCUtil.renderXml(xml);
	}

	/**
	 *  直接输出text文本
	 * @param text 文本
	 */
	public void renderText(final String text) {
		SpringMVCUtil.renderText(text);
	}
	
	/**
	 *  获取登录用户的usersession
	 * @param request
	 * @return UserSession
	 */
	/*protected UserSession getUserSession() {
		UserSession us = UserSessionUtil.getUserSession();
		if (us == null) {
			us = new UserSession();
		}
		return us;
	}*/
	
	/**
	 * 拷贝session中数据至model 如果model中数据不为''或null或'null'的话才拷贝
	 * @param t
	 *            Model类型
	 * @return T Model
	 */
	/*protected T copySessionToModel(final T t) {
		UserSession userSession = this.getUserSession();
		BeanWrapper userSessionBW = new BeanWrapperImpl(userSession);
		BeanWrapper tBW = new BeanWrapperImpl(t);
		PropertyDescriptor[] propertyDescriptors = userSessionBW.getPropertyDescriptors();
		for (int i = 1; i < propertyDescriptors.length; i++) {
			String name = propertyDescriptors[i].getName();
			try {
				Object nowValue = tBW.getPropertyValue(name);
				if (nowValue == null || "null".equals(nowValue.toString()) || "".equals(nowValue.toString())) {
					tBW.setPropertyValue(name, userSessionBW.getPropertyValue(name));
				}
			} catch (Exception e) {
			}
		}
		return t;
	}*/
	
	/**
	 *  返回分页表格的json
	 * @param service 业务服务类
	 * @param model 模型对象
	 * @param countMethodName 统计条数的方法名
	 * @param listMethodName 查询列表的方法名
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void renderPagingTable(final Object service, final T model, final String countMethodName, final String listMethodName) {
		HttpServletRequest request = SpringMVCUtil.getRequest();
		String sEcho = (String) request.getParameter("sEcho");
		int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
		int count = 0;
		try {
			Method countMethod;
			countMethod = service.getClass().getDeclaredMethod(countMethodName, new Class[] { model.getClass() });
			count = ((Number) countMethod.invoke(service, new Object[] { model })).intValue();
			Method listMethod = service.getClass().getDeclaredMethod(listMethodName, new Class[] { model.getClass(), int.class, int.class });
			List<T> list = (List<T>) listMethod.invoke(service, new Object[] { model, iDisplayStart, iDisplayLength < count ? iDisplayLength : count });
			String json = toTablePageJson(list, sEcho, count, iDisplayStart, iDisplayLength);
			this.renderText(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回表格的json
	 * @param list 对象列表
	 */
	public void renderTable(final List<T> list) {
		this.renderText(toTablePageJson(list, "-1", -1, -1, -1));
	}
	
	/**
	 * 进行返回数据前的json转换
	 * @param list 数据
	 * @param sEcho 显示数量
	 * @param count 总数
	 * @param iDisplayStart 显示起点
	 * @param iDisplayLength 显示行数
	 * @return 表格分页数据
	 */
	private String toTablePageJson(final List<T> list, final String sEcho, final int count, final int iDisplayStart, final int iDisplayLength) {
		return "{\"sEcho\":"
				+ sEcho
				+ (iDisplayStart == -1 ? "" : ",\"iDisplayStart\":" + iDisplayStart + ",\"iDisplayLength\":" + iDisplayLength + ",\"iTotalRecords\":" + count
						+ ",\"iTotalDisplayRecords\":" + count) + ",\"aaData\":" + JsonUtil.gson.toJson(list) + "}";
	}
	
	/**
	 * @param list 数据
	 * @param textName 文本模型名
	 * @param idName ID模型名
	 * @param parentIdName 父节点ID模型名
	 * @param attributes 隐藏属性
	 */
	public void renderList4Tree(final List<T> list, final String textName, final String idName, final String parentIdName, final String[] attributes) {
		this.renderText(this.returnList4Tree(list, textName, idName, parentIdName, attributes));
	}
	
	/**
	 * @param list 数据
	 * @param textName 文本模型名
	 * @param idName ID模型名
	 * @param parentIdName 父节点ID模型名
	 * @param attributes 隐藏属性
	 * @return string
	 */
	public String returnList4Tree(final List<T> list, final String textName, final String idName, final String parentIdName, final String[] attributes) {
		for (int i = 0; i < list.size(); i++) {
			T t = list.get(i);
			BeanWrapper tBean = new BeanWrapperImpl(t);
			tBean.setPropertyValue("_treeName", tBean.getPropertyValue(textName).toString());
			tBean.setPropertyValue("_treeId", tBean.getPropertyValue(idName).toString());
			tBean.setPropertyValue("_treeParentId", tBean.getPropertyValue(parentIdName).toString());
		}
		String[] attrs = new String[attributes.length + 3];
		attrs[0] = "_treeId";
		attrs[1] = "_treeName";
		attrs[2] = "_treeParentId";
		for (int i = 0; i < attributes.length; i++) {
			attrs[i + 3] = attributes[i];
		}
		return ViewUtil.json4list(list, attrs);
	}

	/**
	 * @param list 数据
	 * @param textName 文本模型名
	 * @param idName ID模型名
	 * @param attributes 隐藏属性
	 */
	public void renderList4Tree(final List<T> list, final String textName, final String idName, final String[] attributes) {
		StringBuffer json = new StringBuffer("");
		for (int i = 0; i < list.size(); i++) {
			T t = list.get(i);
			BeanWrapper tBean = new BeanWrapperImpl(t);
			json.append("{");
			json.append("\"id\"").append(":").append("\"" + tBean.getPropertyValue(idName).toString() + "\",");
			json.append("\"text\"").append(":").append("\"" + tBean.getPropertyValue(textName).toString() + "\",");
			json.append("\"attributes\"").append(":").append("{");
			for (int j = 0; j < attributes.length; j++) {
				json.append("\"").append(attributes[j]).append("\":\"").append(tBean.getPropertyValue(attributes[j])).append("\"").append(j + 1 == attributes.length ? "" : ",");
			}
			json.append("}");
			json.append(",\"state\":\"closed\"");
			json.append(",\"children\"").append(":[").append("").append("]");
			json.append("},");
		}
		if (json.length() > 1) {
			json.deleteCharAt(json.length() - 1);
		}
		this.renderText("[" + json.toString() + "]");
	}

	/**
	 * 将List中数据转换为树结点JSON数据，主方法
	 * @param list 数据
	 * @param attributes 树隐藏属性
	 * @param layers 层级
	 * @param treelayers 树总层级
	 * @return 树节点数据
	 */
	@SuppressWarnings("unused")
	private String renderList4TreeNode(final List<T> list, final String[] attributes, final String layers, final String treelayers) {
		StringBuffer json = new StringBuffer("");
		for (int i = 0; i < list.size(); i++) {
			T t = list.get(i);
			BeanWrapper tBean = new BeanWrapperImpl(t);
			String tId = tBean.getPropertyValue("id").toString();
			json.append("{");
			json.append("\"id\"").append(":").append("\"" + tId + "\",");
			json.append("\"text\"").append(":").append("\"" + tBean.getPropertyValue("text").toString() + "\",");
			json.append("\"attributes\"").append(":").append("{");
			for (int j = 0; j < attributes.length; j++) {
				json.append("\"").append(attributes[j]).append("\":\"").append(tBean.getPropertyValue(attributes[j])).append("\"").append(j + 1 == attributes.length ? "" : ",");
			}
			json.append("}");
			if (!treelayers.equals(layers) && "1".equals(tBean.getPropertyValue("treeChildren").toString())) {
				json.append(",\"state\":\"closed\"");
				json.append(",\"children\"").append(":[").append("").append("]");
			}
			json.append("},");
		}
		if (json.length() > 1) {
			json.deleteCharAt(json.length() - 1);
		}
		return json.toString();
	}
	
	/**
	 * 将List中数据转换为树JSON数据，主方法 LIST中ID与TEXT为必传值，CHECKED为默认是否选中
	 * @param list
	 * @param attributes
	 * @param no 层级数
	 * @param id所属层级id 空则第一级
	 * @return 树JSON数据
	 */
	@SuppressWarnings("unused")
	private String renderList4Treejson(List<T> list, String[] attributes, int no, String... id) {
		StringBuffer json = new StringBuffer("");
		for (int i = 0; i < list.size(); i++) {
			T t = list.get(i);
			BeanWrapper tBean = new BeanWrapperImpl(t);
			String tId = tBean.getPropertyValue("id").toString();
			String tQybh = tBean.getPropertyValue("qybh") == null ? "" : tBean.getPropertyValue("qybh").toString();
			if (tId.length() == no * 3 && (id == null || id.length == 0 || tId.indexOf(id[0]) == 0)) {
				json.append("{");
				json.append("\"id\"").append(":").append("\"" + tId + "\",");
				json.append("\"qybh\"").append(":").append("\"" + tQybh + "\",");
				json.append("\"text\"").append(":").append("\"" + tBean.getPropertyValue("text").toString() + "\",");
				json.append("\"attributes\"").append(":").append("{");
				for (int j = 0; j < attributes.length; j++) {
					json.append("\"").append(attributes[j]).append("\":\"").append(tBean.getPropertyValue(attributes[j])).append("\"").append(j + 1 == attributes.length ? "" : ",");
				}
				json.append("}");
				String jsonBlock = renderList4Treejson(list, attributes, no + 1, tId);
				if (jsonBlock != null && !"".equals(jsonBlock)) {
					json.append(",\"state\":\"closed\"");
					json.append(",\"children\"").append(":[").append(jsonBlock).append("]");
				} else if (Boolean.valueOf(tBean.getPropertyValue("checked") + "")) {
					json.append(",\"checked\":true");
				}
				json.append("},");
			}
		}
		if (json.length() > 1) {
			json.deleteCharAt(json.length() - 1);
		}
		return json.toString();
	}
	
	/**
	 * 回传excel
	 * @param bytes
	 *            数据
	 * @param fileName
	 *            文件名称
	 */
	public void renderExcel(final byte[] bytes, final String fileName) {
		HttpServletResponse response = SpringMVCUtil.getResponse();
		try {
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("GBK"), "ISO_8859_1") + ".xls");
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			try {
				OutputStream out = response.getOutputStream();
				out.write(bytes);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * @Description: 将字节发送到客户端
	 * @param b
	 *            发送的内容
	 * @param filename
	 *            文件名
	 */
	public void renderByte(byte[] b, String filename) {
		SpringMVCUtil.renderByte(b, filename);
	}
	
	/**
	 * 获取IP地址
	 * @return IP
	 */
	public String getIpAddr() {
		HttpServletRequest request = SpringMVCUtil.getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
