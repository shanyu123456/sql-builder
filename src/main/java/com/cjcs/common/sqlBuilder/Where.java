/**
 * Project Name:sql-builder
 * File Name:Where.java
 * Package Name:com.cjcs.common.expression
 * Date:2017年8月21日下午1:28:24
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.cjcs.common.sqlBuilder;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.cjcs.common.expression.Expression;

import lombok.Data;

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
public class Where {
	
	private List<Expression> expressions;
	
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
	
}

