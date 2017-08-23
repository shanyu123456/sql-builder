/**
 * Project Name:sql-builder
 * File Name:Column.java
 * Package Name:com.cjcs.sqlBuilder.annoation
 * Date:2017年8月18日下午4:45:03
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.annoation;
/**
 * ClassName:Column <br/>
 * Function: 列字段 <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月18日 下午4:45:03 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	
	/**
	 * 
	 * columnName:数据库列名<br/>
	 */
	String columnName() default "";
	
	String comment() default "";

}

