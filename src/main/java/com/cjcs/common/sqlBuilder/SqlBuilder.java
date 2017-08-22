/**
 * Project Name:sql-builder
 * File Name:SqlBuilder.java
 * Package Name:com.cjcs.common.sqlBuilder
 * Date:2017年8月21日上午9:09:50
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.cjcs.common.sqlBuilder;

import java.util.Map;

import lombok.Data;

/**
 * ClassName:SqlBuilder <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月21日 上午9:09:50 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Data
public class SqlBuilder {
	
	//别名
	private Map<String,String> aliasMap;
	
	//表名
	private String tableName;
	
	//数据库目录
	private String catalog;
	 
	//转换成对应sql的表达式 
	public String toSql(){
		return null;
	}

}

