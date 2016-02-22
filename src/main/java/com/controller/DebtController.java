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
import com.entity.Debt;
import com.entity.PageWidget;
import com.service.DebtService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-17 下午04:27:24
* 版本号： v1.0
*/
@Controller
public class DebtController
{
	@Resource(name = "debtService")
	private DebtService<Debt> debtService;
	
	@RequestMapping("/loadDebtPage")
	public String loadDebtPage(String currentPage, String pageSize, String filterValue, String orderValue, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		List<Debt> debtList = null;
		try
		{
			debtList = debtService.showDebtList(filterValue, pageSize, currentPage, orderValue, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = debtService.getPageWidget(filterValue, pageSize, currentPage, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("debtList", debtList);
		req.setAttribute("orderByParam", orderValue);
		
		return "grids/debtcheckin_grid";
	}
	
	@RequestMapping("/addDebt")
	public String addDebt(Debt debt, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		debt.setProjectid(Integer.valueOf(UtilPackage.projectid));
		try
		{
			debtService.addDebt(debt);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("debtcheckinPage.html");
		return null;
	}
	
	@RequestMapping("/editDebt")
	public String editDebt(Debt debt, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			debtService.editDebt(debt);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("debtcheckinPage.html");
		return null;
	}
	
	@RequestMapping("/removeDebts")
	public String removeDebts(String builderIds, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			debtService.removeDebts(builderIds, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("debtcheckinPage.html");
		return null;
	}
	
	@RequestMapping("/getDebtByBuilderId")
	@ResponseBody
	public String getDebtByBuilderId(String builderId, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = debtService.getDebtByBuilderId(builderId);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
}
