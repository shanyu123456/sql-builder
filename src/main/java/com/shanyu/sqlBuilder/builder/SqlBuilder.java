/**
 * Project Name:sql-builder
 * File Name:SqlBuilder.java
 * Package Name:com.cjcs.common.sqlBuilder
 * Date:2017年8月21日上午9:09:50
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.builder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cjsc.common.utils.camelCase.CamelCaseUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.shanyu.sqlBuilder.annoation.Column;
import com.shanyu.sqlBuilder.annoation.Entity;
import com.shanyu.sqlBuilder.annoation.Table;
import com.shanyu.sqlBuilder.constant.MatchMode;
import com.shanyu.sqlBuilder.expression.Expression;
import com.shanyu.sqlBuilder.sql.From;
import com.shanyu.sqlBuilder.sql.Select;
import com.shanyu.sqlBuilder.sql.Where;

import oracle.net.aso.f;

/**
 * ClassName:SqlBuilder <br/>
 * Date:     2017年8月21日 上午9:09:50 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SqlBuilder {
	
	private SqlBuilder(){
		
	}
	
	private Class<?> entity;
	
	//别名
	private Map<String,String> aliasMap;
	
	//参数
	private Map<String,Object> paramsMap;
	
	private Select select;
	
	private From from;
	
	private Where where;
	
	/**
	 * 
	 * @Title: dealWithEntity
	 * @Description: 处理实体类
	 * @param entity    
	 * @throws
	 */
	private void dealWithEntity(){
		Annotation[] clsAnnoations=entity.getDeclaredAnnotations();
		for (Annotation annotation : clsAnnoations) {
			if(annotation instanceof Entity){
				
			}
			if(annotation instanceof Table){
				String tableName = ((Table) annotation).tableName();
				String catalog = ((Table) annotation).calatog();
				Preconditions.checkArgument(StringUtils.isNotBlank(tableName), "table name is null!");
				Preconditions.checkArgument(StringUtils.isNotBlank(catalog), "catalog name is null!");
				this.from = new From(tableName,catalog);
			}
		}
		Field[] fields=entity.getDeclaredFields();
		aliasMap = Maps.newHashMap();
		for (Field field : fields) {
			fillAliasMap(field);
		}
	}
	
	/**
	 * 
	 * @Title: fillAliasMap
	 * @Description: 获取field对应的数据库表列名
	 * @param field    
	 * @throws
	 */
	private void fillAliasMap(Field field){
		Annotation[] annoations = field.getDeclaredAnnotations();
		for (Annotation annotation : annoations) {
			if(annotation instanceof Column){
				String columnName = ((Column) annotation).columnName();
				if(StringUtils.isNotBlank(columnName)){
					aliasMap.put(field.getName(), columnName);
				}else{
					aliasMap.put(field.getName(), CamelCaseUtil.camelCaseToUnderline(field.getName()));
				}
			}
		}
	}
	
	/**
	 * @Title: select
	 * @Description: 查询字段
	 * @param qureyProps    
	 * @throws
	 */
	private void initSelect(){
		select = new Select();
		select.setAliasMap(aliasMap); 
	}
	
	private void select(String...queryProps){
		select.add(queryProps);
	}
	
	/**
	 * 
	 * @Title: initWhere
	 * @Description: 初始化where语句   
	 * @throws
	 */
	private void initWhere(){
		if(paramsMap==null){
			paramsMap = Maps.newHashMap();
		}
		if(where==null){
			where = new Where(Lists.newArrayList(),paramsMap,aliasMap,Lists.newArrayList());
		}
	}
	
	public void eq(String param,Object value){
		Preconditions.checkArgument(aliasMap.containsKey(param),"column "+param+" not exists!");
		where.eq(param, value);
	}
	public void lt(String param,Object value){
		Preconditions.checkArgument(aliasMap.containsKey(param),"column "+param+" not exists!");
		where.lt(param, value);
	}
	public void le(String param,Object value){
		Preconditions.checkArgument(aliasMap.containsKey(param),"column "+param+" not exists!");
		where.le(param, value);
	}
	public void gt(String param,Object value){
		Preconditions.checkArgument(aliasMap.containsKey(param),"column "+param+" not exists!");
		where.gt(param, value);
	}
	public void ge(String param,Object value){
		Preconditions.checkArgument(aliasMap.containsKey(param),"column "+param+" not exists!");
		where.ge(param, value);
	}
	public void like(String param,String value){
		Preconditions.checkArgument(aliasMap.containsKey(param),"column "+param+" not exists!");
		where.like(param, value);
	}
	public void like(String param,String value,MatchMode matchMode){
		Preconditions.checkArgument(aliasMap.containsKey(param),"column "+param+" not exists!");
		where.like(param, value,matchMode);
	}
	public void and(Expression expression){
		where.addExpression(expression);
	}
	public void or(Expression expression){
		where.addExpression(expression);
	}
	
	public static SqlBuilder createBuilder(Class<?> entity,String...queryProps){
		SqlBuilder builder = new SqlBuilder();
		builder.entity = entity;
		builder.dealWithEntity();
		builder.initWhere();
		builder.initSelect();
		if(queryProps!=null){
			builder.select(queryProps);
		}
		return builder;
	}
	
	public String toSql(){
		StringBuilder builder = new StringBuilder();
		builder.append(select.toSql()).append(from.toSql()).append(where.toSql());
		return builder.toString();
	}
	
	public static void main(String[] args) {
		SqlBuilder builder = SqlBuilder.createBuilder(TClientInfo.class);
		builder.eq("id", 1);
		System.out.println(builder.toSql());
	}
	
}

