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
import com.entity.Food;
import com.entity.PageWidget;
import com.service.BuildingsiteService;
import com.service.FoodService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-20 上午12:45:39
* 版本号： v1.0
*/
@Controller
public class FoodController
{
	@Resource(name = "foodService")
	private FoodService<Food> foodService;
	
	@Resource(name = "buildingsiteService")
	private BuildingsiteService<Buildingsite> buildingsiteService;
	
	@RequestMapping("/loadFoodForMonthPage")
	public String loadFoodForMonthPage(String type, String currentPage, String pageSize, String filterValue, String orderValue, String buildingsiteid, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		List<Food> foodList = null;
		try
		{
			foodList = foodService.showFoodList(filterValue, pageSize, currentPage, orderValue, buildingsiteid, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = foodService.getPageWidget(filterValue, pageSize, currentPage, buildingsiteid, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		List<Map<String, String>> monthList = null;
		monthList = UtilPackage.formatMonthForChineseCharacter(UtilPackage.startyear, UtilPackage.startmonth, UtilPackage.endyear, UtilPackage.endmonth);
		
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("foodList", foodList);
		req.setAttribute("monthList", monthList);
		req.setAttribute("orderByParam", orderValue);
		
		if("checkin".equals(type))
		{
			return "grids/foodformonthcheckin_grid";
		}
		else if("checkout".equals(type))
		{
			return "grids/foodformonthcheckout_grid";
		}
		else
		{
			return "grids/foodformonthcheckin_grid";
		}
	}
	
	@RequestMapping("/downloadFoodForMonthPage")
	public String downloadFoodForMonthPage(String type, String currentPage, String pageSize, String filterValue, String orderValue, String buildingsiteid, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		if(UtilPackage.getMonth(UtilPackage.startyear, UtilPackage.startmonth, UtilPackage.endyear, UtilPackage.endmonth) > 9)
		{
			response.sendRedirect("errorDownloadPage.html?errorMonth=9");
			return null;
		}
		
		try
        {
			HSSFWorkbook wb = foodService.getFoodListForMonth(filterValue, pageSize, currentPage, orderValue, buildingsiteid, UtilPackage.projectid);

            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + UtilPackage.encodeFilename(request, buildingsiteService.getBuildingsiteNameByBuildingsiteId(buildingsiteid) + "-伙食费汇总.xls"));
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
	
	@RequestMapping("/changeFood")
	@ResponseBody
	public String changeFood(Food food, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		food.setProjectid(Integer.valueOf(UtilPackage.projectid));
		try
		{
			foodService.changeFood(food);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
			return "fail";
		}
		
		return "success";
	}
	
	@RequestMapping("/loadFoodForAllPage")
	public String loadFoodForAllPage(String currentPage, String pageSize, String filterValue, String orderValue, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		List<Food> foodList = null;
		try
		{
			foodList = foodService.showFoodListForAll(filterValue, pageSize, currentPage, orderValue, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = foodService.getPageWidgetForAll(filterValue, pageSize, currentPage, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		List<Map<String, String>> buildingsiteList = null;
		buildingsiteList = UtilPackage.formatBuildingsitenameList(foodList, "food");
		
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("foodList", foodList);
		req.setAttribute("buildingsiteList", buildingsiteList);
		req.setAttribute("orderByParam", orderValue);
		
		return "grids/foodforallcheckout_grid";
	}
	
	@RequestMapping("/downloadFoodForAllPage")
	public String downloadFoodForAllPage(String currentPage, String pageSize, String filterValue, String orderValue, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try
        {
			HSSFWorkbook wb = foodService.getFoodListForAll(filterValue, pageSize, currentPage, orderValue, UtilPackage.projectid);

            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + UtilPackage.encodeFilename(request, "伙食费汇总.xls"));
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
	
	@RequestMapping("/getFoodDetailInfo")
	@ResponseBody
	public String getFoodDetailInfo(String builderid, String buildingsiteid, String foodyear, String foodmonth, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = foodService.getFoodDetailInfo(builderid, buildingsiteid, foodyear, foodmonth);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
}
