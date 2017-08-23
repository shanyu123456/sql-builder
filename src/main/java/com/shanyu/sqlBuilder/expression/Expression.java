/**
 * Project Name:sql-builder
 * File Name:Expression.java
 * Package Name:com.cjcs.common.expression
 * Date:2017年8月21日下午1:47:11
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.expression;

import com.shanyu.sqlBuilder.constant.Operator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class Expression {
	
	private Object left;//左表达式

	public Operator op;//操作符
	
	private Object right;//右表达式
		

}

