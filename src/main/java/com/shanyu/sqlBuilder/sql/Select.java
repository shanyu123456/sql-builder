/**
 * Project Name:sql-builder
 * File Name:Select.java
 * Package Name:com.cjcs.common.expression
 * Date:2017年8月21日上午9:58:54
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.sql;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class Select {
	
	private List<String> selectColumns;
	
	private Map<String,String> aliasMap;
	
	public final static String SELECT = "SELECT ";
	
	public void add(String...queryProps){
		if(selectColumns==null){
			selectColumns = Lists.newArrayList();
		}
		for (String prop : queryProps) {
			if(aliasMap.containsKey(prop)){
				selectColumns.add(prop);
			}
		}
	}
	
	public String toSql(){
		StringBuilder builder = new StringBuilder();
		builder.append(SELECT);
		if(CollectionUtils.isEmpty(selectColumns)){
			if(selectColumns==null){
				selectColumns = Lists.newArrayList();
			}
			selectColumns.addAll(aliasMap.keySet());
		}
		for (String alias : selectColumns) {
			Preconditions.checkArgument(aliasMap.containsKey(alias), "字段"+alias+"不存在！");
			String columnName = aliasMap.get(alias);
			builder.append(columnName).append(" ").append(alias).append(", ");
		}
		builder.deleteCharAt(builder.length()-2);
		return builder.toString();
	}

}

