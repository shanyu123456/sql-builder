/**
 * Project Name:sql-builder
 * File Name:SqlBuilder.java
 * Package Name:com.cjcs.common.sqlBuilder
 * Date:2017年8月21日上午9:09:50
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.builder;

import java.util.Map;

import com.shanyu.sqlBuilder.sql.From;
import com.shanyu.sqlBuilder.sql.Select;
import com.shanyu.sqlBuilder.sql.Where;

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
	
	//参数
	private Map<String,String> paramsMap;
	
	private Select select;
	
	private From from;
	
	private Where where;

}

