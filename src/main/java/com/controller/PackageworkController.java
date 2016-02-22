package com.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.exception.ServiceException;
import com.common.util.UtilPackage;
import com.entity.Buildingsite;
import com.entity.Packagework;
import com.entity.PageWidget;
import com.service.BuildingsiteService;
import com.service.PackageworkService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-17 下午02:42:23
* 版本号： v1.0
*/
@Controller
public class PackageworkController
{
	@Resource(name = "packageworkService")
	private PackageworkService<Packagework> packageworkService;
	
	@Resource(name = "buildingsiteService")
	private BuildingsiteService<Buildingsite> buildingsiteService;	

	@RequestMapping("/loadPackageworkPage")
	public String loadPackageworkPage(String type, String currentPage, String pageSize, String filterValue, String orderValue, String buildingsiteid, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		List<Packagework> packageworkList = null;
		try
		{
			packageworkList = packageworkService.showPackageworkList(filterValue, pageSize, currentPage, orderValue, buildingsiteid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = packageworkService.getPageWidget(filterValue, pageSize, currentPage, buildingsiteid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("packageworkList", packageworkList);
		req.setAttribute("orderByParam", orderValue);
		
		if("checkin".equals(type))
		{
			return "grids/packageworkcheckin_grid";
		}
		else if("checkout".equals(type))
		{
			return "grids/packageworkcheckout_grid";
		}
		else
		{
			return "grids/packageworkcheckin_grid";
		}
	}
	
	@RequestMapping("/changePackagework")
	@ResponseBody
	public String changePackagework(Packagework packagework, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		packagework.setProjectid(Integer.valueOf(UtilPackage.projectid));
		try
		{
			packageworkService.changePackagework(packagework);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
			return "fail";
		}
		
		return "success";
	}
	
	@RequestMapping("/removePackageworks")
	public String removePackageworks(String builderIds, String buildingsiteId, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		try
		{
			packageworkService.removePackageworks(builderIds, buildingsiteId, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		resp.sendRedirect("packageworkcheckinPage.html");
		return null;
	}
	
	@RequestMapping("/getPackageworkByBuilderidAndBuildingsiteid")
	@ResponseBody
	public String getPackageworkByBuilderidAndBuildingsiteid(String buildingsiteId, String builderId, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = packageworkService.getPackageworkByBuilderidAndBuildingsiteid(buildingsiteId, builderId);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/loadPackageworkForAllPage")
	public String loadPackageworkForAllPage(String currentPage, String pageSize, String filterValue, String orderValue, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		List<Packagework> packageworkList = null;
		try
		{
			packageworkList = packageworkService.showPackageworkListForAll(filterValue, pageSize, currentPage, orderValue, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = packageworkService.getPageWidgetForAll(filterValue, pageSize, currentPage, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		List<Map<String, String>> buildingsiteList = null;
		buildingsiteList = UtilPackage.formatBuildingsitenameList(packageworkList, "packagework");
		
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("packageworkList", packageworkList);
		req.setAttribute("buildingsiteList", buildingsiteList);
		req.setAttribute("orderByParam", orderValue);
		
		return "grids/packageworkforallcheckout_grid";
	}
	
	@RequestMapping("/downloadPackageworkPage")
	public String downloadPackageworkPage(String currentPage, String pageSize, String filterValue, String orderValue, String buildingsiteid, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		if(UtilPackage.getMonth(UtilPackage.startyear, UtilPackage.startmonth, UtilPackage.endyear, UtilPackage.endmonth) > 12)
		{
			response.sendRedirect("errorDownloadPage.html?errorMonth=12");
			return null;
		}
		
		try
        {
			HSSFWorkbook wb = packageworkService.getPackageworkList(filterValue, pageSize, currentPage, orderValue, buildingsiteid, UtilPackage.projectid);

            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + UtilPackage.encodeFilename(request, buildingsiteService.getBuildingsiteNameByBuildingsiteId(buildingsiteid) + "-包工工资汇总.xls"));
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/x-msdownload");

            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            wb.write(toClient);
            toClient.flush();
            toClient.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return null;   
	}
	
	@RequestMapping("/downloadPackageworkForAllPage")
	public String downloadPackageworkForAllPage(String currentPage, String pageSize, String filterValue, String orderValue, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try
        {
			HSSFWorkbook wb = packageworkService.getPackageworkListForAll(filterValue, pageSize, currentPage, orderValue, UtilPackage.projectid);

            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + UtilPackage.encodeFilename(request, "包工工资汇总.xls"));
            response.setHeader("Connection", "close");
            response.setHeader("Content-Type", "application/x-msdownload");

            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            wb.write(toClient);
            toClient.flush();
            toClient.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return null;   
	}
}
