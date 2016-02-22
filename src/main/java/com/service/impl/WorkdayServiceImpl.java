package com.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.json.JSONArray;
import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.exception.ServiceException;
import com.common.util.PinYinUtils;
import com.common.util.UtilPackage;
import com.dao.BuilderDao;
import com.dao.BuildingsiteDao;
import com.dao.WorkdayDao;
import com.entity.Builder;
import com.entity.Buildingsite;
import com.entity.MonthEntity;
import com.entity.PageWidget;
import com.entity.Workday;
import com.entity.WorkdayDetail;
import com.service.WorkdayService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:04:40
* 版本号： v1.0
*/
@Service("workdayService")
public class WorkdayServiceImpl<T extends Workday> implements WorkdayService<T> {

	@Autowired
	private WorkdayDao<T> workdayDao;

	@Autowired
	private BuildingsiteDao<Buildingsite> buildingsiteDao;
	
	@Autowired
	private BuilderDao<Builder> builderDao;
	
	@Override
	public List<T> showWorkdayList(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteid, String workyear, String workmonth) throws ServiceException
	{	
		int startIndex = (Integer.valueOf(currentPage) - 1)* Integer.valueOf(pageSize);
		int endIndex = (Integer.valueOf(currentPage))* Integer.valueOf(pageSize);
		if(Integer.valueOf(pageSize) == -1)
		{
			startIndex = 0;
			endIndex = 10000000;
		}
		
		filterValue = filterCharacter(filterValue);
		
		if("".equals(orderValue))
		{
			orderValue = "tmp2.buildername asc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		List<T> workdayList = workdayDao.getWorkdayList(filterValue, startIndex, endIndex, orderValue, UtilPackage.projectid, buildingsiteid, workyear, workmonth);
		for(Workday workday : workdayList)
		{
			workday.setWorkdayDetailList(workdayDao.getWorkdayDetailList(buildingsiteid, workday.getBuilderid() + "", workyear, workmonth));
		}
		
		return workdayList;
	}
		
	@Override
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String buildingsiteid, String workyear, String workmonth) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		int totalCount = workdayDao.getTotalCount(filterValue, UtilPackage.projectid, buildingsiteid, workyear, workmonth);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		if(Integer.valueOf(pageSize) == -1)
		{
			totalPages = 1;
		}
		
		int startIndex = 0;
		int endIndex = 0;
		
		if(totalPages == Integer.valueOf(currentPage))
		{
			startIndex = Integer.valueOf(pageSize) * (totalPages - 1);
			endIndex = (totalPages % Integer.valueOf(currentPage)) + startIndex;
			
			if(Integer.valueOf(pageSize) == -1)
			{
				endIndex = totalCount - 1;
			}
		}
		
		PageWidget pageWidget = new PageWidget();
		pageWidget.setPageSize(pageSize);
		pageWidget.setTotalCount(totalCount + "");
		pageWidget.setTotalPages(totalPages + "");
		pageWidget.setCurrentPage(currentPage);
		pageWidget.setStartIndex(startIndex + "");
		pageWidget.setEndIndex(endIndex + "");
		
