package com.service;

import java.util.List;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.common.exception.ServiceException;
import com.entity.PageWidget;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-20 上午12:29:37
* 版本号： v1.0
* @param <T>
*/
public interface FoodService<T> {
	public List<T> showFoodList(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteid, String projectid) throws ServiceException;
	
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String buildingsiteid, String projectid) throws ServiceException;
	
	public boolean changeFood(T food) throws ServiceException;
	
	public List<T> showFoodListForAll(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException;
	
	public PageWidget getPageWidgetForAll(String filterValue, String pageSize, String currentPage, String projectid) throws ServiceException;
	
	public HSSFWorkbook getFoodListForMonth(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteid, String projectid) throws ServiceException;
	
	public HSSFWorkbook getFoodListForAll(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException;
	
	public JSONObject getFoodDetailInfo(String builderid, String buildingsiteid, String foodyear, String foodmonth) throws ServiceException, JSONException;
}