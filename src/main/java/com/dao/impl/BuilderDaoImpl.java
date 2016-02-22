/**
* Copyright ? 2014-1-9 liuninglin
* WorkingTimeRecordSystem 上午01:05:56
* Version 1.0
* All right reserved.
*
*/

package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.dao.BuilderDao;
import com.entity.Builder;
import com.mapper.BuilderMapper;

/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-1-9 上午01:05:56
 * 版本号： v1.0
 */
@SuppressWarnings("unchecked")
@Repository
public class BuilderDaoImpl<T extends Builder> implements BuilderDao<T>
{
	@Autowired
    private BuilderMapper mapper;

	@Override
	public boolean addBuilder(T builder) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.addBuilder(builder);
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
	public boolean editBuilder(T builder) throws DataAccessException
	{
		if(builder.getSex() == null)
		{
			builder.setSex("");
		}
		
		boolean flag = false;
		try
		{
			mapper.editBuilder(builder);
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
	public List<T> getBuilderList(String filterValue, int startIndex, int endIndex, String orderValue, String showtype) throws DataAccessException
	{
		return (List<T>)mapper.getBuilderList(filterValue, startIndex, endIndex, orderValue, showtype);
	}

	@Override
	public boolean removeBuilders(String ids) throws DataAccessException
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
				mapper.removeBuilders(idList);
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
	public boolean reStartBuilders(String ids) throws DataAccessException
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
				mapper.reStartBuilders(idList);
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
	public int getTotalCount(String filterValue, String showtype) throws DataAccessException
	{
		return mapper.getTotalCount(filterValue, showtype);
	}

	@Override
	public T getBuilderByBuilderId(String builderId) throws DataAccessException
	{
		return (T)mapper.getBuilderByBuilderId(builderId);
	}

	@Override
	public List<T> getBuilderListByFilterValue(String filterValue) throws DataAccessException
	{
		return (List<T>)mapper.getBuilderListByFilterValue(filterValue);
	}

	@Override
	public List<T> getBuilderListByFilterValueForPackagework(String filterValue) throws DataAccessException
	{
		return (List<T>)mapper.getBuilderListByFilterValueForPackagework(filterValue);
	}

	@Override
	public int getBuilderNumForAll(String projectid) throws DataAccessException
	{
		return mapper.getBuilderNumForAll(projectid);
	}

	@Override
	public List<T> getBuilderListByFilterValueForTimework(String filterValue)
			throws DataAccessException {
		return (List<T>)mapper.getBuilderListByFilterValueForTimework(filterValue);
	}

	@Override
	public T getBuilderByBuilderName(String builderName) throws DataAccessException
	{
		return (T)mapper.getBuilderByBuilderName(builderName);
	}

	@Override
	public int getBuilderIdByBuilderName(String builderName) throws DataAccessException
	{
		return mapper.getBuilderIdByBuilderName(builderName);
	}

	@Override
	public List<T> getBuilderListByFilterValueForTimeworkForWorkdayCheckin(String filterValue, String buildingsiteid, String workyear, String workmonth)
	{
		return (List<T>)mapper.getBuilderListByFilterValueForTimeworkForWorkdayCheckin(filterValue, buildingsiteid, workyear, workmonth);
	}
}
