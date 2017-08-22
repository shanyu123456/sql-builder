/**
 * Project Name:sql-builder
 * File Name:Expressions.java
 * Package Name:com.cjcs.common.constant
 * Date:2017年8月21日上午9:35:38
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.constant;
/**
 * ClassName:Expressions <br/>
 * Function: 比较表达式<br/> 
 * Date:     2017年8月21日 上午9:35:38 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public enum CompareEnum {
	
	eq("相等","="),
	
	lt("小于","<"),
	
	le("小于等于","<="),
	
	gt("大于",">"),
	
	ge("大于等于",">="),
	
	;
	
	private String desc;
	
	private String value;
	
	public String getDesc(){
		return desc;
	}
	
	public String getValue(){
		return value;
	}
	
	private CompareEnum(String desc,String value){
		this.desc = desc;
		this.value = value;
	}

}

