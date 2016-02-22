package com.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.exception.ServiceException;
import com.common.util.UtilPackage;
import com.entity.Project;
import com.entity.UserManage;
import com.service.ProjectService;
import com.service.UserManageService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:04:51
* 版本号： v1.0
*/
@Controller
public class UserManageController{

	@Resource(name = "userService")
	private UserManageService<UserManage> uService;
	
	@Resource(name = "projectService")
	private ProjectService<Project> projectService;
	
	@RequestMapping("/loginUser")
	public String loginUser(HttpServletRequest req, HttpServletResponse resp, UserManage usermanage, String project, Model model, String remembervalue) throws IOException {
		UserManage usermanageTemp = null;
		
		if(usermanage == null || (usermanage != null && ((usermanage.getUsername() == null || (usermanage.getUsername() != null && usermanage.getUsername().trim().equals("")))||(usermanage.getPassword() == null || (usermanage.getPassword() != null && usermanage.getPassword().trim().equals(""))))))
		{
			model.addAttribute("message", "用户名或密码不能为空！");
			return "layouts/login";
		}
			
		if(project == null || (project != null && project.trim().equals("")))
		{
			model.addAttribute("message", "请选择相应的工程或新建工程！");
			return "layouts/login";
		}
		
		try
		{
			usermanageTemp = uService.checkLogin(usermanage.getUsername(), usermanage.getPassword());
		}
		catch (ServiceException e) 
		{
			model.addAttribute("message", "用户名或密码不能为空！");
			return "layouts/login";
		}
		
		if(null != usermanageTemp)
		{
			Project projectTemp = null;
			try
			{
				projectTemp = projectService.getProjectByProjectId(project);
			}
			catch (ServiceException e)
			{
				e.printStackTrace();
			}
//			List<Project> projectList = projectService.get
			
			
			UtilPackage.projectid = project;
			UtilPackage.startyear = projectTemp.getStarttime().length() == 0 ? "" : projectTemp.getStarttime().substring(0, 4);
			UtilPackage.startmonth = projectTemp.getStarttime().length() == 0 ? "" : projectTemp.getStarttime().substring(5, 7);
			UtilPackage.endyear = projectTemp.getEndtime().length() == 0 ? "" : projectTemp.getEndtime().substring(0, 4);
			UtilPackage.endmonth = projectTemp.getEndtime().length() == 0 ? "" : projectTemp.getEndtime().substring(5, 7);
			
			
			if("1".equals(remembervalue))//保存密码及所选项目
			{
				Cookie cookieTemp = new Cookie("WorkingTimeRecordSystem.UserState", (usermanage.getUsername() + " " + usermanage.getPassword() + " " + project + " " + projectTemp.getName()));
				cookieTemp.setSecure(req.isSecure());
				cookieTemp.setMaxAge(3600);
				cookieTemp.setPath("/WorkingTimeRecordSystem");
				
				resp.addCookie(cookieTemp);
			}
			else
			{
				Cookie cookieTemp = new Cookie("WorkingTimeRecordSystem.UserState", null);
				cookieTemp.setSecure(req.isSecure());
				cookieTemp.setMaxAge(0);
				cookieTemp.setPath("/WorkingTimeRecordSystem");
				resp.addCookie(cookieTemp);
			}
			
			HttpSession session = req.getSession();
			session.setAttribute("usermanage", usermanageTemp);
			session.setAttribute("project", projectTemp);
			session.setAttribute("jsessionid", session.getId());
			
			resp.sendRedirect("indexPage.html");
			return null;
		}
		else
		{
			model.addAttribute("message", "用户名或密码错误！");
			return "layouts/login";
		}
	}
	
	@RequestMapping("/logoutUser")
	public String logoutUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserManage usermanage = req.getSession().getAttribute("usermanage") == null ? null : (UserManage)req.getSession().getAttribute("usermanage");
		if(null != usermanage)
		{
			req.getSession().removeAttribute("usermanage");
		}
		
		Project project = req.getSession().getAttribute("project") == null ? null : (Project)req.getSession().getAttribute("project");
		if(null != project)
		{
			req.getSession().removeAttribute("project");
		}

		resp.sendRedirect("loginPage.html");
		return null;
	}
	
	
	@RequestMapping("/changeCurrentProject")
	@ResponseBody
	public String changeCurrentProject(String project, HttpServletRequest req, HttpServletResponse resp){
		try
		{
			Project projectTemp = null;
			try
			{
				projectTemp = projectService.getProjectByProjectId(project);
			}
			catch (ServiceException e)
			{
				e.printStackTrace();
			}
			UtilPackage.projectid = project;
			HttpSession session = req.getSession();
			session.setAttribute("project", projectTemp);
			
			return "success";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "fail";
		}
	}
	
	@RequestMapping("/checkUsernameExist")
	@ResponseBody
	public String checkUsernameExist(String username, String userid, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException {
		boolean flag = false;
		try
		{
			flag = uService.checkUsernameIsExist(username, userid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		if(flag)
		{
			return "exist";
		}
		else
		{
			return "no exist";
		}
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(UserManage usermanage, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		boolean flag = false;
		try
		{
			flag = uService.updateUser(usermanage);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("accountPage.html");
		return null;
	}
}
