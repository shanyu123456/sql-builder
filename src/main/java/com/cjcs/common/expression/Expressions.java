/**
 * Project Name:sql-builder
 * File Name:Expressions.java
 * Package Name:com.cjcs.common.expression
 * Date:2017年8月21日下午3:13:48
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.cjcs.common.expression;

import com.cjcs.common.constant.MatchMode;
import com.cjcs.common.constant.Operator;

/**
 * ClassName:Expressions <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月21日 下午3:13:48 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Expressions {
	
	public static Expression lt(String left,String right){
		return new SimpleExpression(left,Operator.lt,right);
	}
	
	public static Expression le(String left,String right){
		return new SimpleExpression(left,Operator.le,right);
	}
	
	public static Expression gt(String left,String right){
		return new SimpleExpression(left,Operator.gt,right);
	}
	
	public static Expression ge(String left,String right){
		return new SimpleExpression(left,Operator.ge,right);
	}
	
	public static Expression eq(String left,String right){
		return new SimpleExpression(left,Operator.eq,right);
	}
	
	public static Expression and(Expression left, Expression right){
		return new ComplexExpression(left,Operator.and, right);
	}
	
	public static Expression or(Expression left, Expression right){
		return new ComplexExpression(left,Operator.or, right);
	}
	
	public static Expression like(String left,String right,MatchMode matchMode){
		return new LikeExpression(left,Operator.like,right,matchMode);
	}
	
	public static Expression like(String left,String right){
		return new LikeExpression(left,Operator.like,right,null);
	}

}

