/**
 * Project Name:sql-builder
 * File Name:Entity.java
 * Package Name:com.cjcs.sqlBuilder.annoation
 * Date:2017年8月18日下午3:46:23
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.cjcs.sqlBuilder.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName:Entity <br/>
 * Function: 数据库实体注解<br/>
 * Reason:	 标识数据库实体 <br/>
 * Date:     2017年8月18日 下午3:46:23 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {

}

