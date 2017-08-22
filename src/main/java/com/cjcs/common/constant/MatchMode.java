/**
 * Project Name:sql-builder
 * File Name:MatchMode.java
 * Package Name:com.cjcs.common.constant
 * Date:2017年8月21日上午9:45:48
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.cjcs.common.constant;
/**
 * ClassName:MatchMode <br/>
 * Function: like 表达式对应枚举<br/>
 * Date:     2017年8月21日 上午9:45:48 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public enum MatchMode {
	
	start("前缀匹配","key%"),
	
	end("后缀匹配","%key"),
	
	anyWhere("模糊","%key%"),
	
	;
	
	private String desc;
	
	private String value;
	
	public String getDesc(){
		return desc;
	}
	
	public String getValue(){
		return value;
	}
	
	private MatchMode(String desc,String value){
		this.desc = desc;
		this.value = value;
	}

}

