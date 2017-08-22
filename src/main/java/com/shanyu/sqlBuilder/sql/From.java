/**
 * Project Name:sql-builder
 * File Name:From.java
 * Package Name:com.cjcs.common.expression
 * Date:2017年8月21日上午10:34:37
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.sql;

import lombok.Data;

/**
 * ClassName:From <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月21日 上午10:34:37 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Data
public class From {
	
	public final static String FROM = "FROM";
	
	private String tableName;
	
	public String toSql(){
		StringBuilder builder = new StringBuilder();
		builder.append(FROM).append(" ").append(tableName);
		return builder.toString();
	}
	
}

