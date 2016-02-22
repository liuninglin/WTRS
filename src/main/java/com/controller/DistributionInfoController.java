package com.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.exception.ServiceException;
import com.common.util.UtilPackage;
import com.entity.DistributionInfo;
import com.entity.PageWidget;
import com.service.DistributionInfoService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-21 下午04:34:27
* 版本号： v1.0
*/
@Controller
public class DistributionInfoController
{
	@Resource(name = "distributioninfoService")
	private DistributionInfoService<DistributionInfo> distributionInfoService;
	
	@RequestMapping("/loadDistributionInfoPage")
	public String loadDistributionInfoPage(String currentPage, String pageSize, String filterValue, String orderValue, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		List<DistributionInfo> distributionInfoList = null;
		try
		{
			distributionInfoList = distributionInfoService.showDistributionInfoList(filterValue, pageSize, currentPage, orderValue, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		PageWidget pageWidget = null;
		try
		{
			pageWidget = distributionInfoService.getPageWidget(filterValue, pageSize, currentPage, UtilPackage.projectid);
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		req.setAttribute("pageWidget", pageWidget);
		req.setAttribute("distributionInfoList", distributionInfoList);
		req.setAttribute("orderByParam", orderValue);
		
		return "grids/distributioninfocheckout_grid";
	}
	
	@RequestMapping("/downloadDistributionInfoPage")
	public String downloadDistributionInfoPage(String currentPage, String pageSize, String filterValue, String orderValue, HttpServletRequest request, HttpServletResponse response) throws ServiceException
	{
		try
        {
			HSSFWorkbook wb = distributionInfoService.getDistributionInfoListForAll(filterValue, pageSize, currentPage, orderValue, UtilPackage.projectid);

            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + UtilPackage.encodeFilename(request, "工资分配表.xls"));
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
