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
import com.shanyu.sqlBuilder.builder.SqlBuilder;

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
public class TClientInfoDaoImpl extends JdbcTemplateDaoSupport<com.shanyu.sqlBuilder.po.TClientInfo> {
	
	List<com.shanyu.sqlBuilder.po.TClientInfo> getList(String clientId){
		SqlBuilder builder = createBuilder();
		builder.eq("clientId", clientId);
		return getList(builder);
	};
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Map<String,DruidDataSource> dataSourceMap=context.getBeansOfType(DruidDataSource.class);
		System.out.println(dataSourceMap.keySet());
		TClientInfoDaoImpl tclientInfoDao = context.getBean(TClientInfoDaoImpl.class);
		System.out.println(tclientInfoDao.getList("2007117"));
	}
}

