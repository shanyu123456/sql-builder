/**
 * Project Name:sql-builder
 * File Name:Select.java
 * Package Name:com.cjcs.common.expression
 * Date:2017年8月21日上午9:58:54
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.cjcs.common.sqlBuilder;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.cjcs.common.exception.BuilderException;

import lombok.Data;

/**
 * ClassName:Select <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月21日 上午9:58:54 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Data
public class Select {
	
	private List<String> selectColumns;
	
	private final static String SELECT = "SELECT";
	
	public String toSql(Map<String,String> aliasMap) throws BuilderException{
		StringBuilder builder = new StringBuilder();
		builder.append(SELECT);
		if(CollectionUtils.isNotEmpty(selectColumns)){
			for (String alias : selectColumns) {
				String columnName = aliasMap.get(alias);
				if(columnName==null){
					throw new BuilderException("字段"+alias+"不存在！");
				}
				builder.append(" ").append(columnName).append(" ").append(alias).append(",");
			}
			if(selectColumns.size()>1){
				builder.deleteCharAt(builder.length()-1);
			}
		}
		return builder.toString();
	}

}

