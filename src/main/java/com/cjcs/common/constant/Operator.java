/**
 * Project Name:sql-builder
 * File Name:Operator.java
 * Package Name:com.cjcs.common.constant
 * Date:2017年8月21日下午2:02:16
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.cjcs.common.constant;
/**
 * ClassName:Operator <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月21日 下午2:02:16 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public enum Operator {
	
	eq("等于"," = "),
	
	lt("小于"," < "),
	
	le("小于等于"," <= "),
	
	gt("大于"," > "),
	
	ge("大于等于"," >= "),
	
	and("and"," and "),
	
	or("or"," or "),
	
	like("like"," like "),
	
	;
	
	private String desc;
	
	private String op;
	
	public String getDesc(){
		return desc;
	}
	
	public String getOp(){
		return op;
	}
	
	private Operator(String desc, String op){
		this.desc = desc;
		this.op = op;
	}

}

