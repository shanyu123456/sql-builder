/**
 * Project Name:sql-builder
 * File Name:CompareExpression.java
 * Package Name:com.cjcs.common.expression
 * Date:2017年8月21日下午2:52:20
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.cjcs.common.expression;

import com.cjcs.common.constant.Operator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ClassName:CompareExpression <br/>
 * Function: 比较类的表达式(=,>,>=,<,<=) <br/>
 * Date:     2017年8月21日 下午2:52:20 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class SimpleExpression extends Expression{
	
	private String left;
	
	private Operator op;
	
	private String right;

	@Override
	public String left() {
		return getLeft();
	}

	@Override
	public String right() {
		return getRight();
	}
}

