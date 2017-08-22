/**
 * Project Name:sql-builder
 * File Name:Expression.java
 * Package Name:com.cjcs.common.expression
 * Date:2017年8月21日下午1:47:11
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.cjcs.common.expression;

import com.cjcs.common.constant.Operator;

import lombok.Data;

/**
 * ClassName:Expression <br/>
 * Function: 表达式<br/>
 * Date:     2017年8月21日 下午1:47:11 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Data
public abstract class Expression {
	
	public abstract String left();//左表达式
	
	public abstract String right();//右表达式
	
	public Operator op;//操作符
	
	public String toSql(){
		StringBuilder builder = new StringBuilder();
		builder.append(left()).append(op.getOp()).append(right());
		return builder.toString();
	}

}

