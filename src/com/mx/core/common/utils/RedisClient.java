package com.mx.core.common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import com.mx.core.common.Config;

public class RedisClient {
    private static String webapp_num;

    private static JedisCluster jediscluster;
    private static Long reStartTime = 0l;
    private static Lock  lock = new ReentrantLock();

    static {
    	initJedisCluster();
    }
	public RedisClient() {
	}
/**
 * 判断redis服务器是否开启
 * config 为 false 则关闭，true则开启
 */
	public static boolean isRedisState() {
		return !Boolean.valueOf(Config.getString("redisState"));
	}

	/**
	 * 初始化
	 */
	public static void initJedisCluster() {
		if (isRedisState()) return;
//		 String proFilePath = System.getProperty("user.dir") + "\\config\\config.properties";
//		 String proFilePath =Config.get
//				 "com.mx.core.conf.config";
//		ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(proFilePath);
		webapp_num = Config.getString("webapp_num");
		String redisShareIp1 = Config.getString("redisShareIp1");
		String redisSharePort1 = Config.getString("redisSharePort1");
		String[] redisShareIps =  redisShareIp1.split(",");
		String[] redisSharePorts =  redisSharePort1.split(",");
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		for(int i=0;i<redisShareIps.length;i++){
			jedisClusterNodes.add(new HostAndPort(redisShareIps[i], Integer.valueOf(redisSharePorts[i])));
		}
		JedisPoolConfig config = new JedisPoolConfig();
		config.setBlockWhenExhausted(true);
		config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
		config.setJmxEnabled(true);
		config.setJmxNamePrefix("pool");
		config.setLifo(true);
		config.setMaxIdle(200);
		config.setMaxTotal(1024);
		config.setMaxWaitMillis(1000);
		config.setMinEvictableIdleTimeMillis(1800000);
		config.setMinIdle(0);
		config.setNumTestsPerEvictionRun(3);
		config.setSoftMinEvictableIdleTimeMillis(1800000);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		config.setTestWhileIdle(false);
		config.setTimeBetweenEvictionRunsMillis(-1);
		jediscluster = new JedisCluster(jedisClusterNodes,3000, config);

	}
	/**
	 * 向REDIS插入数据 key : value格式
	 * @param key
	 * @param value
	 */
	public static void setToRedis(String key, String value) {
		if (isRedisState()) return;
		try{
			jediscluster.set(key, value);
		}catch(JedisConnectionException e){
			restart(e,"setToRedis");
		}
	}
	/**
	 * 根据key从REDIS中取数据
	 * @param key
	 * @param jedis 
	 * @return
	 */
	public static String getValueFromRedis(String key) {
		if (isRedisState()) return null;
		String value ;
		try{
			value =	 jediscluster.get(key);
		}catch(JedisConnectionException e){
			restart(e,"getValueFromRedis");
			return "getValueFromRedis;error";
		}
		return value;
	}
	//redis 集群设置后的操作
	/**
	 * 根据key值获取map
	 * @param key
	 * @return
	 * @throws InterruptedException 
	 */
	public static Map<String, String> getMapFromRedis(String key) {
		Map<String, String> hgetAll = null;
		try{
			hgetAll = jediscluster.hgetAll(key);
			JedisPool s = null;
		}catch(JedisConnectionException e){
			restart(e,"getMapFromRedis");
		}
		return hgetAll;
	}
	/**
	 * 模糊查询	
	 * @param key
	 * @return
	 */
    public static List<Map<String,Map<String, String>>> getValueByKeyLike(String key){
    	try{
    		Set<String> keys = new HashSet<String>();
        	Map<String, JedisPool> clusterNodes = jediscluster.getClusterNodes();
        	Set<String> nodekeys = clusterNodes.keySet();
        	Iterator<String> itera = nodekeys.iterator();
        	while(itera.hasNext()){
        		JedisPool jp = clusterNodes.get(itera.next());
        		keys.addAll(jp.getResource().keys(key));
        	}
        	List<Map<String,Map<String, String>>> mapList =new  ArrayList<Map<String,Map<String, String>>>();
        	Iterator<String> iterator = keys.iterator();
        	while(iterator.hasNext()){
        		String k = iterator.next();
        		Map<String, String> hgetAll = jediscluster.hgetAll(k);
        		Map<String,Map<String, String>> map=new HashMap<String, Map<String,String>>();
        		map.put(k, hgetAll);
        		mapList.add(map);
        	}
        	return mapList;
    	}catch(JedisConnectionException e){
			restart(e,"getValueByKeyLike");
			return null;
		}
    }
	/**
	 * 赋值
	 * @param key
	 * @param map
	 */
	public static void setMapToRedis(String key, Map<String, String> map){
		try{
			jediscluster.hmset(key, map);
		}catch(JedisConnectionException e){
			restart(e,"setMapToRedis");
		}
	}
    /**
     * 根据key值删除
     * @param key
     */
	public static void del(String key){
		try{
		jediscluster.del(key);
		}catch(JedisConnectionException e){
			restart(e,"del");
		}
	}
	
	/**
	 * 向队列的末尾添加一个元素
	 * @param key 队列名， 第一次插入时会自动创建队列
	 * @param message 要插入到队列的信息 
	 */
	public static void publishToQueueLast( String key, String message){
		try{
		jediscluster.rpush(key, message);
		}catch(JedisConnectionException e){
			restart(e,"publishToQueueLast");
		}
	}
	/**
	 * 任务报文
	 */
    private static int task_num = 0;
    private static  final char[] zeroArray = "000000000000000000000".toCharArray();
	public static String buildTaskNumber(){
		SimpleDateFormat df = new SimpleDateFormat("yyMMdd");  
		 String bh= df.format(new Date());
		 String [] chars=bh.split("-");
	    	  synchronized (RedisClient.class) {
	    		  if(task_num==9999){
	  	    		task_num=0001;
	  	    	 }else{
	  	    		task_num++; 
	  	    	 }
	    	  }
	         String wh=zeroPadString(task_num+"",4);	
	        return  webapp_num+bh+wh;
	}
	
	/**
     * 不够位数的在前面补0
     * @param string
     * @param length
     * @return
     */
    public  static final String zeroPadString(String string, int length) {
        if(string == null || string.length() > length) {
            return string;
        }
        StringBuffer buf = new StringBuffer(length);
        buf.append(zeroArray, 0, length - string.length()).append(string);
        return buf.toString();
    }
    
    private static void restart(JedisConnectionException e,String mn){
    	System.out.println("调用"+mn+"时发生JedisClusterException;");
    	e.printStackTrace();
    	Long now = new Date().getTime();
		try {
			if(lock.tryLock(3,TimeUnit.SECONDS)){
				if(now-reStartTime>50000){
					initJedisCluster();
					reStartTime = now;
				}
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		throw e;
    }
}