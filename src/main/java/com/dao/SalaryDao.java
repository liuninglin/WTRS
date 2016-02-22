package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-14 下午05:26:25
* 版本号： v1.0
* @param <T>
*/
public interface SalaryDao<T>
{
	public List<T> getSalaryList(String filterValue, int startIndex, int endIndex, String orderValue, String projectid) throws DataAccessException;
	
	public boolean addSalary(T salary) throws DataAccessException;
	
	public boolean editSalary(T salary) throws DataAccessException;
	
	public boolean removeSalaries(String ids, String projectid) throws DataAccessException;
	
	public int getTotalCount(String filterValue, String projectid) throws DataAccessException;
	
	public T getSalaryByBuilderId(String builderId, String projectid) throws DataAccessException;
}
