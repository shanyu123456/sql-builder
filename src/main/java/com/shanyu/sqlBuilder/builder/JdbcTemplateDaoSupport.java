/**
 * Project Name:sql-builder
 * File Name:JdbcTemplate.java
 * Package Name:com.shanyu.sqlBuilder.builder
 * Date:2017年8月24日上午8:58:47
 * Copyright (c) 2017, shanyu123456@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.builder;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import com.shanyu.sqlBuilder.annoation.Table;
import com.shanyu.sqlBuilder.builder.SqlBuilder;

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
		List<T> list= this.jdbcTemplate.query(finalSql, objList.toArray(new Object[objList.size()]), new RowMapper<T>(){
			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				try {
					T t = entity.newInstance();
					for (String column : builder.getSelectColumns()) {
						Field field = entity.getDeclaredField(column);
						field.setAccessible(true);
						setFieldValue(field, t, rs);
					}
					return t;
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				return null;
			}});
		if(CollectionUtils.isEmpty(list)){
			return Lists.newArrayList();
		}
		return list;
	}
	
	private void setFieldValue(Field field,Object obj,ResultSet rs) throws IllegalArgumentException, IllegalAccessException, SQLException{
		if(field.getType()== Integer.class){
			field.set(obj, rs.getInt(field.getName()));
		}else if(field.getType() == Long.class){
			field.set(obj, rs.getLong(field.getName()));
		}else if(field.getType() == Date.class){
			field.set(obj, rs.getDate(field.getName()));
		}else if(field.getType()==String.class){
			field.set(obj, rs.getString(field.getName()));
		}else if(field.getType()==Boolean.class){
			field.setBoolean(obj, rs.getBoolean(field.getName()));
		}
	}

}

