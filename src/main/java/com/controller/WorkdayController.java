package com.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.common.exception.ServiceException;
import com.common.util.UtilPackage;
import com.entity.Buildingsite;
import com.entity.MonthEntity;
import com.entity.PageWidget;
import com.entity.Workday;
import com.service.BuildingsiteService;
import com.service.WorkdayService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-12 下午02:29:19
* 版本号： v1.0
*/
@Controller
public class WorkdayController
{
	@Resource(name = "workdayService")
	private WorkdayService<Workday> workdayService;
	
	@Resource(name = "buildingsiteService")
	private BuildingsiteService<Buildingsite> buildingsiteService;
	
	@RequestMapping("/loadWorkdayPage")
	public String loadWorkdayPage(String showType, String currentPage, String pageSize, String filterValue, String orderValue, String buildingsiteid, String workyear, String workmonth, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		List<Workday> workdayList = null;
		try
		{
			workdayList = workdayService.showWorkdayList(filterValue, pageSize, currentPage, orderValue, buildingsiteid, workyear, workmonth);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = workdayService.getPageWidget(filterValue, pageSize, currentPage, buildingsiteid, workyear, workmonth);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("workdayList", workdayList);
		req.setAttribute("orderByParam", orderValue);
		
		if("checkin".equals(showType))
		{
			return "grids/workdaycheckin_grid";
		}
		else if("checkout".equals(showType))
		{
			return "grids/workdaycheckout_grid";
		}
		else
		{
			return "grids/workdaycheckin_grid";
		}
	}
	
	@RequestMapping("/downloadWorkdayPage")
	public String downloadWorkdayPage(String currentPage, String pageSize, String filterValue, String orderValue, String buildingsiteid, String workyear, String workmonth, HttpServletRequest request, HttpServletResponse response) throws ServiceException
	{
		try
        {
			HSSFWorkbook wb = workdayService.getWorkdayListForDay(filterValue, pageSize, currentPage, orderValue, buildingsiteid, workyear, workmonth);

            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + UtilPackage.encodeFilename(request, buildingsiteService.getBuildingsiteNameByBuildingsiteId(buildingsiteid) + "-" + workyear + "年" + workmonth + "月考勤表.xls"));
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
	
	@RequestMapping("/changeWorkday")
	@ResponseBody
	public String changeWorkday(Workday workday, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		workday.setProjectid(Integer.valueOf(UtilPackage.projectid));
		try
		{
			workdayService.changeWorkday(workday);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
			return "fail";
		}
		
		return "success";
	}
	
	@RequestMapping("/getWorkdayByWorkdayDetail")
	@ResponseBody
	public String getWorkdayByWorkdayDetail(String builderid, String buildingsiteid, String workyear, String workmonth, String workday, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = workdayService.getWorkdayByWorkdayDetail(builderid, buildingsiteid, workyear, workmonth, workday);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/getWorkyearListByBuildingsiteidAndFilterValue")
	@ResponseBody
	public String getWorkyearListByBuildingsiteidAndFilterValue(String filterValue, String buildingsiteid, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = workdayService.getWorkyearListByBuildingsiteidAndFilterValue(filterValue, buildingsiteid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/getWorkmonthListByBuildingsiteidAndWorkyearAndFilterValue")
	@ResponseBody
	public String getWorkmonthListByBuildingsiteidAndWorkyearAndFilterValue(String filterValue, String buildingsiteid, String workyear, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = workdayService.getWorkmonthListByBuildingsiteidAndWorkyearAndFilterValue(filterValue, buildingsiteid, workyear);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/getWorkyearListByBuildingsiteid")
	@ResponseBody
	public String getWorkyearListByBuildingsiteid(String buildingsiteid, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = workdayService.getWorkyearListByBuildingsiteid(buildingsiteid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/getWorkmonthListByBuildingsiteidAndWorkyear")
	@ResponseBody
	public String getWorkmonthListByBuildingsiteidAndWorkyear(String buildingsiteid, String workyear, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = workdayService.getWorkmonthListByBuildingsiteidAndWorkyear(buildingsiteid, workyear);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	@RequestMapping("/downloadWorkdayForYearPage")
	public String downloadWorkdayForYearPage(String currentPage, String pageSize, String filterValue, String orderValue, String buildingsiteid, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
//		if(UtilPackage.getMonth(UtilPackage.startyear, UtilPackage.startmonth, UtilPackage.endyear, UtilPackage.endmonth) > 12)
//		{
//			response.sendRedirect("errorDownloadPage.html?errorMonth=12");
//			return null;
//		}
		
		try
        {
			HSSFWorkbook wb = workdayService.getWorkdayListForYear(filterValue, pageSize, currentPage, orderValue, buildingsiteid, UtilPackage.projectid);

            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + UtilPackage.encodeFilename(request, buildingsiteService.getBuildingsiteNameByBuildingsiteId(buildingsiteid) + "-点工工时汇总.xls"));
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
	
	@RequestMapping("/loadWorkdayForYearPage")
	public String loadWorkdayForYearPage(String currentPage, String pageSize, String filterValue, String orderValue, String buildingsiteid, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		List<Workday> workdayList = null;
		try
		{
			workdayList = workdayService.showWorkdayListForYear(filterValue, pageSize, currentPage, orderValue, buildingsiteid, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = workdayService.getPageWidgetForYear(filterValue, pageSize, currentPage, buildingsiteid, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		List<Map<String, String>> monthList = null;
		try
		{
			List<MonthEntity> monthEntityList = workdayService.getMonthListForWorkdayYear(filterValue, pageSize, currentPage, buildingsiteid, UtilPackage.projectid);
			monthList = UtilPackage.formatMonthList(monthEntityList);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("workdayList", workdayList);
		req.setAttribute("monthList", monthList);
		req.setAttribute("orderByParam", orderValue);
		
		return "grids/workdayforyearcheckout_grid";
	}
	
	@RequestMapping("/loadWorkdayForAllPage")
	public String loadWorkdayForAllPage(String currentPage, String pageSize, String filterValue, String orderValue, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		List<Workday> workdayList = null;
		try
		{
			workdayList = workdayService.showWorkdayListForAll(filterValue, pageSize, currentPage, orderValue, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = workdayService.getPageWidgetForAll(filterValue, pageSize, currentPage, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		List<Map<String, String>> bMapList = null;
		try
		{
			List<Buildingsite> buildingsiteList = workdayService.getBuildingsiteListForWorkdayAll(filterValue, pageSize, currentPage, UtilPackage.projectid);
			bMapList = UtilPackage.formatBuildingsiteList(buildingsiteList);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("workdayList", workdayList);
		req.setAttribute("buildingsiteList", bMapList);
		req.setAttribute("orderByParam", orderValue);
		
		return "grids/workdayforallcheckout_grid";
	}
	
	@RequestMapping("/downloadWorkdayPageForAll")
	public String downloadWorkdayPageForAll(String currentPage, String pageSize, String filterValue, String orderValue, HttpServletRequest request, HttpServletResponse response) throws ServiceException
	{
		try
        {
			HSSFWorkbook wb = workdayService.getWorkdayListForAll(filterValue, pageSize, currentPage, orderValue, UtilPackage.projectid);

            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + UtilPackage.encodeFilename(request, "点工工资汇总.xls"));
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
	
	@RequestMapping("/getWorkdayDetailInfo")
	@ResponseBody
	public String getWorkdayDetailInfo(String builderid, String buildingsiteid, String workyear, String workmonth, String workday, HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = workdayService.getWorkdayDetailInfo(builderid, buildingsiteid, workyear, workmonth, workday);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		String str = jsonObject.toString();
		return str;
	}
	
	/**
	 * 
	* 方法描述 :导入(不存在的工地、工人不导入) 
	* 创建者：刘宁林 
	* 项目名称： WorkingTimeRecordSystem
	* 类名： WorkdayController.java
	* 版本： v1.0
	* 创建时间： 2014-11-21 上午10:17:58
	* @param file
	* @param request
	* @param response
	* @return
	* @throws IOException
	* @throws JSONException String
	 */
	@RequestMapping(value="/uploadWorkdayExcel",method=RequestMethod.POST)
	@ResponseBody
	public String uploadWorkdayExcel(@RequestParam(value="file", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException
	{
		//备份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		byte[] bytes = file.getBytes();  
        String sep = System.getProperty("file.separator");  
        String uploadDir = request.getRealPath("/")+"upload"+sep+"工时Excel上传"+sep+sdf.format(new Date());  
        File dirPath = new File(uploadDir);  
        if (!dirPath.exists()) {  
            dirPath.mkdirs();  
        }  
        File uploadedFile = new File(uploadDir + sep  
                + file.getOriginalFilename());  
        FileCopyUtils.copy(bytes, uploadedFile);  
        
        InputStream is = file.getInputStream();
        if (!is.markSupported()) {  
            is = new PushbackInputStream(is, 8);
        }  
        
        int flagNum = -1;
        
        try
        {
        	flagNum = workdayService.processExcelData(file.getOriginalFilename(), is);
        }
        catch (Exception e) {
        	return "-1";//未知错误
		}
        
        return flagNum + "";
	}
	
	/**
	 * 
	* 方法描述 : 粗导入（不存在的工地、工人添加操作）
	* 创建者：刘宁林 
	* 项目名称： WorkingTimeRecordSystem
	* 类名： WorkdayController.java
	* 版本： v1.0
	* 创建时间： 2014-11-21 上午10:17:41
	* @param file
	* @param request
	* @param response
	* @return
	* @throws IOException
	* @throws JSONException String
	 */
	@RequestMapping(value="/uploadWorkdayExcelRAW",method=RequestMethod.POST)
	@ResponseBody
	public String uploadWorkdayExcelRAW(@RequestParam(value="file", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException
	{
		//备份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		byte[] bytes = file.getBytes();  
        String sep = System.getProperty("file.separator");  
        String uploadDir = request.getRealPath("/")+"upload"+sep+"工时Excel上传"+sep+sdf.format(new Date());  
        File dirPath = new File(uploadDir);  
        if (!dirPath.exists()) {  
            dirPath.mkdirs();  
        }  
        File uploadedFile = new File(uploadDir + sep  
                + file.getOriginalFilename());  
        FileCopyUtils.copy(bytes, uploadedFile);  
        
        InputStream is = file.getInputStream();
        if (!is.markSupported()) {  
            is = new PushbackInputStream(is, 8);
        }  
        
        int flagNum = -1;
        
        try
        {
        	flagNum = workdayService.processExcelDataRAW(file.getOriginalFilename(), is);
        }
        catch (Exception e) {
        	return "-1";//未知错误
		}
        
        return flagNum + "";
	}
}
