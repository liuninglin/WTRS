package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.dao.SalaryDao;
import com.entity.Salary;
import com.mapper.SalaryMapper;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-14 下午05:27:33
* 版本号： v1.0
* @param <T>
*/
@SuppressWarnings("unchecked")
@Repository
public class SalaryDaoImpl<T extends Salary> implements SalaryDao<T>
{
	@Autowired
    private SalaryMapper mapper;

	@Override
	public boolean addSalary(T salary) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.addSalary(salary);
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public boolean editSalary(T salary) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.editSalary(salary);
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public List<T> getSalaryList(String filterValue, int startIndex, int endIndex, String orderValue, String projectid) throws DataAccessException
	{
		return (List<T>)mapper.getSalaryList(filterValue, startIndex, endIndex, orderValue, projectid);
	}

	@Override
	public boolean removeSalaries(String ids, String projectid) throws DataAccessException
	{
		boolean flag = false;
		List<Integer> idList = new ArrayList<Integer>();
		try
		{
			if(ids != null && !"".equals(ids))
			{
				String[] idStrArray = ids.split(",");
				for(String idStr : idStrArray)
				{
					idList.add(new Integer(idStr));
				}
				mapper.removeSalaries(idList, projectid);
			}
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public int getTotalCount(String filterValue, String projectid) throws DataAccessException
	{
		return mapper.getTotalCount(filterValue, projectid);
	}

	@Override
	public T getSalaryByBuilderId(String builderId, String projectid) throws DataAccessException
	{
		return (T)mapper.getSalaryByBuilderId(builderId, projectid);
	}
}
