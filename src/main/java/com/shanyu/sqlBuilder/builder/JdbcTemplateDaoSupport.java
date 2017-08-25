/**
 * Project Name:sql-builder
 * File Name:JdbcTemplate.java
 * Package Name:com.shanyu.sqlBuilder.builder
 * Date:2017年8月24日上午8:58:47
 * Copyright (c) 2017, shanyu123456@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.builder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.shanyu.sqlBuilder.annoation.Table;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:JdbcTemplate <br/>
 * Date:     2017年8月24日 上午8:58:47 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Slf4j
public class JdbcTemplateDaoSupport<T> extends JdbcDaoSupport implements ApplicationContextAware, CommonDao<T> {
	
	private static final String DATASOURCE="DataSource";
	
	private static final String PARAM_REGEX=":[a-z|A-Z|0-9|_]*";
	
	private static final String REPLACE_STR="?";
	
	private Class<T> entity;
	
	/**
	 * 支持多数据源配置
	 */
	private Map<String,DruidDataSource> dataSourceMap;
	
	private JdbcTemplate jdbcTemplate;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		entity = getEntity();
		dataSourceMap=context.getBeansOfType(DruidDataSource.class);
		DruidDataSource dataSource = dataSourceMap.get(getCatalog()+DATASOURCE);
		jdbcTemplate = new JdbcTemplate(dataSource);
		super.setJdbcTemplate(jdbcTemplate);
	}
	
	private String getCatalog(){
		Table table = entity.getDeclaredAnnotation(Table.class);
		return table.calatog();
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getEntity(){
		Type type=getClass().getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) type;
		Type[] types=pType.getActualTypeArguments();
		return (Class<T>) types[0];
	}
	
	public SqlBuilder createBuilder(String...queryProps){
		return SqlBuilder.createBuilder(entity, queryProps);
	}
	
	public List<T> getList(SqlBuilder builder){
		String sql = builder.toSql();
		log.debug("sql={},params={}",sql,builder.getParamMap());
		String finalSql = sql;
		List<Object> objList = Lists.newArrayList();
		Pattern p = Pattern.compile(PARAM_REGEX);
		Matcher m = p.matcher(sql);
		while(m.find()){
			String param = m.group();
			finalSql=finalSql.replace(param, REPLACE_STR);
			objList.add(builder.getParamMap().get(param));
		}
		List<T> list= this.jdbcTemplate.query(finalSql, objList.toArray(new Object[objList.size()]), new BeanPropertyRowMapper<T>(entity));
		if(CollectionUtils.isEmpty(list)){
			list = Lists.newArrayList();
		}
		if(builder.getPageDto()!=null){
			builder.getPageDto().setCurrentCount(list.size());
		}
		return list;
	}
	
	@Override
	public T getSingleObject(SqlBuilder builder) {
		List<T> list = getList(builder);
		Preconditions.checkArgument(list.size()<=1, "not single record!");
		if(list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
	}

}

