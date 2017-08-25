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

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.shanyu.sqlBuilder.constant.MatchMode;
import com.shanyu.sqlBuilder.constant.Operator;
import com.shanyu.sqlBuilder.expression.Expression;
import com.shanyu.sqlBuilder.expression.Expressions;
import com.shanyu.sqlBuilder.expression.LikeExpression;

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
	
	private Map<String,String> aliasMap;
	
	private Map<String,AtomicInteger> paramIndexMap;
			
	private static final String PREFIX = ":";
	
	public static final String WHERE =" WHERE ";
	
	
	public void eq(String param,Object value){
		Expression expression = Expressions.eq(param, value);
		this.expressions.add(expression);
	}
	
	public void lt(String param,Object value){
		Expression expression = Expressions.lt(param, value);
		this.expressions.add(expression);
	}
	
	public void le(String param,Object value){
		Expression expression = Expressions.le(param, value);
		this.expressions.add(expression);
	}
	
	public void gt(String param,Object value){
		Expression expression = Expressions.gt(param, value);
		this.expressions.add(expression);
	}
	
	public void ge(String param,Object value){
		Expression expression = Expressions.ge(param, value);
		this.expressions.add(expression);
	}
	
	public void like(String param,String value){
		Expression expression = Expressions.like(param, value);
		this.expressions.add(expression);
	}
	
	public void like(String param,String value,MatchMode matchMode){
		Expression expression = Expressions.like(param, value,matchMode);
		this.expressions.add(expression);
	}
	
	public void addExpression(Expression expression){
		this.expressions.add(expression);
	}
	
	public String toSql(){
		StringBuilder builder = new StringBuilder();
		
		if(CollectionUtils.isNotEmpty(expressions)){
			builder.append(WHERE);
			for (Expression expression : expressions) {
				dealWithExpression(expression, builder);
				builder.append(Operator.and.getOp());
			}
			builder.append(" 1=1");
		}
		return builder.toString();
	}
	
	/**
	 * 处理
	 * @Title: dealWithExpression
	 * @Description: (这里用一句话描述这个方法的作用)
	 * @param expression    
	 * @throws
	 */
	private void dealWithExpression(Expression expression,StringBuilder builder){
		if(expression instanceof LikeExpression){//like
			LikeExpression likeExpression = (LikeExpression) expression;
			String field = (String) expression.getLeft();
			String param = getParamName(field);
			Preconditions.checkArgument(aliasMap.containsKey(field),"column "+param+" not exists!");
			String column = aliasMap.get(field);
			builder.append(column).append(likeExpression.getOp().getOp()).append(MatchMode.MatchKey(likeExpression.getMatchMode(), param));
			paramsMap.put(param, likeExpression.getRight());
		}else{
			if(expression.getLeft() instanceof Expression){
				builder.append("(");
				dealWithExpression((Expression) expression.getLeft(), builder);
				builder.append(")").append(expression.op.getOp()).append("(");
				dealWithExpression((Expression) expression.getRight(), builder);
				builder.append(")");
			}else{
				String field = (String) expression.getLeft();
				String param = getParamName(field);
				Preconditions.checkArgument(aliasMap.containsKey(field),"column "+param+" not exists!");
				String column = aliasMap.get(field);
				builder.append(column).append(expression.getOp().getOp()).append(param);
				paramsMap.put(param, expression.getRight());
			}
		}
	}
	
	/**
	 * 
	 * @Title: getParamName
	 * @Description: 参数替代名
	 * @param param
	 * @return    
	 * @throws
	 */
	public String getParamName(String param){
		StringBuilder builder = new StringBuilder();
		builder.append(PREFIX).append(param);
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
		return builder.toString();
	}
	
	public void addparam(String paramName,Object value){
		paramsMap.put(paramName, value);
	}
	
	public static void main(String[] args) {
		LikeExpression like = new LikeExpression();
		System.out.println(like instanceof LikeExpression);
		System.out.println(like instanceof Expression);
	}
	
}

