/**
 * Project Name:sql-builder
 * File Name:Where.java
 * Package Name:com.cjcs.common.expression
 * Date:2017年8月21日下午1:28:24
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.sql;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Maps;
import com.shanyu.sqlBuilder.constant.MatchMode;
import com.shanyu.sqlBuilder.expression.Expression;
import com.shanyu.sqlBuilder.expression.Expressions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName:Where <br/>
 * Function: where 语句<br/>
 * Date:     2017年8月21日 下午1:28:24 <br/>
 * @author   shanyu
 * @version  
 * @param <Expression>
 * @since    JDK 1.6
 * @see 	 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Where {
		
	private List<Expression> expressions;
	
	private Map<String,Object> paramsMap;
	
	private Map<String,AtomicInteger> paramIndexMap; 
	
	private static final String PREFIX = ":";
	
	public void eq(String param,Object value){
		String paramName = getParamName(param);
		paramsMap.put(paramName, value);
		Expression expression = Expressions.eq(param, paramName);
		this.expressions.add(expression);
	}
	
	public void lt(String param,Object value){
		String paramName = getParamName(param);
		paramsMap.put(paramName, value);
		Expression expression = Expressions.lt(param, paramName);
		this.expressions.add(expression);
	}
	
	public void le(String param,Object value){
		String paramName = getParamName(param);
		paramsMap.put(paramName, value);
		Expression expression = Expressions.le(param, paramName);
		this.expressions.add(expression);
	}
	
	public void gt(String param,Object value){
		String paramName = getParamName(param);
		paramsMap.put(paramName, value);
		Expression expression = Expressions.gt(param, paramName);
		this.expressions.add(expression);
	}
	
	public void ge(String param,Object value){
		String paramName = getParamName(param);
		paramsMap.put(paramName, value);
		Expression expression = Expressions.ge(param, paramName);
		this.expressions.add(expression);
	}
	
	public void like(String param,Object value){
		String paramName = getParamName(param);
		paramsMap.put(paramName, value);
		Expression expression = Expressions.like(param, paramName);
		this.expressions.add(expression);
	}
	
	public void like(String param,Object value,MatchMode matchMode){
		String paramName = getParamName(param);
		paramsMap.put(paramName, value);
		Expression expression = Expressions.like(param, paramName,matchMode);
		this.expressions.add(expression);
	}
	
	public void addExpression(Expression expression){
		
	}
	
	public String toSql(){
		StringBuilder builder = new StringBuilder();
		if(CollectionUtils.isNotEmpty(expressions)){
			for (Expression expression : expressions) {
				builder.append(expression.toSql()).append(" AND");
			}
			builder.append(" 1<>1");
		}
		return builder.toString();
	}
	
	/**
	 * 
	 * @Title: getParamName
	 * @Description: 参数替代名
	 * @param param
	 * @return    
	 * @throws
	 */
	private String getParamName(String param){
		StringBuilder builder = new StringBuilder();
		builder.append(PREFIX).append(param);
		if(paramsMap.containsKey(param)){
			if(paramIndexMap==null){
				paramIndexMap = Maps.newHashMap();
			}
			AtomicInteger index = null;
			if(paramIndexMap.containsKey(param)){
				index=paramIndexMap.get(param);
			}else{
				index = new AtomicInteger(0);
				paramIndexMap.put(param, index);
			}
			builder.append(index.incrementAndGet());
		}
		return builder.toString();
	}
	
}

