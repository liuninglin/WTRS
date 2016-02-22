package com.service.impl;

import java.util.List;

import org.apache.commons.json.JSONArray;
import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.exception.ServiceException;
import com.dao.ProjectDao;
import com.entity.PageWidget;
import com.entity.Project;
import com.service.ProjectService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:04:40
* 版本号： v1.0
*/
@Service("projectService")
public class ProjectServiceImpl<T extends Project> implements ProjectService<T> {

	@Autowired
	private ProjectDao<T> projectDao;

	@Override
	public boolean addProject(T project) throws ServiceException
	{
		return projectDao.addProject(project);
	}

	@Override
	public JSONObject showProjectListByFilterValue(String filterValue) throws ServiceException, JSONException
	{
		filterValue = filterCharacter(filterValue);
		List<T> projectList = projectDao.getProjectListByFilterValue(filterValue);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(Project project : projectList)
		{
			JSONObject jsonObjectTemp = new JSONObject();
			jsonObjectTemp.append("id", project.getId());
			jsonObjectTemp.append("name", project.getName());
			jsonObjectTemp.append("shortname", project.getShortname());
			jsonObjectTemp.append("starttime", project.getStarttime());
			jsonObjectTemp.append("endtime", project.getEndtime());
			jsonObjectTemp.append("status", project.getStatus());
			jsonArray.add(jsonObjectTemp);
		}
		jsonObject.append("projectArray", jsonArray);
		return jsonObject;
	}
	
	public String filterCharacter(String filterValue)
	{
		boolean flag = false;
		
		if(null == filterValue || "".equals(filterValue))
		{
			filterValue = "%";
			flag = true;
		}
		if(filterValue.contains("*"))
		{
			filterValue = filterValue.replaceAll("\\*", "%");
			flag = true;
		}
		if(filterValue.contains("?"))
		{
			filterValue = filterValue.replaceAll("\\?", "_");
			flag = true;
		}
		if(filterValue.contains(" "))
		{
			filterValue = filterValue.replaceAll(" ", "%");
			flag = true;
		}
		
		if(!flag)
		{
			filterValue = "%" + filterValue + "%";
		}
		
		return filterValue;
	}

	@Override
	public T getProjectByProjectId(String projectId) throws ServiceException
	{
		return projectDao.getProjectByProjectId(projectId);
	}
	
	@Override
	public JSONObject getProjectByProjectIdForJson(String projectId) throws ServiceException, JSONException
	{
		Project project = projectDao.getProjectByProjectId(projectId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("id", project.getId());
		jsonObject.append("name", project.getName());
		jsonObject.append("shortname", project.getShortname());
		jsonObject.append("starttime", project.getStarttime());
		jsonObject.append("endtime", project.getEndtime());
		jsonObject.append("status", project.getStatus());
		jsonObject.append("otherinfo", project.getOtherinfo());
		
		return jsonObject;
	}

	@Override
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		int totalCount = projectDao.getTotalCount(filterValue);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		int startIndex = 0;
		int endIndex = 0;
		
		if(totalPages == Integer.valueOf(currentPage) && Integer.valueOf(currentPage) != 0)
		{
			startIndex = Integer.valueOf(pageSize) * (totalPages - 1);
			endIndex = (totalPages % Integer.valueOf(currentPage)) + startIndex;
		}
		
		PageWidget pageWidget = new PageWidget();
		pageWidget.setPageSize(pageSize);
		pageWidget.setTotalCount(totalCount + "");
		pageWidget.setTotalPages(totalPages + "");
		pageWidget.setCurrentPage(currentPage);
		pageWidget.setStartIndex(startIndex + "");
		pageWidget.setEndIndex(endIndex + "");
		
		return pageWidget;
	}

	@Override
	public List<T> showProjectList(String filterValue, String pageSize, String currentPage, String orderValue) throws ServiceException
	{
		int startIndex = (Integer.valueOf(currentPage) - 1)* Integer.valueOf(pageSize);
		int endIndex = (Integer.valueOf(currentPage))* Integer.valueOf(pageSize);
		
		filterValue = filterCharacter(filterValue);
		
		if("".equals(orderValue))
		{
			orderValue = "TIMESTAMP(updatetime) desc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		return projectDao.getProjectList(filterValue, startIndex, endIndex, orderValue);
	}

	@Override
	public boolean removeProjects(String projectIds) throws ServiceException
	{
		return projectDao.removeProjects(projectIds);
	}

	@Override
	public boolean editProject(T project) throws ServiceException
	{
		return projectDao.editProject(project);
	}
}
