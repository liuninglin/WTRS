/**
* Copyright ? 2014-1-15 liuninglin
* WorkingTimeRecordSystem 下午01:56:24
* Version 1.0
* All right reserved.
*
*/

package com.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.exception.ServiceException;
import com.common.util.PinYinUtils;
import com.entity.PageWidget;
import com.entity.Project;
import com.service.ProjectService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-7-17 下午04:26:30
* 版本号： v1.0
*/
@Controller
public class ProjectController
{
	@Resource(name = "projectService")
	private ProjectService<Project> projectService;
	
	@RequestMapping("/addProject")
	@ResponseBody
	public String addProject(Project project, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			project.setShortname(PinYinUtils.getPinYinHeadChar(project.getName()));
			projectService.addProject(project);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
			return "fail";
		}
		
		return "success";
	}
	
	@RequestMapping("/getProjectListByFilterValue")
	@ResponseBody
	public String getProjectListByFilterValue(String filterValue, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			filterValue = URLDecoder.decode(filterValue, "utf-8");
			jsonObject = projectService.showProjectListByFilterValue(filterValue);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/loadProjectPage")
	public String loadProjectPage(String currentPage, String pageSize, String filterValue, String orderValue, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		List<Project> projectList = null;
		try
		{
			projectList = projectService.showProjectList(filterValue, pageSize, currentPage, orderValue);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = projectService.getPageWidget(filterValue, pageSize, currentPage);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("projectList", projectList);
		req.setAttribute("orderByParam", orderValue);
		
		return "grids/project_grid";
	}
	
	@RequestMapping("/getProjectByProjectId")
	@ResponseBody
	public String getProjectByProjectId(String projectId, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = projectService.getProjectByProjectIdForJson(projectId);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/removeProjects")
	public String removeProjects(String projectIds, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			projectService.removeProjects(projectIds);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("projectPage.html");
		return null;
	}
	
	@RequestMapping("/editProject")
	public String editProject(Project project, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			projectService.editProject(project);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("projectPage.html");
		return null;
	}
}
