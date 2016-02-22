package com.controller;

import java.io.IOException;
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
import com.common.util.UtilPackage;
import com.entity.PageWidget;
import com.entity.Salary;
import com.service.SalaryService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-16 下午02:40:33
* 版本号： v1.0
*/
@Controller
public class SalaryController
{
	@Resource(name = "salaryService")
	private SalaryService<Salary> salaryService;
	
	@RequestMapping("/loadSalaryPage")
	public String loadSalaryPage(String currentPage, String pageSize, String filterValue, String orderValue, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		List<Salary> salaryList = null;
		try
		{
			salaryList = salaryService.showSalaryList(filterValue, pageSize, currentPage, orderValue, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = salaryService.getPageWidget(filterValue, pageSize, currentPage, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("salaryList", salaryList);
		req.setAttribute("orderByParam", orderValue);
		
		return "grids/salarycheckin_grid";
	}
	
	@RequestMapping("/addSalary")
	public String addSalary(Salary salary, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		salary.setProjectid(Integer.valueOf(UtilPackage.projectid));
		try
		{
			salaryService.addSalary(salary);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("salarycheckinPage.html");
		return null;
	}
	
	@RequestMapping("/editSalary")
	public String editSalary(Salary salary, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		salary.setProjectid(Integer.valueOf(UtilPackage.projectid));
		try
		{
			salaryService.editSalary(salary);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("salarycheckinPage.html");
		return null;
	}
	
	@RequestMapping("/removeSalaries")
	public String removeSalaries(String salaryIds, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			salaryService.removeSalaries(salaryIds, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("salarycheckinPage.html");
		return null;
	}
	
	@RequestMapping("/getSalaryByBuilderId")
	@ResponseBody
	public String getSalaryByBuilderId(String builderId, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = salaryService.getSalaryByBuilderId(builderId, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
}
