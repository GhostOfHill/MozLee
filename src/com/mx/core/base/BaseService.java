package com.mx.core.base;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;






/**
 * @Description: 所有service类的父类 主要完成对各数据源的链接 继承此类后可以在各自的service类中方便的得到数据源
 * @author 申龙赫
 * @param <T>
 */
@Service
public class BaseService<T> {

	/**
	 *	业务本地MyBatisDAO
	 */
	private MybatisDao<T> dao;
	
	@Autowired(required=false)
	@Qualifier("secondDao")
	private MybatisDao<T> secondDao;	//第二个数据源
	
//	@Autowired
	private RedisDao redisDao;	//注入操作redis数据库dao
	

	public MybatisDao<T> getDao() {
		this.dao.setModelName(this.getClass().getName().replace("service", "model").replace("Service", ""));
		return dao;
	}
	
	public MybatisDao<T> getSecondDao() {
		this.secondDao.setModelName(this.getClass().getName().replace("service", "model").replace("Service", ""));
		return secondDao;
	}

	@Resource
	public void setDao(MybatisDao<T> dao) {
		this.dao = dao;
	}
	
	/**
	 * 获取操作redis的dao
	 * @return
	 * @author ljp
	 * @date 2015年7月24日
	 */
	public RedisDao getRedisDao() {
		return redisDao;
	}
}
