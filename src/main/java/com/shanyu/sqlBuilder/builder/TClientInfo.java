/**
 * Project Name:sql-builder
 * File Name:TClientInfo.java
 * Package Name:com.shanyu.sqlBuilder.builder
 * Date:2017年8月23日下午4:48:23
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.builder;

import com.shanyu.sqlBuilder.annoation.Column;
import com.shanyu.sqlBuilder.annoation.Entity;
import com.shanyu.sqlBuilder.annoation.Table;

import lombok.Data;

/**
 * ClassName:TClientInfo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月23日 下午4:48:23 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Data
@Entity
@Table(tableName="t_client_info",calatog="custgroup")
public class TClientInfo {
	
	@Column
	private Integer id;
	
	@Column
	private Integer uid;
}

