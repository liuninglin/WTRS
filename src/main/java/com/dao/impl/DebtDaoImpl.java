package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.dao.DebtDao;
import com.entity.Debt;
import com.mapper.DebtMapper;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-17 下午04:20:38
* 版本号： v1.0
* @param <T>
*/
@SuppressWarnings("unchecked")
@Repository
public class DebtDaoImpl<T extends Debt> implements DebtDao<T>
{
	@Autowired
    private DebtMapper mapper;

	@Override
	public boolean addDebt(T debt) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.addDebt(debt);
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
	public boolean editDebt(T debt) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.editDebt(debt);
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
	public List<T> getDebtList(String filterValue, int startIndex, int endIndex, String orderValue, String projectid) throws DataAccessException
	{
		return (List<T>)mapper.getDebtList(filterValue, startIndex, endIndex, orderValue, projectid);
	}

	@Override
	public boolean removeDebts(String ids, String projectid) throws DataAccessException
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
				mapper.removeDebts(idList, projectid);
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
	public T getDebtByBuilderId(String builderId) throws DataAccessException
	{
		return (T)mapper.getDebtByBuilderId(builderId);
	}
}
