package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.dao.BuildingsiteDao;
import com.entity.Buildingsite;
import com.mapper.BuildingsiteMapper;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-10 下午05:40:11
* 版本号： v1.0
* @param <T>
*/
@SuppressWarnings("unchecked")
@Repository
public class BuildingsiteDaoImpl<T extends Buildingsite> implements BuildingsiteDao<T>
{
	@Autowired
    private BuildingsiteMapper mapper;

	@Override
	public boolean addBuildingsite(T buildingsite) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.addBuildingsite(buildingsite);
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
	public boolean editBuildingsite(T buildingsite) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.editBuildingsite(buildingsite);
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
	public List<T> getBuildingsiteList(String filterValue, int startIndex, int endIndex, String orderValue, String showtype) throws DataAccessException
	{
		return (List<T>)mapper.getBuildingsiteList(filterValue, startIndex, endIndex, orderValue, showtype);
	}

	@Override
	public boolean reStartBuildingsites(String ids) throws DataAccessException
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
				mapper.reStartBuildingsites(idList);
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
	public boolean removeBuildingsites(String ids) throws DataAccessException
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
				mapper.removeBuildingsites(idList);
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
	public T getBuildingsiteByBuildingsiteId(String buildingsiteId) throws DataAccessException
	{
		return (T)mapper.getBuildingsiteByBuildingsiteId(buildingsiteId);
	}

	@Override
	public List<T> getAllBuildingsiteListForWorkday(String projectid) throws DataAccessException
	{
		return (List<T>)mapper.getAllBuildingsiteListForWorkday(projectid);
	}

	@Override
	public List<T> getBuildingsiteListByFilterValue(String filterValue) throws DataAccessException
	{
		return (List<T>)mapper.getBuildingsiteListByFilterValue(filterValue);
	}

	@Override
	public List<T> getAllBuildingsiteListForPackagework(String projectid) throws DataAccessException
	{
		return (List<T>)mapper.getAllBuildingsiteListForPackagework(projectid);
	}

	@Override
	public List<T> getAllBuildingsiteListForFood(String projectid) throws DataAccessException
	{
		return (List<T>)mapper.getAllBuildingsiteListForFood(projectid);
	}

	@Override
	public String getBuildingsiteNameByBuildingsiteId(String buildingsiteId)
	{
		return mapper.getBuildingsiteNameByBuildingsiteId(buildingsiteId);
	}

	@Override
	public int getBuildingsiteNumForAll(String projectid) throws DataAccessException
	{
		return mapper.getBuildingsiteNumForAll(projectid);
	}

	public List<T> getAllBuildingsiteList() throws DataAccessException
	{
		return (List<T>)mapper.getAllBuildingsiteList();
	}

	@Override
	public T getBuildingsiteByBuildingsiteName(String buildingsiteName) throws DataAccessException
	{
		buildingsiteName = "%" + buildingsiteName + "%";
		return (T)mapper.getBuildingsiteByBuildingsiteName(buildingsiteName);
	}

	@Override
	public int getBuildingsiteIdByBuildingsiteName(String buildingsiteName) throws DataAccessException
	{
		return mapper.getBuildingsiteIdByBuildingsiteName(buildingsiteName);
	}
}
