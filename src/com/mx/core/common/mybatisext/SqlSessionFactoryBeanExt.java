package com.mx.core.common.mybatisext;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import com.mx.core.common.mybatisext.helper.SmartDate;
import com.mx.core.common.mybatisext.helper.SmartDateTypeHandler;
import com.mx.core.common.mybatisext.helper.SqlSessionFactoryHolder;
import com.mx.core.common.mybatisext.interceptor.MyBatisInterceptor;

/**
 * 扩展功能,mapper文件自动加载
 * 空resultMap自动配置,mapper拦截
 */
public class SqlSessionFactoryBeanExt extends SqlSessionFactoryBean {
	private static final Logger logger = LogManager.getLogger("mylogs");
	private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class"; 
	private final Class<?> superClass;
	private SqlSessionFactory sqlSessionFactory;
	private MyBatisInterceptor[] interceptors;

	public SqlSessionFactoryBeanExt() {
		superClass = SqlSessionFactoryBean.class;
	}

	public void setValue(String name, Object value) {
		try {
			Field field = superClass.getDeclaredField(name);
			field.setAccessible(true);
			field.set(this, value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@Override  
    public void setTypeAliasesPackage(String typeAliasesPackage) {  
        ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();  
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);  
        typeAliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +  
                ClassUtils.convertClassNameToResourcePath(typeAliasesPackage) + "/" + DEFAULT_RESOURCE_PATTERN;  
  
        //将加载多个绝对匹配的所有Resource  
        //将首先通过ClassLoader.getResource("META-INF")加载非模式路径部分  
        //然后进行遍历模式匹配  
        try {  
            List<String> result = new ArrayList<String>();  
            Resource[] resources =  resolver.getResources(typeAliasesPackage);  
            if(resources != null && resources.length > 0){  
                MetadataReader metadataReader = null;  
                for(Resource resource : resources){  
                    if(resource.isReadable()){  
                       metadataReader =  metadataReaderFactory.getMetadataReader(resource);  
                        try {  
                            result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());  
                        } catch (ClassNotFoundException e) {  
                            e.printStackTrace();  
                        }  
                    }  
                }  
            }  
            if(result.size() > 0) {  
                super.setTypeAliasesPackage(StringUtils.join(result.toArray(), ","));  
            }else{  
                logger.warn("参数typeAliasesPackage:"+typeAliasesPackage+"，未找到任何包");  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  

	@Override
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		SqlSessionFactory factory = super.buildSqlSessionFactory();
		try {
			Configuration config = factory.getConfiguration();
			Class<?> classConfig = Configuration.class;
			// 拦截
			Field field = classConfig.getDeclaredField("mapperRegistry");
			field.setAccessible(true);
			field.set(config, new MapperRegistryExt(config, interceptors));
			// 日期格式处理
			config.getTypeHandlerRegistry().register(java.util.Date.class, SmartDateTypeHandler.class);
			config.getTypeHandlerRegistry().register(java.sql.Date.class, SmartDateTypeHandler.class);
			config.getTypeHandlerRegistry().register(SmartDate.class, SmartDateTypeHandler.class);
			
			config.setMapUnderscoreToCamelCase(true);//驼峰式命名
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return factory;
	}

	@Override
	public SqlSessionFactory getObject() throws Exception {
		if (sqlSessionFactory == null) {
			sqlSessionFactory = buildSqlSessionFactory();
			setValue("sqlSessionFactory", sqlSessionFactory);
			SqlSessionFactoryHolder.setSqlSessionFactory(sqlSessionFactory);
		}
		return sqlSessionFactory;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 重写，解决初始化两次的bug
	}

	public MyBatisInterceptor[] getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(MyBatisInterceptor[] interceptors) {
		this.interceptors = interceptors;
	}
}
