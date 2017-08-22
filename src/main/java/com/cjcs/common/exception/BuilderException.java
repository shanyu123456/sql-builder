/**
 * Project Name:sql-builder
 * File Name:BuilderException.java
 * Package Name:com.cjcs.common.exception
 * Date:2017年8月21日上午10:05:38
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.cjcs.common.exception;

/**
 * ClassName:BuilderException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月21日 上午10:05:38 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class BuilderException extends Exception{

	private static final long serialVersionUID = -207479864810379732L;
	
	private String message;
	
	public String getMessage(){
		return message;
	}
	
	public BuilderException(String message){
		super(message);
		this.message = message;
	}

}

