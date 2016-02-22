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
import com.entity.Builder;
import com.entity.Buildingsite;
import com.entity.PageWidget;
import com.service.BuilderService;

/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-1-15 下午01:56:24
 * 版本号： v1.0
 */
@Controller
public class BuilderController
{
	@Resource(name = "builderService")
	private BuilderService<Builder> builderService;
	
	@RequestMapping("/loadBuilderPage")
	public String loadBuilderPage(String currentPage, String pageSize, String filterValue, String orderValue, String showtype, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		List<Builder> builderList = null;
		try
		{
			builderList = builderService.showBuilderList(filterValue, pageSize, currentPage, orderValue, showtype);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = builderService.getPageWidget(filterValue, pageSize, currentPage, showtype);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("builderList", builderList);
		req.setAttribute("orderByParam", orderValue);
		
		return "grids/buildercheckin_grid";
	}
	
	@RequestMapping("/addBuilderReturnBuilderId")
	@ResponseBody
	public String addBuilderReturnBuilderId(Builder builder, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		String builderId = "-1";
		try
		{
			builder.setAge("");
			builder.setHometown("");
			builder.setIdcard("");
			builder.setOtherinfo("");
			builder.setSex("");
			builder.setShortname(PinYinUtils.getPinYinHeadChar(builder.getName()));
			builder.setShowtype("1");
			builder.setType("timework");
			builder.setPinyin(PinYinUtils.getPinYin(builder.getName()));
			
			builderService.addBuilder(builder);
			builderId = builderService.getBuilderIdByBuilderName(builder.getName()) + "";
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		return builderId;
	}
	
	@RequestMapping("/addBuilder")
	public String addBuilder(Builder builder, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			builder.setShortname(PinYinUtils.getPinYinHeadChar(builder.getName()));
			builder.setPinyin(PinYinUtils.getPinYin(builder.getName()));
			builderService.addBuilder(builder);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("buildercheckinPage.html");
		return null;
	}
	
	@RequestMapping("/editBuilder")
	public String editBuilder(Builder builder, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			builderService.editBuilder(builder);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("buildercheckinPage.html");
		return null;
	}
	
	@RequestMapping("/removeBuilders")
	public String removeBuilders(String builderIds, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			builderService.removeBuilders(builderIds);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("buildercheckinPage.html");
		return null;
	}
	
	@RequestMapping("/reStartBuilders")
	public String reStartBuilders(String builderIds, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			builderService.reStartBuilders(builderIds);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("buildercheckinPage.html");
		return null;
	}
	
	@RequestMapping("/getBuilderByBuilderId")
	@ResponseBody
	public String getBuilderByBuilderId(String builderId, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = builderService.getBuilderByBuilderId(builderId);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/getBuilderListByFilterValue")
	@ResponseBody
	public String getBuilderListByFilterValue(String filterValue, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			filterValue = URLDecoder.decode(filterValue, "utf-8");
			jsonObject = builderService.showBuilderListByFilterValue(filterValue);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/getBuilderListByFilterValueForPackagework")
	@ResponseBody
	public String getBuilderListByFilterValueForPackagework(String filterValue, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			filterValue = URLDecoder.decode(filterValue, "utf-8");
			jsonObject = builderService.showBuilderListByFilterValueForPackagework(filterValue);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/getBuilderListByFilterValueForTimework")
	@ResponseBody
	public String getBuilderListByFilterValueForTimework(String filterValue, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			filterValue = URLDecoder.decode(filterValue, "utf-8");
			jsonObject = builderService.showBuilderListByFilterValueForTimework(filterValue);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/getBuilderListByFilterValueForTimeworkForWorkdayCheckin")
	@ResponseBody
	public String getBuilderListByFilterValueForTimeworkForWorkdayCheckin(String filterValue, String buildingsiteid, String workyear, String workmonth, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			filterValue = URLDecoder.decode(filterValue, "utf-8");
			jsonObject = builderService.showBuilderListByFilterValueForTimeworkForWorkdayCheckin(filterValue, buildingsiteid, workyear, workmonth);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
}
