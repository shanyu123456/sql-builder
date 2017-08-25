/**
 * Project Name:sql-builder
 * File Name:TClientInfoDaoImpl.java
 * Package Name:com.shanyu.sqlBuilder
 * Date:2017年8月24日下午4:06:39
 * Copyright (c) 2017, shanyu123456@126.com All Rights Reserved.
 *
*/

package com.shanyu.sqlBuilder;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.shanyu.sqlBuilder.builder.JdbcTemplateDaoSupport;
import com.shanyu.sqlBuilder.builder.PageDto;
import com.shanyu.sqlBuilder.builder.SqlBuilder;
import com.shanyu.sqlBuilder.po.TClientInfo;

/**
 * ClassName:TClientInfoDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年8月24日 下午4:06:39 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Repository
public class TClientInfoDaoImpl extends JdbcTemplateDaoSupport<TClientInfo> {
	
	List<TClientInfo> getList(String clientId,PageDto pageDto){
		SqlBuilder builder = createBuilder();
		builder.eq("clientId", clientId);
		builder.setPageDto(pageDto);
		return getList(builder);
	};
	
	List<TClientInfo> getAllList(PageDto pageDto){
		SqlBuilder builder = createBuilder();
		builder.setPageDto(pageDto);
		return getList(builder);
	};
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Map<String,DruidDataSource> dataSourceMap=context.getBeansOfType(DruidDataSource.class);
		System.out.println(dataSourceMap.keySet());
		TClientInfoDaoImpl tclientInfoDao = context.getBean(TClientInfoDaoImpl.class);
		PageDto page = new PageDto();
		
		List<TClientInfo> infos = tclientInfoDao.getAllList(page);
		System.out.println(page);
	}
}

