package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.dao.PackageworkDao;
import com.entity.Packagework;
import com.entity.PackageworkDetail;
import com.mapper.PackageworkMapper;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-17 下午02:28:51
* 版本号： v1.0
* @param <T>
*/
@SuppressWarnings("unchecked")
@Repository
public class PackageworkDaoImpl<T extends Packagework> implements PackageworkDao<T>
{
	@Autowired
    private PackageworkMapper mapper;

	@Override
	public boolean addPackagework(T packagework) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.addPackagework(packagework);
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
	public boolean editPackagework(T packagework) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.editPackagework(packagework);
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
	public List<T> getPackageworkList(String filterValue, int startIndex, int endIndex, String orderValue, String buildingsiteId) throws DataAccessException
	{
		return (List<T>)mapper.getPackageworkList(filterValue, startIndex, endIndex, orderValue, buildingsiteId);
	}

	@Override
	public boolean removePackageworks(String ids, String buildingsiteid, String projectid) throws DataAccessException
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
				mapper.removePackageworks(idList, buildingsiteid, projectid);
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
	public int getTotalCount(String filterValue, String buildingsiteId) throws DataAccessException
	{
		return mapper.getTotalCount(filterValue, buildingsiteId);
	}

	@Override
	public T getPackageworkByBuilderidAndBuildingsiteid(String buildingsiteId, String builderId) throws DataAccessException
	{
		return (T)mapper.getPackageworkByBuilderidAndBuildingsiteid(buildingsiteId, builderId);
	}

	@Override
	public List<PackageworkDetail> getPackageworkDetailListForAll(String builderid) throws DataAccessException
	{
		return (List<PackageworkDetail>)mapper.getPackageworkDetailListForAll(builderid);
	}

	@Override
	public List<T> getPackageworkListForAll(String filterValue, int startIndex, int endIndex, String orderValue, String projectid) throws DataAccessException
	{
		return (List<T>)mapper.getPackageworkListForAll(filterValue, startIndex, endIndex, orderValue, projectid);
	}

	@Override
	public int getTotalCountForAll(String filterValue, String projectid) throws DataAccessException
	{
		return mapper.getTotalCountForAll(filterValue, projectid);
	}

	@Override
	public T getPackageworkByPackageworkDetail(String builderid, String buildingsiteid) throws DataAccessException
	{
		return (T)mapper.getPackageworkByPackageworkDetail(builderid, buildingsiteid);
	}
}
