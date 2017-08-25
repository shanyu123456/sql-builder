/**
 * Project Name:sql-builder
 * File Name:PageDto.java
 * Package Name:com.shanyu.sqlBuilder.builder
 * Date:2017年8月25日上午9:25:55
 * Copyright (c) 2017, shanyu123456@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder.builder;

import lombok.Data;

/**
 * ClassName:PageDto <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月25日 上午9:25:55 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Data
public class PageDto {
	
	public final static int DEFAULT_PAGE_SIZE = 20;
	
	private Integer pageSize = 20; //分页大小
	
	private Integer pageNum = 1; //当前页号
	
	private Integer totalCount = 0; //总数量
	
	private Integer currentCount = 0; //当前页数量

}

