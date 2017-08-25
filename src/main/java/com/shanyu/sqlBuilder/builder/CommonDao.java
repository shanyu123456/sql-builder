package com.shanyu.sqlBuilder.builder;

import java.util.List;

/**
 * ClassName:CommonDao <br/>
 * Date:     2017年8月24日 上午8:48:25 <br/>
 * @author   shanyu
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface CommonDao<T> {
	
	/**
	 * 
	 * @Title: getList
	 * @Description: 获取列表
	 * @param builder
	 * @return    
	 * @throws
	 */
	public List<T> getList(SqlBuilder builder);
	
	/**
	 * 
	 * @Title: getSingleObject
	 * @Description: 获取单条记录
	 * @param builder
	 * @return    
	 * @throws
	 */
	public T getSingleObject(SqlBuilder builder);

}

