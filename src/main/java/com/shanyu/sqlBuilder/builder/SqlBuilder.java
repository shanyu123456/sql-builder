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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.shanyu.sqlBuilder.annoation.Column;
import com.shanyu.sqlBuilder.annoation.Entity;
import com.shanyu.sqlBuilder.annoation.Table;
import com.shanyu.sqlBuilder.sql.From;
import com.shanyu.sqlBuilder.sql.Select;
import com.shanyu.sqlBuilder.sql.Where;

import lombok.Data;

/**
 * ClassName:SqlBuilder <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月21日 上午9:09:50 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Data
public class SqlBuilder {
	
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
	public void dealWithEntity(){
		Annotation[] clsAnnoations=entity.getAnnotations();
		for (Annotation annotation : clsAnnoations) {
			if(annotation instanceof Entity){
				
			}
			if(annotation instanceof Table){
				if(StringUtils.isNotBlank(((Table) annotation).tableName())&&StringUtils.isNotBlank(((Table) annotation).tableName())){
					String tableName = ((Table) annotation).tableName();
					String catalog = ((Table) annotation).calatog();
					this.from = new From(tableName,catalog);
				}
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
					aliasMap.put(field.getName(), CamelCaseUtil.camelCaseToUnderline(columnName));
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
	public void select(String...queryProps){
		if(select==null){
			select = new Select();
			select.setAliasMap(aliasMap); 
		}
		select.add(queryProps);
	}
	
	/**
	 * 
	 * @Title: initWhere
	 * @Description: 初始化where语句   
	 * @throws
	 */
	public void initWhere(){
		if(paramsMap==null){
			paramsMap = Maps.newHashMap();
		}
		if(where==null){
			where = new Where(Lists.newArrayList(),paramsMap,null);
		}
	}
	
	/**
	 * 
	 * @Title: eq
	 * @Description: (这里用一句话描述这个方法的作用)
	 * @param comumn
	 * @param value    
	 * @throws
	 */
	public void eq(String param,Object value){
		initWhere();
		
	}
	
}

