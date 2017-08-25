/**
 * Project Name:sql-builder
 * File Name:From.java
 * Package Name:com.cjcs.common.expression
 * Date:2017年8月21日上午10:34:37
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.sql;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class From {
	
	public final static String FROM = " FROM ";
	
	public final static String SEPARATOR = ".";
	
	private String tableName;
	
	private String catalog;
	
	public String toSql(){
		Preconditions.checkArgument(StringUtils.isNotEmpty(tableName), "tableName is null!");
		Preconditions.checkArgument(StringUtils.isNotEmpty(catalog), "catalog is null!");
		StringBuilder builder = new StringBuilder();
		builder.append(FROM).append(catalog).append(SEPARATOR).append(tableName);
		return builder.toString();
	}
	
}

