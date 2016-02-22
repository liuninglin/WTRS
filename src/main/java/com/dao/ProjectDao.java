/**
* Copyright ? 2014-1-9 liuninglin
* WorkingTimeRecordSystem 上午01:02:53
* Version 1.0
* All right reserved.
*
*/

package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-7-17 下午04:09:25
* 版本号： v1.0
* @param <T>
*/
public interface ProjectDao<T>
{
	public List<T> getProjectListByFilterValue(String filterValue) throws DataAccessException;
	
	public T getProjectByProjectId(String projectId) throws DataAccessException;
	
	public boolean addProject(T project) throws DataAccessException;
	
	public List<T> getProjectList(String filterValue, int startIndex, int endIndex, String orderValue) throws DataAccessException;
	
	public int getTotalCount(String filterValue) throws DataAccessException;
	
	public boolean removeProjects(String ids) throws DataAccessException;
	
	public boolean editProject(T project) throws DataAccessException;
}