		return pageWidget;
	}
	
	public String filterCharacter(String filterValue)
	{
		boolean flag = false;
		
		if(null == filterValue || "".equals(filterValue))
		{
			filterValue = "%";
			flag = true;
		}
		if(filterValue.contains("*"))
		{
			filterValue = filterValue.replaceAll("\\*", "%");
			flag = true;
		}
		if(filterValue.contains("?"))
		{
			filterValue = filterValue.replaceAll("\\?", "_");
			flag = true;
		}
		if(filterValue.contains(" "))
		{
			filterValue = filterValue.replaceAll(" ", "%");
			flag = true;
		}
		
		if(!flag)
		{
			filterValue = "%" + filterValue + "%";
		}
		
		return filterValue;
	}

	@Override
	public boolean changeWorkday(T workday) throws ServiceException
	{
		Workday workdayTemp = (Workday)workday;
		workdayTemp.setWorkmonth(UtilPackage.formatMonthOrDay(Integer.valueOf(workdayTemp.getWorkmonth())));
		workdayTemp.setWorkday(UtilPackage.formatMonthOrDay(Integer.valueOf(workdayTemp.getWorkday())));
		if("".equals(workdayTemp.getWorkcount().trim()) || "0".equals(workdayTemp.getWorkcount().trim()))
		{
			return workdayDao.removeWorkday(workday);
		}
		
		Workday workdayTemp2 = workdayDao.getWorkdayByWorkdayDetail(workdayTemp.getBuilderid() + "", workdayTemp.getBuildingsiteid() + "", workdayTemp.getWorkyear(), workdayTemp.getWorkmonth(), workdayTemp.getWorkday());
		if(workdayTemp2 == null)
		{
			return workdayDao.addWorkday(workday);
		}
		else
		{
			if("".equals(workday.getWorkcount().trim()) || "0".equals(workday.getWorkcount().trim()))
			{
				return workdayDao.removeWorkday(workday);
			}
			else
			{
				if("#".equals(workday.getWorkcount().trim()))
				{
					return workdayDao.editWorkdayDetailInfo(workday);
				}
				else
				{
					if(workday.getOtherinfo() == null && workday.getBuildinfo() == null)
					{
						return workdayDao.editWorkday(workday);
					}
					else
					{
						return workdayDao.editWorkdayInfo(workday);
					}
				}
			}
		}
	}

	@Override
	public JSONObject getWorkdayByWorkdayDetail(String builderid, String buildingsiteid, String workyear, String workmonth, String workday) throws ServiceException, JSONException
	{
		Workday workdayObject = workdayDao.getWorkdayByWorkdayDetail(builderid, buildingsiteid, workyear, workmonth, workday);
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("builderid", workdayObject.getBuilderid());
		jsonObject.append("buildingsiteid", workdayObject.getBuildingsiteid());
		jsonObject.append("buildinfo", workdayObject.getBuildinfo());
		jsonObject.append("workyear", workdayObject.getWorkyear());
		jsonObject.append("workmonth", workdayObject.getWorkmonth());
		jsonObject.append("workday", workdayObject.getWorkday());
		jsonObject.append("workdate", workdayObject.getWorkdate());
		jsonObject.append("workcount", workdayObject.getWorkcount());
		jsonObject.append("otherinfo", workdayObject.getOtherinfo());
		
		return jsonObject;
	}

	@Override
	public JSONObject getWorkyearListByBuildingsiteid(String buildingsiteid) throws ServiceException, JSONException
	{
		List<String> workyearList = workdayDao.getWorkyearListByBuildingsiteid(buildingsiteid);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(String workyear : workyearList)
		{
			jsonArray.add(workyear);
		}
		jsonObject.append("workyearArray", jsonArray);
		return jsonObject;
	}

	@Override
	public JSONObject getWorkmonthListByBuildingsiteidAndWorkyear(String buildingsiteid, String workyear) throws ServiceException, JSONException
	{
		List<String> workmonthList = workdayDao.getWorkmonthListByBuildingsiteidAndWorkyear(buildingsiteid, workyear);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(String workmonth : workmonthList)
		{
			jsonArray.add(workmonth);
		}
		jsonObject.append("workmonthArray", jsonArray);
		return jsonObject;
	}

	@Override
	public List<T> showWorkdayListForYear(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteid, String projectid)
			throws ServiceException
	{
		int startIndex = (Integer.valueOf(currentPage) - 1)* Integer.valueOf(pageSize);
		int endIndex = (Integer.valueOf(currentPage))* Integer.valueOf(pageSize);
		if(Integer.valueOf(pageSize) == -1)
		{
			startIndex = 0;
			endIndex = 10000000;
		}
		
		filterValue = filterCharacter(filterValue);
		
		if("".equals(orderValue))
		{
			orderValue = "tmp2.buildershortname asc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		List<T> workdayList = workdayDao.getWorkdayListForYear(filterValue, startIndex, endIndex, orderValue, buildingsiteid, UtilPackage.projectid);
		for(Workday workday : workdayList)
		{
			workday.setWorkdayDetailList(workdayDao.getWorkdayDetailListForYear(buildingsiteid, workday.getBuilderid() + "", UtilPackage.projectid));
		}
		
		return workdayList;
	}

	@Override
	public PageWidget getPageWidgetForYear(String filterValue, String pageSize, String currentPage, String buildingsiteid, String projectid) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		int totalCount = workdayDao.getTotalCountForYear(filterValue, buildingsiteid, UtilPackage.projectid);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		if(Integer.valueOf(pageSize) == -1)
		{
			totalPages = 1;
		}
		
		int startIndex = 0;
		int endIndex = 0;
		
		if(totalPages == Integer.valueOf(currentPage))
		{
			startIndex = Integer.valueOf(pageSize) * (totalPages - 1);
			endIndex = (totalPages % Integer.valueOf(currentPage)) + startIndex;
			
			if(Integer.valueOf(pageSize) == -1)
			{
				endIndex = totalCount - 1;
			}
		}
		
		PageWidget pageWidget = new PageWidget();
		pageWidget.setPageSize(pageSize);
		pageWidget.setTotalCount(totalCount + "");
		pageWidget.setTotalPages(totalPages + "");
		pageWidget.setCurrentPage(currentPage);
		pageWidget.setStartIndex(startIndex + "");
		pageWidget.setEndIndex(endIndex + "");
		
		return pageWidget;
	}

	@Override
	public PageWidget getPageWidgetForAll(String filterValue, String pageSize, String currentPage, String projectid) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		int totalCount = workdayDao.getTotalCountForAll(filterValue, UtilPackage.projectid);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		if(Integer.valueOf(pageSize) == -1)
		{
			totalPages = 1;
		}
		
		int startIndex = 0;
		int endIndex = 0;
		
		if(totalPages == Integer.valueOf(currentPage))
		{
			startIndex = Integer.valueOf(pageSize) * (totalPages - 1);
			endIndex = (totalPages % Integer.valueOf(currentPage)) + startIndex;
			
			if(Integer.valueOf(pageSize) == -1)
			{
				endIndex = totalCount - 1;
			}
		}
		
		PageWidget pageWidget = new PageWidget();
		pageWidget.setPageSize(pageSize);
		pageWidget.setTotalCount(totalCount + "");
		pageWidget.setTotalPages(totalPages + "");
		pageWidget.setCurrentPage(currentPage);
		pageWidget.setStartIndex(startIndex + "");
		pageWidget.setEndIndex(endIndex + "");
		
		return pageWidget;
	}

	@Override
	public List<T> showWorkdayListForAll(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException
	{
		int startIndex = (Integer.valueOf(currentPage) - 1)* Integer.valueOf(pageSize);
		int endIndex = (Integer.valueOf(currentPage))* Integer.valueOf(pageSize);
		if(Integer.valueOf(pageSize) == -1)
		{
			startIndex = 0;
			endIndex = 10000000;
		}
		
		filterValue = filterCharacter(filterValue);
		
		if("".equals(orderValue))
		{
			orderValue = "tmp.builderpinyin asc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		List<T> workdayList = workdayDao.getWorkdayListForAll(filterValue, startIndex, endIndex, orderValue, UtilPackage.projectid);
		for(Workday workday : workdayList)
		{
			workday.setWorkdayDetailList(workdayDao.getWorkdayDetailListForAll(workday.getBuilderid() + ""));
		}
		
		return workdayList;
	}

	@Override
	public HSSFWorkbook getWorkdayListForDay(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteid, String workyear, String workmonth)
			throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		if("".equals(orderValue))
		{
			orderValue = "tmp2.buildername asc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		int totalCount = workdayDao.getTotalCount(filterValue, UtilPackage.projectid, buildingsiteid, workyear, workmonth);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		try
        {
            HSSFWorkbook workBook = new HSSFWorkbook(this.getClass().getResourceAsStream("/../../static/templet/日工时报表模板.xls")) ;
            HSSFSheet sheet = workBook.getSheet("模板");
            workBook.setSheetName(0, "报表");
            
//            for(int i = totalPages*26; i < totalPages*26+(8-totalPages)*26; i++)
//            {
//            	if(null != sheet.getRow(i))
//            	{
//            		POIUtils.removeRow(sheet, i);
//            	}
//            }
            workBook.setPrintArea(0, 0, 34, 0, totalPages*26-1);
            
            for(int i = 1; i <= totalPages; i++)
            {
            	int startIndex = (Integer.valueOf(i) - 1)* Integer.valueOf(pageSize);
            	int endIndex = (Integer.valueOf(i))* Integer.valueOf(pageSize);
            	List<T> workdayList = workdayDao.getWorkdayList(filterValue, startIndex, endIndex, orderValue, UtilPackage.projectid, buildingsiteid, workyear, workmonth);
	      		for(Workday workday : workdayList)
	      		{
	      			workday.setWorkdayDetailList(workdayDao.getWorkdayDetailList(buildingsiteid, workday.getBuilderid() + "", workyear, workmonth));
	      		}
      		
	      		sheet.getRow((i-1)*26).getCell(2).setCellValue(buildingsiteDao.getBuildingsiteNameByBuildingsiteId(buildingsiteid)+"考勤表");
      			sheet.getRow((i-1)*26).getCell(29).setCellValue(workyear+"年"+workmonth+"月");
      			
	      		for(int j = 0; j < workdayList.size(); j++)
	      		{
	      			Workday workday = workdayList.get(j);
	      			
	      			sheet.getRow((i-1)*26 + 2 + j).getCell(1).setCellValue(workday.getBuildername());
	      			
	      			List<WorkdayDetail> workdayDetailList = workday.getWorkdayDetailList();
	      			for(int w = 0; w < workdayDetailList.size(); w++)
	      			{
	      				WorkdayDetail workdayDetail = workdayDetailList.get(w);
	      				int dayNumber = Integer.valueOf(workdayDetail.getWorkday());
	      				sheet.getRow((i-1)*26 + 2 + j).getCell(dayNumber+1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	      				sheet.getRow((i-1)*26 + 2 + j).getCell(dayNumber+1).setCellValue(Double.valueOf(workdayDetail.getWorkcount()));
	      			}
	      		}
            }
            
            sheet.setForceFormulaRecalculation(true);
            
            return (workBook);
        }
        catch (Exception e)
        {
        	System.out.println(e);
        }
		return null;
	}

	@Override
	public HSSFWorkbook getWorkdayListForYear(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteid, String projectid) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		if("".equals(orderValue))
		{
			orderValue = "tmp.buildername asc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		int totalCount = workdayDao.getTotalCountForYear(filterValue, buildingsiteid, UtilPackage.projectid);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		try
        {
			HSSFWorkbook workBook = new HSSFWorkbook(this.getClass().getResourceAsStream("/../../static/templet/工地考勤汇总模板.xls"));
            HSSFSheet sheet = workBook.getSheet("模板");
            workBook.setSheetName(0, "报表");
            
//            for(int i = totalPages*26; i < totalPages*26+(8-totalPages)*26; i++)
//            {
//            	if(null != sheet.getRow(i))
//            	{
//            		POIUtils.removeRow(sheet, i);
//            	}
//            }
            workBook.setPrintArea(0, 0, 15, 0, totalPages*25-1);
            
            for(int i = 1; i <= totalPages; i++)
            {
	      		int startIndex = (Integer.valueOf(i) - 1)* Integer.valueOf(pageSize);
        		int endIndex = (Integer.valueOf(i))* Integer.valueOf(pageSize);
                List<T> workdayList = workdayDao.getWorkdayListForYear(filterValue, startIndex, endIndex, orderValue, buildingsiteid, UtilPackage.projectid);
        		for(Workday workday : workdayList)
        		{
        			workday.setWorkdayDetailList(workdayDao.getWorkdayDetailListForYear(buildingsiteid, workday.getBuilderid() + "", UtilPackage.projectid));
        		}
	      		
	      		sheet.getRow((i-1)*25).getCell(2).setCellValue(buildingsiteDao.getBuildingsiteNameByBuildingsiteId(buildingsiteid)+"考勤汇总表");
	      		int monthcount = 0;
      			for(int year = Integer.valueOf(UtilPackage.startyear); year <= Integer.valueOf(UtilPackage.endyear); year++)
      			{
      				if(UtilPackage.startyear.equals(UtilPackage.endyear))
      				{
      					for(int month = Integer.valueOf(UtilPackage.startmonth); month <= Integer.valueOf(UtilPackage.endmonth); month++)
          				{
          					sheet.getRow((i-1)*25+1).getCell(2+monthcount).setCellValue(month + "月");
          					monthcount++;
          				}
      				}
      				else
      				{
      					if(year == Integer.valueOf(UtilPackage.startyear))
      					{
      						for(int month = Integer.valueOf(UtilPackage.startmonth); month <= 12; month++)
              				{
              					sheet.getRow((i-1)*25+1).getCell(2+monthcount).setCellValue(month + "月");
              					monthcount++;
              				}
      					}
      					else if(year == Integer.valueOf(UtilPackage.endyear))
      					{
      						for(int month = 1; month <= Integer.valueOf(UtilPackage.endmonth); month++)
              				{
              					sheet.getRow((i-1)*25+1).getCell(2+monthcount).setCellValue(month + "月");
              					monthcount++;
              				}
      					}
      					else
      					{
      						for(int month = 1; month <= 12; month++)
              				{
              					sheet.getRow((i-1)*25+1).getCell(2+monthcount).setCellValue(month + "月");
              					monthcount++;
              				}
      					}
      				}
      			}
	      		
	      		for(int j = 0; j < workdayList.size(); j++)
	      		{
	      			Workday workday = workdayList.get(j);
	      			
	      			sheet.getRow((i-1)*25 + 2 + j).getCell(1).setCellValue(workday.getBuildername());
	      			
	      			List<WorkdayDetail> workdayDetailList = workday.getWorkdayDetailList();
	      			for(int w = 0; w < workdayDetailList.size(); w++)
	      			{
	      				WorkdayDetail workdayDetail = workdayDetailList.get(w);
	      				int yearNumber = Integer.valueOf(workdayDetail.getWorkyear());
	      				int monthNumber = Integer.valueOf(workdayDetail.getWorkmonth());
	      				int monthTemp = UtilPackage.getMonth(UtilPackage.startyear, UtilPackage.startmonth, yearNumber + "", monthNumber + "");
	      				
	      				sheet.getRow((i-1)*25 + 2 + j).getCell(monthTemp+1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	      				sheet.getRow((i-1)*25 + 2 + j).getCell(monthTemp+1).setCellValue(Double.valueOf(workdayDetail.getWorkcount()));
	      			}
	      		}
            }
            sheet.setForceFormulaRecalculation(true);
            return (workBook);
        }
        catch (Exception e)
        {
        	System.out.println(e);
        }
		return null;
	}

	@Override
	public HSSFWorkbook getWorkdayListForAll(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		if("".equals(orderValue))
		{
			orderValue = "tmp.buildername asc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		int totalCount = workdayDao.getTotalCountForAll(filterValue, UtilPackage.projectid);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		try
        {
			HSSFWorkbook workBook = new HSSFWorkbook(this.getClass().getResourceAsStream("/../../static/templet/点工工资汇总模板.xls"));
            HSSFSheet sheet = workBook.getSheet("模板");
            workBook.setSheetName(0, "报表");
            
//            for(int i = totalPages*26; i < totalPages*26+(8-totalPages)*26; i++)
//            {
//            	if(null != sheet.getRow(i))
//            	{
//            		POIUtils.removeRow(sheet, i);
//            	}
//            }
            workBook.setPrintArea(0, 0, 13, 0, totalPages*25-1);
            
            for(int i = 1; i <= totalPages; i++)
            {
	      		int startIndex = (Integer.valueOf(i) - 1)* Integer.valueOf(pageSize);
        		int endIndex = (Integer.valueOf(i))* Integer.valueOf(pageSize);
        		
        		List<T> workdayList = workdayDao.getWorkdayListForAll(filterValue, startIndex, endIndex, orderValue, UtilPackage.projectid);
        		for(Workday workday : workdayList)
        		{
        			workday.setWorkdayDetailList(workdayDao.getWorkdayDetailListForAll(workday.getBuilderid() + ""));
        		}
        		
        		List<Map<String, String>> buildingsiteList = null;
        		buildingsiteList = UtilPackage.formatBuildingsitenameList(workdayList, "workday");
        		if(buildingsiteList.size() > 8)
        		{
        			return null;
        		}
        		
        		for(int x = 0; x < buildingsiteList.size(); x++)
        		{
        			sheet.getRow((i-1)*25+1).getCell(2+x).setCellValue(buildingsiteList.get(x).get("name"));
        		}
        		
	      		for(int j = 0; j < workdayList.size(); j++)
	      		{
	      			Workday workday = workdayList.get(j);
	      			
	      			sheet.getRow((i-1)*25 + 2 + j).getCell(1).setCellValue(workday.getBuildername());
	      			
	      			List<WorkdayDetail> workdayDetailList = workday.getWorkdayDetailList();
	      			for(int w = 0; w < workdayDetailList.size(); w++)
	      			{
	      				WorkdayDetail workdayDetail = workdayDetailList.get(w);
	      				String buildingsiteName = workdayDetail.getBuildingsitename();
	      				
	      				if(sheet.getRow((i-1)*25 + 1).getCell(2).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(2).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(2).setCellValue(Double.valueOf(workdayDetail.getWorkcount()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(3).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(3).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(3).setCellValue(Double.valueOf(workdayDetail.getWorkcount()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(4).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(4).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(4).setCellValue(Double.valueOf(workdayDetail.getWorkcount()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(5).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(5).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(5).setCellValue(Double.valueOf(workdayDetail.getWorkcount()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(6).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(6).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(6).setCellValue(Double.valueOf(workdayDetail.getWorkcount()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(7).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(7).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(7).setCellValue(Double.valueOf(workdayDetail.getWorkcount()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(8).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(8).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(8).setCellValue(Double.valueOf(workdayDetail.getWorkcount()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(9).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(9).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(9).setCellValue(Double.valueOf(workdayDetail.getWorkcount()));
	      				}
	      			}
	      			
	      			if(workday.getSalarymoney() != null)
	      			{
	      				sheet.getRow((i-1)*25 + 2 + j).getCell(11).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	      				sheet.getRow((i-1)*25 + 2 + j).getCell(11).setCellValue(Double.valueOf(workday.getSalarymoney()));
	      			}
	      		}
            }
            sheet.setForceFormulaRecalculation(true);
            return (workBook);
        }
        catch (Exception e)
        {
        }
		return null;
	}

	@Override
	public JSONObject getWorkdayDetailInfo(String builderid, String buildingsiteid, String workyear, String workmonth, String workday) throws ServiceException, JSONException
	{
		WorkdayDetail wd = workdayDao.getWorkdayDetailInfo(builderid, buildingsiteid, workyear, workmonth, workday);
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("buildinfo", wd.getBuildinfo());
		jsonObject.append("otherinfo", wd.getOtherinfo());
		return jsonObject;
	}

	
	public List<Workday> getWorkdayListFromExcel(String buildingsiteId, String workyear, String workmonth, Row row, boolean isRAW)
	{
		String builderName = row.getCell(1).getStringCellValue();
		if(builderName != null)
		{
			builderName = UtilPackage.replaceBlank(builderName);
			if(!builderName.equals(""))
			{
				if(isRAW)
				{
					Builder builder = builderDao.getBuilderByBuilderName(builderName);
					if(builder == null)
					{
						builder = new Builder();
						builder.setName(builderName);
						builder.setShortname(PinYinUtils.getPinYinHeadChar(builderName));
						builder.setPinyin(PinYinUtils.getPinYin(builderName));
						builder.setShowtype("1");
						builder.setOtherinfo("");
						builder.setAge("");
						builder.setHometown("");
						builder.setIdcard("");
						builder.setSex("");
						builder.setType("timework");
						
						builderDao.addBuilder(builder);
					}
				}
				
				List<Workday> workdayList = new ArrayList<Workday>();
				Builder builderTemp = builderDao.getBuilderByBuilderName(builderName);
				if(builderTemp == null)
				{
					return new ArrayList();
				}
				String builderId = builderTemp.getId() + "";
				
				
				for(int i = 2; i <= 32; i++)
				{
					Cell cell = row.getCell(i);
					if(cell != null)
					{
						if((cell.getCellType() == Cell.CELL_TYPE_STRING && !cell.getStringCellValue().trim().equals("")) || (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && cell.getNumericCellValue() != 0D))
						{
							Workday workday = getWorkdayFromExcelCell(buildingsiteId, builderId, workyear, workmonth, (i - 1) + "", cell);
							workdayList.add(workday);
						}
					}
				}
				
				return workdayList;
			}
		}
		
		return null;
	}
	
	
	public Workday getWorkdayFromExcelCell(String buildingsiteId, String builderId, String workyear, String workmonth, String workday, Cell cell)
	{
		Workday workdayObj = new Workday();
		workdayObj.setBuildingsiteid(Integer.valueOf(buildingsiteId));
		workdayObj.setBuilderid(Integer.valueOf(builderId));
		workdayObj.setProjectid(Integer.valueOf(UtilPackage.projectid));
		workdayObj.setBuildinfo("");
		workdayObj.setWorkyear(workyear);
		workdayObj.setWorkmonth(workmonth);
		workdayObj.setWorkday(workday);
		workdayObj.setWorkdate(workyear + "-" + workmonth + "-" + workday);
		if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
		{
			workdayObj.setWorkcount(cell.getNumericCellValue() + "");
		}
		else if(cell.getCellType() == Cell.CELL_TYPE_STRING)
		{
			workdayObj.setWorkcount(cell.getStringCellValue());
		}
		workdayObj.setOtherinfo("");
		
		return workdayObj;
	}
	
	@Override
	@Transactional(readOnly = false,  rollbackFor = Exception.class)
	public int processExcelData(String fileName, InputStream is) throws ServiceException, IOException, InvalidFormatException
	{
		int resultCode = 0;
		
		try
		{
			String buildingsiteId = null;
			List<Buildingsite> buildingsiteList = buildingsiteDao.getAllBuildingsiteList();		
			for(Buildingsite buildingsite : buildingsiteList)
			{
				if(fileName.contains(buildingsite.getName()))
				{
					buildingsiteId = buildingsite.getId() + "";
					break;
				}
			}
			
			if(buildingsiteId == null)
			{
				return 1;//系统中找不到此工地【文件名中应当包含系统的中的工地名】
			}
			
			
			Workbook workbook = null;

	        if (POIFSFileSystem.hasPOIFSHeader(is))
	        {
	            workbook = new HSSFWorkbook(is);
	        }
	        else if (POIXMLDocument.hasOOXMLHeader(is))
	        {
	            workbook = new XSSFWorkbook(OPCPackage.open(is));
	        }

	        if (workbook == null)
	        {
	            return 2;//无法解析此Excel文件
	        }

	        boolean flag = false;
	        
	        for (int i = 0; i < workbook.getNumberOfSheets(); i++)//获取每个Sheet表
	        {
	        	SimpleDateFormat sdf_year = new SimpleDateFormat("yyyy");
	        	SimpleDateFormat sdf_month = new SimpleDateFormat("MM");
	        	
	            Sheet sheet=workbook.getSheetAt(i);
	            Date date_temp = sheet.getRow(0).getCell(29).getDateCellValue();
	            if(date_temp != null)
	            {
	            	String workyear = sdf_year.format(date_temp);
		            String workmonth = sdf_month.format(date_temp);
		            
		            for(int j = 0; j <= sheet.getLastRowNum(); j++)
		            {
		            	Row rowTemp = sheet.getRow(j);
		            	if(rowTemp != null)
		            	{
		            		Cell cellTemp = rowTemp.getCell(0);
		            		if(cellTemp != null)
		            		{
		            			if(cellTemp.getCellType() == Cell.CELL_TYPE_STRING)
		            			{
		            				String strTemp = cellTemp.getStringCellValue();
		            				if(strTemp == null)
		            				{
		            					continue;
		            				}
		            				else
		            				{
		            					try
		            					{
		            						int intTemp = Integer.valueOf(strTemp);
		            						if(intTemp <= 0)
		            						{
		            							continue;
		            						}
		            					}
		            					catch (Exception e) {
		            						continue;
										}
		            				}
		            			}
		            			else if(cellTemp.getCellType() == Cell.CELL_TYPE_NUMERIC)
		            			{
		            				double doubleTemp = cellTemp.getNumericCellValue();
		            				if(doubleTemp <= 0D)
	        						{
	        							continue;
	        						}
		            			}
		            			else
		            			{
		            				continue;
		            			}
		            			
		            			List<Workday> workdayList = getWorkdayListFromExcel(buildingsiteId, workyear, workmonth, sheet.getRow(j), false);
		    	            	if(workdayList != null)
		    	            	{
		    	            		if(workdayList.size() == 0)
		    	            		{
		    	            			resultCode = 4;//此Excel中有部分工人不存在
		    	            		}
		    	            		
		    	            		for(Workday workday : workdayList)
		    	            		{
		    	            			Workday workdayTemp = workdayDao.getWorkdayByWorkdayDetail(workday.getBuilderid() + "", workday.getBuildingsiteid() + "", workday.getWorkyear(), workday.getWorkmonth(), workday.getWorkday());
		    	            			if(workdayTemp == null)
		    	            			{
		    	            				workdayDao.addWorkday((T)workday);
		    	            				flag = true;
		    	            			}
		    	            			else
		    	            			{
		    	            				workdayDao.editWorkday((T)workday);
		    	            				flag = true;
		    	            			}
		    	            		}
		    	            	}
		            		}
		            	}
		            }
	            }
	        }

	        
	        is.close();
	        
	    	if(!flag)
	    	{
	    		return 3;//此Excel文件中不存在工时信息
	    	}
	        
	    	return resultCode;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	@Transactional(readOnly = false,  rollbackFor = Exception.class)
	public int processExcelDataRAW(String fileName, InputStream is) throws ServiceException, IOException, InvalidFormatException
	{
		try
		{
			String buildingsiteName = fileName.split("\\.")[0];
			Buildingsite buildingsite = buildingsiteDao.getBuildingsiteByBuildingsiteName(buildingsiteName);
			if(buildingsite == null)
			{
				buildingsite = new Buildingsite();
				buildingsite.setName(buildingsiteName);
				buildingsite.setShortname(PinYinUtils.getPinYinHeadChar(buildingsiteName));
				buildingsite.setPinyin(PinYinUtils.getPinYin(buildingsiteName));
				buildingsite.setShowtype("1");
				buildingsite.setOtherinfo("");
				buildingsite.setAddress("");
				buildingsite.setStarttime("");
				buildingsite.setEndtime("");
				
				buildingsiteDao.addBuildingsite(buildingsite);
			}
			
			String buildingsiteId = buildingsiteDao.getBuildingsiteByBuildingsiteName(buildingsiteName).getId() + "";
			
			Workbook workbook = null;

	        if (POIFSFileSystem.hasPOIFSHeader(is))
	        {
	            workbook = new HSSFWorkbook(is);
	        }
	        else if (POIXMLDocument.hasOOXMLHeader(is))
	        {
	            workbook = new XSSFWorkbook(OPCPackage.open(is));
	        }

	        if (workbook == null)
	        {
	            return 2;//无法解析此Excel文件
	        }

	        for (int i = 0; i < workbook.getNumberOfSheets(); i++)//获取每个Sheet表
	        {
	        	SimpleDateFormat sdf_year = new SimpleDateFormat("yyyy");
	        	SimpleDateFormat sdf_month = new SimpleDateFormat("MM");
	        	
	            Sheet sheet=workbook.getSheetAt(i);
	            Date date_temp = sheet.getRow(0).getCell(29).getDateCellValue();
	            if(date_temp != null)
	            {
	            	String workyear = sdf_year.format(date_temp);
		            String workmonth = sdf_month.format(date_temp);
		            
		            for(int j = 0; j <= sheet.getLastRowNum(); j++)
		            {
		            	Row rowTemp = sheet.getRow(j);
		            	if(rowTemp != null)
		            	{
		            		Cell cellTemp = rowTemp.getCell(0);
		            		if(cellTemp != null)
		            		{
		            			if(cellTemp.getCellType() == Cell.CELL_TYPE_STRING)
		            			{
		            				String strTemp = cellTemp.getStringCellValue();
		            				if(strTemp == null)
		            				{
		            					continue;
		            				}
		            				else
		            				{
		            					try
		            					{
		            						int intTemp = Integer.valueOf(strTemp);
		            						if(intTemp <= 0)
		            						{
		            							continue;
		            						}
		            					}
		            					catch (Exception e) {
		            						continue;
										}
		            				}
		            			}
		            			else if(cellTemp.getCellType() == Cell.CELL_TYPE_NUMERIC)
		            			{
		            				double doubleTemp = cellTemp.getNumericCellValue();
		            				if(doubleTemp <= 0D)
	        						{
	        							continue;
	        						}
		            			}
		            			else
		            			{
		            				continue;
		            			}
		            				
		            			
		            			List<Workday> workdayList = getWorkdayListFromExcel(buildingsiteId, workyear, workmonth, sheet.getRow(j), true);
				            	if(workdayList != null)
				            	{
				            		for(Workday workday : workdayList)
				            		{
				            			Workday workdayTemp = workdayDao.getWorkdayByWorkdayDetail(workday.getBuilderid() + "", workday.getBuildingsiteid() + "", workday.getWorkyear(), workday.getWorkmonth(), workday.getWorkday());
				            			if(workdayTemp == null)
				            			{
				            				workdayDao.addWorkday((T)workday);
				            			}
				            			else
				            			{
				            				workdayDao.editWorkday((T)workday);
				            			}
				            		}
				            	}
		            		}
		            	}
		            }
	            }
	        }
	        
	        is.close();
	        
	        return 0;//成功
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public List<MonthEntity> getMonthListForWorkdayYear(String filterValue, String pageSize, String currentPage, String buildingsiteid, String projectid) throws ServiceException
	{
		int startIndex = (Integer.valueOf(currentPage) - 1)* Integer.valueOf(pageSize);
		int endIndex = (Integer.valueOf(currentPage))* Integer.valueOf(pageSize);
		if(Integer.valueOf(pageSize) == -1)
		{
			startIndex = 0;
			endIndex = 10000000;
		}
		
		filterValue = filterCharacter(filterValue);
		
		return workdayDao.getMonthListForWorkdayYear(filterValue, startIndex, endIndex, buildingsiteid, projectid);
	}

	@Override
	public List<Buildingsite> getBuildingsiteListForWorkdayAll(String filterValue, String pageSize, String currentPage, String projectid) throws ServiceException
	{
		int startIndex = (Integer.valueOf(currentPage) - 1)* Integer.valueOf(pageSize);
		int endIndex = (Integer.valueOf(currentPage))* Integer.valueOf(pageSize);
		if(Integer.valueOf(pageSize) == -1)
		{
			startIndex = 0;
			endIndex = 10000000;
		}
		
		filterValue = filterCharacter(filterValue);
		
		return workdayDao.getBuildingsiteListForWorkdayAll(filterValue, startIndex, endIndex, projectid);
	}

	@Override
	public JSONObject getWorkyearListByBuildingsiteidAndFilterValue(String filterValue, String buildingsiteid) throws ServiceException, JSONException
	{
		filterValue = filterCharacter(filterValue);
		List<String> workyearList = null;
		if(buildingsiteid != null && !"".equals(buildingsiteid))
		{
			workyearList = workdayDao.getWorkyearListByBuildingsiteidAndFilterValue(filterValue, buildingsiteid);
		}
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if((workyearList != null && workyearList.size() != 0))
		{
			for(String workyear : workyearList)
			{
				JSONObject jsonObjectTemp = new JSONObject();
				jsonObjectTemp.append("id", workyear);
				jsonObjectTemp.append("name", workyear);
				jsonArray.add(jsonObjectTemp);
			}
			jsonObject.append("workyearArray", jsonArray);
		}
		else
		{
			JSONObject jsonObjectTemp = new JSONObject();
			jsonObjectTemp.append("id", "-1");
			jsonObjectTemp.append("name", "-1");
			jsonArray.add(jsonObjectTemp);
			jsonObject.append("workyearArray", jsonArray);
		}
		return jsonObject;
	}

	@Override
	public JSONObject getWorkmonthListByBuildingsiteidAndWorkyearAndFilterValue(String filterValue, String buildingsiteid, String workyear) throws ServiceException, JSONException
	{
		filterValue = filterCharacter(filterValue);
		List<String> workmonthList = null;
		if((buildingsiteid != null && !"".equals(buildingsiteid)) && (workyear != null && !"".equals(workyear)))
		{
			workmonthList = workdayDao.getWorkmonthListByBuildingsiteidAndWorkyearAndFilterValue(filterValue, buildingsiteid, workyear);
		}
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if((workmonthList != null && workmonthList.size() != 0))
		{
			for(String workmonth : workmonthList)
			{
				JSONObject jsonObjectTemp = new JSONObject();
				jsonObjectTemp.append("id", workmonth);
				jsonObjectTemp.append("name", workmonth);
				jsonArray.add(jsonObjectTemp);
			}
			jsonObject.append("workmonthArray", jsonArray);
		}
		else
		{
			JSONObject jsonObjectTemp = new JSONObject();
			jsonObjectTemp.append("id", "-1");
			jsonObjectTemp.append("name", "-1");
			jsonArray.add(jsonObjectTemp);
			jsonObject.append("workmonthArray", jsonArray);
		}
		return jsonObject;
	}
}
