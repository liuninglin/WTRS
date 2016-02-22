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
import com.common.util.UtilPackage;
import com.entity.Buildingsite;
import com.entity.PageWidget;
import com.service.BuildingsiteService;

/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-1-15 下午01:56:24
 * 版本号： v1.0
 */
@Controller
public class BuildingsiteController
{
	@Resource(name = "buildingsiteService")
	private BuildingsiteService<Buildingsite> buildingsiteService;
	
	@RequestMapping("/loadBuildingsitePage")
	public String loadBuildingsitePage(String currentPage, String pageSize, String filterValue, String orderValue, String showtype, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		List<Buildingsite> buildingsiteList = null;
		try
		{
			buildingsiteList = buildingsiteService.showBuildingsiteList(filterValue, pageSize, currentPage, orderValue, showtype);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = buildingsiteService.getPageWidget(filterValue, pageSize, currentPage, showtype);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("buildingsiteList", buildingsiteList);
		req.setAttribute("orderByParam", orderValue);
		
		return "grids/buildingsitecheckin_grid";
	}
	
	@RequestMapping("/addBuildingsiteReturnBuildingsiteId")
	@ResponseBody
	public String addBuildingsiteReturnBuildingsiteId(Buildingsite buildingsite, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		String buildingsiteId = "-1";
		try
		{
			buildingsite.setAddress("");
			buildingsite.setOtherinfo("");
			buildingsite.setShortname(PinYinUtils.getPinYinHeadChar(buildingsite.getName()));
			buildingsite.setShowtype("1");
			buildingsite.setStarttime("");
			buildingsite.setEndtime("");
			buildingsite.setPinyin(PinYinUtils.getPinYin(buildingsite.getName()));
			
			buildingsiteService.addBuildingsite(buildingsite);
			buildingsiteId = buildingsiteService.getBuildingsiteIdByBuildingsiteName(buildingsite.getName()) + "";
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		return buildingsiteId;
	}
	
	
	@RequestMapping("/addBuildingsite")
	public String addBuildingsite(Buildingsite buildingsite, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			buildingsite.setShortname(PinYinUtils.getPinYinHeadChar(buildingsite.getName()));
			buildingsite.setPinyin(PinYinUtils.getPinYin(buildingsite.getName()));
			buildingsiteService.addBuildingsite(buildingsite);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("buildingsitecheckinPage.html");
		return null;
	}
	
	@RequestMapping("/editBuildingsite")
	public String editBuildingsite(Buildingsite buildingsite, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			buildingsiteService.editBuildingsite(buildingsite);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("buildingsitecheckinPage.html");
		return null;
	}
	
	@RequestMapping("/reStartBuildingsites")
	public String reStartBuildingsites(String buildingsiteIds, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			buildingsiteService.reStartBuildingsites(buildingsiteIds);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("buildingsitecheckinPage.html");
		return null;
	}
	
	@RequestMapping("/removeBuildingsites")
	public String removeBuildingsites(String buildingsiteIds, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			buildingsiteService.removeBuildingsites(buildingsiteIds);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("buildingsitecheckinPage.html");
		return null;
	}
	
	@RequestMapping("/getBuildingsiteByBuildingsiteId")
	@ResponseBody
	public String getBuildingsiteByBuildingsiteId(String buildingsiteId, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = buildingsiteService.getBuildingsiteByBuildingsiteId(buildingsiteId);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/getAllBuildingsitesForWorkday")
	@ResponseBody
	public String getAllBuildingsitesForWorkday(HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = buildingsiteService.getAllBuildingsitesForWorkday(UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/getBuildingsiteListByFilterValue")
	@ResponseBody
	public String getBuildingsiteListByFilterValue(String filterValue, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			filterValue = URLDecoder.decode(filterValue, "utf-8");
			jsonObject = buildingsiteService.getBuildingsiteListByFilterValue(filterValue);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/getAllBuildingsitesForPackagework")
	@ResponseBody
	public String getAllBuildingsitesForPackagework(HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = buildingsiteService.getAllBuildingsitesForPackagework(UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/getAllBuildingsitesForFood")
	@ResponseBody
	public String getAllBuildingsitesForFood(HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = buildingsiteService.getAllBuildingsitesForFood(UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
}
