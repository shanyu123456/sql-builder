/**
 * Project Name:sql-builder
 * File Name:TClientInfo.java
 * Package Name:com.shanyu.sqlBuilder.po
 * Date:2017年8月24日下午4:03:18
 * Copyright (c) 2017, shanyu123456@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.po;

import com.shanyu.sqlBuilder.annoation.Column;
import com.shanyu.sqlBuilder.annoation.Entity;
import com.shanyu.sqlBuilder.annoation.Table;

import lombok.Data;

/**
 * ClassName:TClientInfo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月24日 下午4:03:18 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Entity
@Table(tableName="t_client_info",calatog="custgroup")
@Data
public class TClientInfo {
	
	@Column
	private String clientId;
	
	@Column
	private String idKind;
	
	@Column
	private String idNo;
	
	@Column
	private String userName;

}

