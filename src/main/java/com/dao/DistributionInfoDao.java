package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-21 下午04:23:06
* 版本号： v1.0
* @param <T>
*/
public interface DistributionInfoDao<T>
{
	public List<T> getDistributionInfoList(String filterValue, int startIndex, int endIndex, String orderValue, String projectid) throws DataAccessException;
	
	public int getTotalCount(String filterValue, String projectid) throws DataAccessException;
	
	public Integer getAllMoney(String projectid);
}
