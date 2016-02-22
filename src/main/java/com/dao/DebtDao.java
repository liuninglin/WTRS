package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-17 下午04:19:36
* 版本号： v1.0
* @param <T>
*/
public interface DebtDao<T>
{
	public List<T> getDebtList(String filterValue, int startIndex, int endIndex, String orderValue, String projectid) throws DataAccessException;
	
	public boolean addDebt(T debt) throws DataAccessException;
	
	public boolean editDebt(T debt) throws DataAccessException;
	
	public boolean removeDebts(String ids, String projectid) throws DataAccessException;
	
	public int getTotalCount(String filterValue, String projectid) throws DataAccessException;
	
	public T getDebtByBuilderId(String builderId) throws DataAccessException;
}
