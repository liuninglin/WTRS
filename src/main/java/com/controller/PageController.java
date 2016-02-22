package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.exception.ServiceException;
import com.common.util.UtilPackage;
import com.entity.Builder;
import com.entity.Buildingsite;
import com.entity.Debt;
import com.entity.Food;
import com.entity.Packagework;
import com.entity.PageWidget;
import com.entity.Project;
import com.entity.Salary;
import com.entity.UserManage;
import com.entity.Workday;
import com.service.BuilderService;
import com.service.BuildingsiteService;
import com.service.DebtService;
import com.service.ProjectService;
import com.service.SalaryService;
import com.service.UserManageService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:04:51
* 版本号： v1.0
*/
@Controller
public class PageController{

	@Resource(name = "userService")
	private UserManageService<UserManage> userService;
	
	@Resource(name = "builderService")
	private BuilderService<Builder> builderService;
	
	@Resource(name = "buildingsiteService")
	private BuildingsiteService<Buildingsite> buildingsiteService;
	
	@Resource(name = "salaryService")
	private SalaryService<Salary> salaryService;
	
	@Resource(name = "debtService")
	private DebtService<Debt> debtService;
	
	@Resource(name = "projectService")
	private ProjectService<Project> projectService;
	
	@RequestMapping("/errorDownloadPage")
	public String errorDownloadPage(String errorMonth, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setAttribute("page", "errorDownload");
		req.setAttribute("errorMonth", errorMonth);
		return "layouts/errorDownload";
	}
	
	@RequestMapping("/loginPage")
	public String loginPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Cookie[] cookies = req.getCookies();
		if(null != cookies)
		{
			for(Cookie cookie : cookies)
			{
				if("WorkingTimeRecordSystem.UserState".equals(cookie.getName().trim()))
				{
					if(!"".equals(cookie.getValue().trim()))
					{
						String username = cookie.getValue().split(" ")[0];
						String password = cookie.getValue().split(" ")[1];
						String projectId = cookie.getValue().split(" ")[2];
						String projectName = cookie.getValue().split(" ")[3];
						
						req.setAttribute("login_username", username);
						req.setAttribute("login_password", password);
						req.setAttribute("login_projectId", projectId);
						req.setAttribute("login_projectName", projectName);
					}
				}
			}
		}
		
