package com.mx.core.base;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import redis.clients.jedis.JedisCluster;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * redisDao 主要操作redis内存数据库
 * @author ljp
 * @date 2015年7月30日
 */
public class RedisDao {
	
	private JedisCluster jedisCluster;	//注入jedisCluster对象

	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}
	
	/**
	 * 存储key-field-value字符串
	 * @param key
	 * @param field
	 * @param value
	 * @author ljp
	 * @date 2015年7月30日
	 */
	public void set(String key,String field,String value){
		jedisCluster.hset(key, field, value == null ? "" : value);
	} 
	
	/**
	 * 存储key-value字符串
	 * @param key
	 * @param value
	 * @return
	 * @author ljp
	 * @date 2015年7月30日
	 */
	public boolean set(String key,String value){
		String result = jedisCluster.set(key, value == null ? "" : value);
		if("OK".equals(result)){
			return true;
		}else{
			return false;
		}
	} 
	/**
	 * 存储key-map结构
	 * @param key
	 * @param value
	 * @return
	 * @author ljp
	 * @date 2015年7月30日
	 */
	public boolean set(String key,Map<String, String> map){
		if(map == null || map.isEmpty()){
			return false;
		}
		String result = jedisCluster.hmset(key, map);
		if("OK".equals(result)){
			return true;
		}else{
			return false;
		}
	} 
	
	/**
	 * 存储map结构
	 * @param map
	 * @return
	 * @author ljp
	 * @date 2015年7月30日
	 */
	public void setMap(Map<String, String> map){
		for(Entry<String, String> e : map.entrySet()){
			this.set(e.getKey(),e.getValue());
		}
	} 
	
	/**
	 * 
	 * 获取字符串
	 * @param key
	 * @return
	 * @author ljp
	 * @date 2015年7月30日
	 */
	public String get(String key){
		return this.jedisCluster.get(key);
	}
	
	/**
	 * 
	 * 获取map中的value
	 * @param key
	 * @param mapKey
	 * @return
	 * @author ljp
	 * @date 2015年7月30日
	 */
	public String get(String key,String mapKey){
		return this.jedisCluster.hget(key, mapKey);
	}
	
	/**
	 * 
	 * 获取map结构
	 * @param key
	 * @return
	 * @author ljp
	 * @date 2015年7月30日
	 */
	public Map<String, String> getMap(String key){
		Map<String, String> map = this.jedisCluster.hgetAll(key);
		return map;
	}
	
	/**
	 * 是否存在该key
	 * @param key
	 * @return
	 * @author ljp
	 * @date 2015年7月30日
	 */
	public boolean exists(String key){
		return this.jedisCluster.exists(key);
	}
	
	/**
	 * 是否存在该key-filed
	 * @param key
	 * @return
	 * @author ljp
	 * @date 2015年7月30日
	 */
	public boolean exists(String key,String field){
		return this.jedisCluster.hexists(key, field);
	}
	
	/**
	 * 删除key
	 * @param key
	 * @return
	 * @author ljp
	 * @date 2015年7月30日
	 */
	public boolean del(String key){
		long result = this.jedisCluster.del(key);
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 删除key-field
	 * @param key
	 * @return
	 * @author ljp
	 * @date 2015年7月30日
	 */
	public boolean del(String key,String field){
		long result = this.jedisCluster.hdel(key,field);
		if(result == 0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 在名称为key的list头添加一个值为value的 元素
	 * @param key
	 * @param str
	 * @author jw
	 * @date 2016-3-28 
	 */
	public boolean lpush(String key,String... str){
		long i=this.jedisCluster.lpush(key, str);
		if(i==str.length){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 返回并删除名称为key的list中的首元素
	 * @param key
	 * @return
	 * @author jw
	 * @date 2016-3-28 
	 */
	public String lpop(String key){
		return this.jedisCluster.lpop(key);
	}
	/**
	 * 在名称为key的list尾添加一个值为value的元素
	 * @param key
	 * @param str
	 * @author jw
	 * @date 2016-3-28 
	 */
	public boolean rpush(String key,String... str){
		long i=this.jedisCluster.rpush(key, str);
		if(i==str.length){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 返回并删除名称为key的list中的尾元素
	 * @param key
	 * @return
	 * @author jw
	 * @date 2016-3-28 
	 */
	public String rpop(String key){
		return this.jedisCluster.rpop(key);
	}
	
	/**
	 * 根据用户名和表名从redis中获取数据结构
	 * @param userName redis抽象的用户名
	 * @param tableName redis抽象的表名
	 * @return
	 * @author ljp
	 * @date 2015年10月26日
	 */
	public Map<String, String> getMetadata(String userName,String tableName){
		StringBuffer sb = new StringBuffer("81_");
		sb.append(userName);
		sb.append("_");
		sb.append(tableName);
		Map<String, String> metadataMap = this.getMap(sb.toString());
		return metadataMap;
	}
	
	/**
	 * 根据数据元信息和主键从redis中获取数据
	 * @param metadataMap 数据元信息
	 * @param pk 数据记录主键
	 * @return
	 * @author ljp
	 * @date 2015年10月26日
	 */
	public Map<String, String> getDataRecode(Map<String, String> metadataMap,String pk){
		String dataKey = getDataKey(metadataMap,pk);
		Map<String, String> dataMap = this.getMap(dataKey);
		return dataMap;
	}
	
	/**
	 * 根据数据元信息和数据主键获取redis的数据key
	 * @param metadataMap 数据元信息
	 * @param pk 数据库主键
	 * @return
	 * @author ljp
	 * @date 2015年10月26日
	 */
	public String getDataKey(Map<String, String> metadataMap,String pk){
		StringBuffer encodeKey = new StringBuffer("9_");
		encodeKey.append(metadataMap.get("0"));
		encodeKey.append("_");
		encodeKey.append(pk);
		return encodeKey.toString();
	}
	
	/**
	 * 根据数据库主键从redis中获取一条数据记录并封装成对象
	 * @param userName redis抽象的用户名
	 * @param tableName redis抽象的表名
	 * @param pk 数据库主键
	 * @param obj 封装的对象
	 * @param fileds 对象的属性数组
	 * @return
	 * @author ljp
	 * @date 2015年10月26日
	 */
	public Object getRedis2Object(String userName,String tableName,String pk,Object obj,String[] fileds){
		if(StringUtils.isBlank(userName) || StringUtils.isBlank(tableName) || 
				StringUtils.isBlank(pk)){
			return obj;
		}
		//获得数据结构
		Map<String, String> metadataMap = this.getMetadata(userName,tableName);
		//获得数据记录
		Map<String, String> dataMap = this.getDataRecode(metadataMap,pk);
		//封装对象
		BeanWrapper bean = new BeanWrapperImpl(obj);
		if(fileds == null || fileds.length == 0){
			for (Map.Entry<String, String> entry:metadataMap.entrySet()) {
				String metaKey = entry.getKey();
				if("0".equals(metaKey)){
					continue;
				}
				String metaValue = entry.getValue();
				String dataValue = dataMap.get(metaValue);
				if(dataValue != null && !"".equals(dataValue)){
					try {
						byte[] base64 = new BASE64Decoder().decodeBuffer(dataValue);
						dataValue = new String(base64, "GBK");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				bean.setPropertyValue(metaKey.toLowerCase(),dataValue);
			}
			return obj;
		}
		for (String filed : fileds) {
			String encodeFiled = metadataMap.get(filed.toUpperCase());
			String filedValue = dataMap.get(encodeFiled);
			if(filedValue != null && !"".equals(filedValue)){
				try {
					byte[] base64 = new BASE64Decoder().decodeBuffer(filedValue);
					filedValue = new String(base64, "GBK");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bean.setPropertyValue(filed,filedValue);
		}
		return obj;
	}
	
	/**
	 * 根据数据库主键从redis中获取一条数据记录并封装成对象
	 * @param userName redis抽象的用户名
	 * @param tableName redis抽象的表名
	 * @param pk 数据库主键
	 * @param obj 封装的对象
	 * @return
	 * @author ljp
	 * @date 2015年10月26日
	 */
	public Object getRedis2Object(String userName,String tableName,String pk,Object obj){
		return this.getRedis2Object(userName, tableName, pk, obj,new String[]{});
	}
	
	/**
	 * 根据数据库主键从redis中获取一条数据记录并封装成对象
	 * @param userName redis抽象的用户名
	 * @param tableName redis抽象的表名
	 * @param pk 数据库主键
	 * @param cls 封装的对象的class
	 * @param fileds 对象的属性名，多个以“,”隔开
	 * @return
	 * @author ljp
	 * @date 2015年10月26日
	 */
	public <T> T getRedis2Object(String userName,String tableName,String pk,Class<T> cls,String fileds){
		T obj = null;
		try {
			obj = cls.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if(StringUtils.isBlank(fileds)){
			return (T)this.getRedis2Object(userName, tableName, pk, obj);
		}
		return (T)this.getRedis2Object(userName, tableName, pk, obj, fileds.split(","));
	}
	
	/**
	 * 根据数据库主键从redis中获取一条数据记录并封装成对象
	 * @param userName redis抽象的用户名
	 * @param tableName redis抽象的表名
	 * @param pk 数据库主键
	 * @param cls 封装的对象的class
	 * @return
	 * @author ljp
	 * @date 2015年10月26日
	 */
	public <T> T getRedis2Object(String userName,String tableName,String pk,Class<T> cls){
		return this.getRedis2Object(userName, tableName, pk, cls,"");
	}
	
	/**
	 * 根据数据库主键从redis中获取一条数据记录并封装成对象
	 * @param userName redis抽象的用户名
	 * @param tableName redis抽象的表名
	 * @param pk 数据库主键
	 * @param obj 封装的对象
	 * @param fileds 对象的属性名，多个以“,”隔开
	 * @return
	 * @author ljp
	 * @date 2015年10月26日
	 */
	public Object getRedis2Object(String userName,String tableName,String pk,Object obj,String fileds){
		if(StringUtils.isBlank(fileds)){
			return  this.getRedis2Object(userName, tableName, pk, obj);
		}
		return this.getRedis2Object(userName, tableName, pk, obj, fileds.split(","));
	}
	
	/**
	 * 根据数据库主键从redis中获取一条数据记录的任一字段值
	 * @param userName redis抽象的用户名
	 * @param tableName redis抽象的表名
	 * @param pk 数据库主键
	 * @param filed 字段名称
	 * @return
	 * @author ljp
	 * @date 2015年10月26日
	 */
	public String getRedis2String(String userName,String tableName,String pk,String filed){
		if(StringUtils.isBlank(userName) || StringUtils.isBlank(tableName) || 
				StringUtils.isBlank(pk) || StringUtils.isBlank(filed)){
			return "";
		}
		//获得数据结构
		Map<String, String> metadataMap = this.getMetadata(userName,tableName);
		//获得数据记录
		Map<String, String> dataMap = this.getDataRecode(metadataMap,pk);
		String filedValue = dataMap.get(metadataMap.get(filed));
		if(filedValue != null && !"".equals(filedValue)){
			try {
				byte[] base64 = new BASE64Decoder().decodeBuffer(filedValue);
				filedValue = new String(base64, "GBK");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return filedValue;
	}
	
	/**
	 * 根据数据库主键通过对象更新redis中的一条数据记录
	 * @param userName redis抽象的用户名
	 * @param tableName redis抽象的表名
	 * @param pk 数据库主键
	 * @param obj 对象实例
	 * @param fileds 对象的属性名数组
	 * @author ljp
	 * @date 2015年10月26日
	 */
	public void setObject2Redis(String userName,String tableName,String pk,Object obj,String[] fileds){
		if(StringUtils.isBlank(userName) || StringUtils.isBlank(tableName) || 
				StringUtils.isBlank(pk) || obj == null || 
				fileds == null || fileds.length == 0){
			return;
		}
		//获得数据结构
		Map<String, String> metadataMap = this.getMetadata(userName,tableName);
		String dataKey = this.getDataKey(metadataMap, pk);
		BeanWrapper bean = new BeanWrapperImpl(obj);
		for (String filed : fileds) {
			Object valueObj = bean.getPropertyValue(filed);
			String filedVal = valueObj == null ? "" : valueObj.toString();
			try {
				filedVal = new BASE64Encoder().encode(filedVal.getBytes("GBK"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String encodeFiled = metadataMap.get(filed.toUpperCase());
			set(dataKey, encodeFiled, filedVal);
		}
	}

	/**
	 * 根据数据库主键通过对象更新redis中的一条数据记录
	 * @param userName redis抽象的用户名
	 * @param tableName redis抽象的表名
	 * @param pk 数据库主键
	 * @param obj 对象实例
	 * @param fileds 对象的属性名，多个以“,”隔开
	 * @author ljp
	 * @date 2015年10月26日
	 */
	public void setObject2Redis(String userName,String tableName,String pk,Object obj,String fileds){
		if(StringUtils.isBlank(fileds)){
			return;
		}
		setObject2Redis(userName, tableName, pk, obj, fileds.split(","));
	}
	
	/**
	 * @Description 根据数据库主键通过Map结构更新redis中的一条数据记录
	 * @param userName redis抽象的用户名
	 * @param tableName redis抽象的表名
	 * @param pk 数据库主键
	 * @param dataMap 更新数据的map结构，key为大写redis字段名，value为实际值
	 * @auth ljp
	 * @date 2015年12月1日 下午1:24:32
	 */
	public void setMap2Redis(String userName,String tableName,String pk,Map<String, String> dataMap){
		if(StringUtils.isBlank(userName) || StringUtils.isBlank(tableName) || 
				StringUtils.isBlank(pk) || dataMap == null || dataMap.isEmpty()){
			return;
		}
		//获得数据结构
		Map<String, String> metadataMap = this.getMetadata(userName,tableName);
		String dataKey = this.getDataKey(metadataMap, pk);
		for (Entry<String, String> entry : dataMap.entrySet()){
			String filed = entry.getKey();
			String metaValue = metadataMap.get(filed.toUpperCase());
			String dataValue = entry.getValue();
			try {
				dataValue = new BASE64Encoder().encode(dataValue.getBytes("GBK"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			set(dataKey, metaValue, dataValue);
		}
	}
	
	/**
	 * @Description 根据数据库主键从redis中获取一条数据记录并封装成map结构
	 * @param userName redis抽象的用户名
	 * @param tableName redis抽象的表名
	 * @param pk 数据库主键
	 * @return
	 * @auth ljp
	 * @date 2015年11月30日 上午11:32:54
	 */
	public Map<String, String> getRedis2Map(String userName,String tableName,String pk){
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.isBlank(userName) || StringUtils.isBlank(tableName) || 
				StringUtils.isBlank(pk)){
			return map;
		}
		//获得数据结构
		Map<String, String> metadataMap = this.getMetadata(userName,tableName);
		//获得数据记录
		Map<String, String> dataMap = this.getDataRecode(metadataMap,pk);
		for (Map.Entry<String, String> entry:metadataMap.entrySet()) {
			String metaKey = entry.getKey();
			if("0".equals(metaKey)){
				continue;
			}
			String metaValue = entry.getValue();
			String dataValue = dataMap.get(metaValue);
			if(dataValue != null && !"".equals(dataValue)){
				try {
					byte[] base64 = new BASE64Decoder().decodeBuffer(dataValue);
					dataValue = new String(base64, "GBK");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			map.put(metaKey, dataValue);
		}
		return map;
	}
	
	/**
	 * @Description 根据数据库主键从redis中删除一条数据记录
	 * @param userName redis抽象的用户名
	 * @param tableName redis抽象的表名
	 * @param pk 数据库主键
	 * @return
	 * @auth ljp
	 * @date 2015年12月1日 下午1:38:24
	 */
	public boolean deleteRedisByPk(String userName,String tableName,String pk){
		Map<String, String> metadataMap = this.getMetadata(userName, tableName);
		String dataKey = this.getDataKey(metadataMap, pk);
		return this.del(dataKey);
	}
}
