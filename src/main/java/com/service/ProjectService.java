package com.service;

import java.util.List;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;

import com.common.exception.ServiceException;
import com.entity.PageWidget;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-7-17 下午04:12:29
* 版本号： v1.0
* @param <T>
*/
public interface ProjectService<T> 
{
	public boolean addProject(T project) throws ServiceException;
	
	public JSONObject showProjectListByFilterValue(String filterValue) throws ServiceException, JSONException;
	
	public T getProjectByProjectId(String projectId) throws ServiceException;
	
	public JSONObject getProjectByProjectIdForJson(String projectId) throws ServiceException, JSONException;
	
	public List<T> showProjectList(String filterValue, String pageSize, String currentPage, String orderValue) throws ServiceException;
	
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage) throws ServiceException;
	
	public boolean removeProjects(String projectIds) throws ServiceException;
	
	public boolean editProject(T project) throws ServiceException;
}