		return "layouts/login";
	}
	
	@RequestMapping("/indexPage")
	public String indexPage(HttpServletRequest req, HttpServletResponse resp) {
		
		List<Workday> workdayList = new ArrayList<Workday>();
		PageWidget pageWidget = new PageWidget();
		pageWidget.setCurrentPage("0");
		pageWidget.setTotalCount("0");
		pageWidget.setStartIndex("0");
		pageWidget.setEndIndex("0");
		pageWidget.setTotalPages("0");
		
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("workdayList", workdayList);
		req.setAttribute("orderByParam", "");
		
		
		req.setAttribute("page", "index");
		return "layouts/index";
	}
	
	@RequestMapping("/workdaycheckinPage")
	public String workdaycheckinPage(HttpServletRequest req, HttpServletResponse resp) {
		List<Workday> workdayList = new ArrayList<Workday>();
		PageWidget pageWidget = new PageWidget();
		pageWidget.setCurrentPage("0");
		pageWidget.setTotalCount("0");
		pageWidget.setStartIndex("0");
		pageWidget.setEndIndex("0");
		pageWidget.setTotalPages("0");
		
		req.setAttribute("page", "workdaycheckin");
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("workdayList", workdayList);
		req.setAttribute("orderByParam", "");
		
		return "layouts/workdaycheckin";
	}
	
	@RequestMapping("/buildercheckinPage")
	public String buildercheckinPage(HttpServletRequest req, HttpServletResponse resp) {
		List<Builder> builderList = null;
		try
		{
			builderList = builderService.showBuilderList("", "10", "1", "", "1");
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = builderService.getPageWidget("", "10", "1", "1");
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("page", "buildercheckin");
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("builderList", builderList);
		req.setAttribute("orderByParam", "");
		return "layouts/buildercheckin";
	}
	
	@RequestMapping("/buildingsitecheckinPage")
	public String buildingsiteheckinPage(HttpServletRequest req, HttpServletResponse resp) {
		List<Buildingsite> buildingsiteList = null;
		try
		{
			buildingsiteList = buildingsiteService.showBuildingsiteList("", "10", "1", "", "1");
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = buildingsiteService.getPageWidget("", "10", "1", "1");
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("page", "buildingsitecheckin");
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("buildingsiteList", buildingsiteList);
		req.setAttribute("orderByParam", "");
		return "layouts/buildingsitecheckin";
	}
	
	@RequestMapping("/salarycheckinPage")
	public String salarycheckinPage(HttpServletRequest req, HttpServletResponse resp) {
		List<Salary> salaryList = null;
		try
		{
			salaryList = salaryService.showSalaryList("", "10", "1", "", UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = salaryService.getPageWidget("", "10", "1", UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("page", "salarycheckin");
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("salaryList", salaryList);
		req.setAttribute("orderByParam", "");
		return "layouts/salarycheckin";
	}
	
	@RequestMapping("/packageworkcheckinPage")
	public String packageworkcheckinPage(HttpServletRequest req, HttpServletResponse resp) {
		List<Packagework> packageworkList = new ArrayList<Packagework>();
		PageWidget pageWidget = new PageWidget();
		pageWidget.setCurrentPage("0");
		pageWidget.setTotalCount("0");
		pageWidget.setStartIndex("0");
		pageWidget.setEndIndex("0");
		pageWidget.setTotalPages("0");
		
		req.setAttribute("page", "packageworkcheckin");
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("packageworkList", packageworkList);
		req.setAttribute("orderByParam", "");
		return "layouts/packageworkcheckin";
	}
	
	@RequestMapping("/debtcheckinPage")
	public String debtcheckinPage(HttpServletRequest req, HttpServletResponse resp) {
		List<Debt> debtList = null;
		try
		{
			debtList = debtService.showDebtList("", "10", "1", "", UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = debtService.getPageWidget("", "10", "1", UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("page", "debtcheckin");
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("debtList", debtList);
		req.setAttribute("orderByParam", "");
		return "layouts/debtcheckin";
	}
	
	@RequestMapping("/foodcheckinPage")
	public String foodcheckinPage(HttpServletRequest req, HttpServletResponse resp) {
		List<Food> foodList = new ArrayList<Food>();
		PageWidget pageWidget = new PageWidget();
		pageWidget.setCurrentPage("0");
		pageWidget.setTotalCount("0");
		pageWidget.setStartIndex("0");
		pageWidget.setEndIndex("0");
		pageWidget.setTotalPages("0");
		
		req.setAttribute("page", "foodcheckin");
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("foodList", foodList);
		req.setAttribute("orderByParam", "");
		return "layouts/foodcheckin";
	}
	
	@RequestMapping("/accountPage")
	public String accountPage(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession session = req.getSession();
		UserManage um = (UserManage)session.getAttribute("usermanage");
		String userid = um.getUserid() + "";
		
		UserManage umTemp = null;
		try
		{
			umTemp = (UserManage)userService.getUserByUserId(userid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		if(umTemp != null)
		{
			session.setAttribute("usermanage", umTemp);
		}
		
		req.setAttribute("page", "account");
		return "layouts/account";
	}
	
	@RequestMapping("/projectPage")
	public String projectcheckinPage(HttpServletRequest req, HttpServletResponse resp) {
		
		List<Project> projectList = null;
		try
		{
			projectList = projectService.showProjectList("", "10", "1", "");
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = projectService.getPageWidget("", "10", "1");
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("page", "project");
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("projectList", projectList);
		req.setAttribute("orderByParam", "");
		return "layouts/project";
	}
}
