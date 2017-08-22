/**
 * Project Name:sql-builder
 * File Name:Table.java
 * Package Name:com.cjcs.sqlBuilder.annoation
 * Date:2017年8月18日下午4:33:26
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName:Table <br/>
 * Function: 对应数据库表注解 <br/>
 * Reason:	  指定对应数据库表 <br/>
 * Date:     2017年8月18日 下午4:33:26 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	
	/**
	 * 
	 * tableName:表名. <br/>
	 */
	String tableName() default "";
	
	/**
	 * 
	 * catalog:对应的数据库目录或者用户 <br/>
	 */
	String calatog() default "";
}

