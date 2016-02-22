package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.common.exception.ServiceException;
import com.entity.Buildingsite;
import com.entity.MonthEntity;
import com.entity.PageWidget;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-12 下午02:05:00
* 版本号： v1.0
* @param <T>
*/
public interface WorkdayService<T> {
	public List<T> showWorkdayList(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteid, String workyear, String workmonth) throws ServiceException;
	
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String buildingsiteid, String workyear, String workmonth) throws ServiceException;
	
	public boolean changeWorkday(T workday) throws ServiceException;
	
	public JSONObject getWorkdayByWorkdayDetail(String builderid, String buildingsiteid, String workyear, String workmonth, String workday) throws ServiceException, JSONException;
	
	public JSONObject getWorkyearListByBuildingsiteidAndFilterValue(String filterValue, String buildingsiteid) throws ServiceException, JSONException;
	
	public JSONObject getWorkyearListByBuildingsiteid(String buildingsiteid) throws ServiceException, JSONException;
	
	public JSONObject getWorkmonthListByBuildingsiteidAndWorkyear(String buildingsiteid, String workyear) throws ServiceException, JSONException;
	
	public JSONObject getWorkmonthListByBuildingsiteidAndWorkyearAndFilterValue(String filterValue, String buildingsiteid, String workyear) throws ServiceException, JSONException;
	
	public List<T> showWorkdayListForYear(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteid, String projectid) throws ServiceException;
	
	public PageWidget getPageWidgetForYear(String filterValue, String pageSize, String currentPage, String buildingsiteid, String projectid) throws ServiceException;
	
	public List<T> showWorkdayListForAll(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException;
	
	public PageWidget getPageWidgetForAll(String filterValue, String pageSize, String currentPage, String projectid) throws ServiceException;
	
	public HSSFWorkbook getWorkdayListForDay(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteid, String workyear, String workmonth) throws ServiceException;
	
	public HSSFWorkbook getWorkdayListForYear(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteid, String projectid) throws ServiceException;
	
	public HSSFWorkbook getWorkdayListForAll(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException;
	
	public JSONObject getWorkdayDetailInfo(String builderid, String buildingsiteid, String workyear, String workmonth, String workday) throws ServiceException, JSONException;
	
	public int processExcelData(String fileName, InputStream is) throws ServiceException, IOException, InvalidFormatException;
	
	public int processExcelDataRAW(String fileName, InputStream is) throws ServiceException, IOException, InvalidFormatException;
	
	public List<MonthEntity> getMonthListForWorkdayYear(String filterValue, String pageSize, String currentPage, String buildingsiteid, String projectid) throws ServiceException;
	
	public List<Buildingsite> getBuildingsiteListForWorkdayAll(String filterValue, String pageSize, String currentPage, String projectid) throws ServiceException;
}
