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

import com.dao.ProjectDao;
import com.entity.Project;
import com.mapper.ProjectMapper;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-7-17 下午04:12:45
* 版本号： v1.0
* @param <T>
*/
@SuppressWarnings("unchecked")
@Repository
public class ProjectDaoImpl<T extends Project> implements ProjectDao<T>
{
	@Autowired
    private ProjectMapper mapper;

	@Override
	public boolean addProject(T project) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.addProject(project);
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
	public List<T> getProjectListByFilterValue(String filterValue) throws DataAccessException
	{
		return (List<T>)mapper.getProjectListByFilterValue(filterValue);
	}

	@Override
	public T getProjectByProjectId(String projectId) throws DataAccessException
	{
		return (T)mapper.getProjectByProjectId(projectId);
	}

	@Override
	public List<T> getProjectList(String filterValue, int startIndex, int endIndex, String orderValue) throws DataAccessException
	{
		return (List<T>)mapper.getProjectList(filterValue, startIndex, endIndex, orderValue);
	}

	@Override
	public int getTotalCount(String filterValue) throws DataAccessException
	{
		return mapper.getTotalCount(filterValue);
	}

	@Override
	public boolean removeProjects(String ids) throws DataAccessException
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
				mapper.removeProjects(idList);
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
	public boolean editProject(T project) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.editProject(project);
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}
}
