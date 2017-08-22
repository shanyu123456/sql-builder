/**
 * Project Name:sql-builder
 * File Name:ComplexExpression.java
 * Package Name:com.cjcs.common.expression
 * Date:2017年8月21日下午3:08:51
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.expression;

import com.shanyu.sqlBuilder.constant.Operator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ClassName:ComplexExpression <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月21日 下午3:08:51 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class ComplexExpression extends Expression{
	
	private Expression left;
	
	private Operator op;
	
	private Expression right;

	@Override
	public String left() {
		return left.toSql();
	}

	@Override
	public String right() {
		return right.toSql();
	} 
	
	@Override
	public String toSql(){
		StringBuilder builder = new StringBuilder();
		builder.append("(").append(left()).append(op.getOp()).append(right()).append(")");
		return builder.toString();
	}

}

