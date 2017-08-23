/**
 * Project Name:sql-builder
 * File Name:LikeExpression.java
 * Package Name:com.cjcs.common.expression
 * Date:2017年8月21日下午3:25:31
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.expression;

import com.shanyu.sqlBuilder.constant.MatchMode;
import com.shanyu.sqlBuilder.constant.Operator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ClassName:LikeExpression <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月21日 下午3:25:31 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class LikeExpression extends Expression{
	
	private String left;
	
	private Operator op;
	
	private String right;
	
	private MatchMode matchMode;
	
}

