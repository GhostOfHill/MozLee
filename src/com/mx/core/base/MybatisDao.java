package com.mx.core.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * @Description 实现myBatis与spring 整合，并封装常用的操作数据库方法
 * 		                     注入所有service类的父类baseService类中
 * @author 申龙赫
 * @param <T>
 */
public class MybatisDao<T> extends SqlSessionDaoSupport{
	
	/**
	 * 模型名称
	 */
	@SuppressWarnings("unused")
	private String modelName;

	/**
	 * 设置模型名称
	 * @param modelName
	 *            模型名称
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	// ---------------------------------------------------------------------------------------//
	/**
	 * 查询
	 * @param statementName 节点名
	 * @return List<T>
	 */
	public List<T> queryForList(String statementName) {
		return this.getSqlSession().selectList(statementName);
	}
	
	/**
	 * 查询
	 * @param statementName 节点名
	 * @param parameterObject Sql所需参数
	 * @return List<T>
	 */
	public List<T> queryForList(String statementName, final Object parameterObject) {
		return this.getSqlSession().selectList(statementName, parameterObject);
	}
	
	/**
	 * 查询
	 * @param statementName 节点名
	 * @return List<Map<String, String>>
	 */
	public List<Map<String, String>> queryForListMap(String statementName) {
		return this.getSqlSession().selectList(statementName);
	}
	
	/**
	 * 查询
	 * @param statementName 节点名
	 * @param parameterObject Sql所需参数
	 * @return List<Map<String, String>>
	 */
	public List<Map<String, String>> queryForListMap(String statementName, final Object parameterObject) {
		return this.getSqlSession().selectList(statementName, parameterObject);
	}
	
	/**
	 * 查询
	 * @param statementName 节点名
	 * @param keyProperty key属性名
	 * @param valueProperty 值属性名
	 * @return Map<Object, Object>
	 */
	@SuppressWarnings("rawtypes")
	public Map queryForMap(String statementName, String keyProperty, String valueProperty) {
		return queryForMap(statementName, null, keyProperty, valueProperty);
	}
	
	@SuppressWarnings("rawtypes")
	public Map queryForMap(String statementName, Object parameterObject, String keyProperty) {
		return this.getSqlSession().selectMap(statementName, parameterObject, keyProperty);
	}

	/**
	 * 查询
	 * @param statementName 节点名
	 * @param parameterObject Sql参数
	 * @param keyProperty key属性名
	 * @param valueProperty 值属性名
	 * @return Map<Object, Object>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map queryForMap(String statementName, Object parameterObject, String keyProperty, String valueProperty) {
		Map<String, String> resultMap = new HashMap<String, String>();
		Map<String, Map<String, String>> selectMap = queryForMap(statementName, parameterObject, keyProperty);
		for(String key: selectMap.keySet()) {
			Map<String, String> map = selectMap.get(key);
			resultMap.put(key, map.get(valueProperty));
		}
		return resultMap;
	}

	/**
	 * 查询
	 * @param statementName 节点名
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public T queryForModel(String statementName) {
		return (T) this.getSqlSession().selectOne(statementName);
	}

	/**
	 * 查询
	 * @param statementName 节点名
	 * @param parameterObject Sql参数
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public T queryForModel(String statementName, Object parameterObject) {
		return (T) this.getSqlSession().selectOne(statementName, parameterObject);
	}

	/**
	 * 查询
	 * @param statementName 节点名
	 * @return Object 单个节点
	 */
	public Object queryForObject(String statementName) {
		return this.getSqlSession().selectOne(statementName);
	}

	/**
	 * 查询
	 * @param statementName 节点名
	 * @param parameterObject Sql参数
	 * @return Object 单个节点
	 */
	public Object queryForObject(String statementName, Object parameterObject) {
		return this.getSqlSession().selectOne(statementName, parameterObject);
	}
	
	/**
	 * 分页查询
	 * @param statementName 节点名
	 * @param skipResults 开始位置
	 * @param maxResults 步长
	 * @return List<T>
	 */
	public List<T> queryForList(String statementName, int skipResults, int maxResults) {
		RowBounds rowBounds = new RowBounds(skipResults,maxResults);
		return this.getSqlSession().selectList(statementName, rowBounds);
	}

	/**
	 * 分页查询
	 * @param statementName 节点名
	 * @param parameterObject Sql参数
	 * @param skipResults 开始位置
	 * @param maxResults 步长
	 * @return List<T>
	 */
	public List<T> queryForList(String statementName, Object parameterObject, int skipResults, int maxResults) {
		RowBounds rowBounds = new RowBounds(skipResults,maxResults);
		return this.getSqlSession().selectList(statementName, parameterObject, rowBounds);
	}
	
	/**
	 * 插入
	 * @param statementName 节点名
	 */
	public void insert(String statementName) {
		this.getSqlSession().insert(statementName);
	}

	/**
	 * 插入
	 * @param statementName 节点名
	 * @param parameterObject Sql参数
	 */
	public void insert(String statementName, Object parameterObject) {
		this.getSqlSession().insert(statementName, parameterObject);
	}

	/**
	 * 更新
	 * @param statementName 节点名
	 * @return 是否成功
	 */
	public Integer update(String statementName) {
		return this.getSqlSession().update(statementName);
	}

	/**
	 * 更新
	 * @param statementName 节点名
	 * @param parameterObject Sql参数
	 * @return 是否成功
	 */
	public int update(String statementName, Object parameterObject) {
		return this.getSqlSession().update(statementName, parameterObject);
	}

	/**
	 * 删除
	 * @param statementName 节点名
	 * @return 是否成功
	 */
	public Integer delete(String statementName) {
		return this.getSqlSession().delete(statementName);
	}

	/**
	 * 删除
	 * @param statementName 节点名
	 * @param parameterObject Sql参数
	 * @return 是否成功
	 */
	public Integer delete(String statementName, Object parameterObject) {
		return this.getSqlSession().delete(statementName, parameterObject);
	}
}
