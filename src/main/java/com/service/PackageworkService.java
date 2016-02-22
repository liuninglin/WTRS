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
* 创建时间： 2014-2-17 下午02:33:34
* 版本号： v1.0
* @param <T>
*/
public interface PackageworkService<T> {
	public List<T> showPackageworkList(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteId) throws ServiceException;
	
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String buildingsiteId) throws ServiceException;
	
	public boolean changePackagework(T packagework) throws ServiceException;
	
	public JSONObject getPackageworkByBuilderidAndBuildingsiteid(String buildingsiteId, String builderId) throws ServiceException, JSONException;
	
	public boolean removePackageworks(String builderIds, String buildingsiteId, String projectid) throws ServiceException;
	
	public List<T> showPackageworkListForAll(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException;
	
	public PageWidget getPageWidgetForAll(String filterValue, String pageSize, String currentPage, String projectid) throws ServiceException;
	
	public HSSFWorkbook getPackageworkList(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteid, String projectid) throws ServiceException;
	
	public HSSFWorkbook getPackageworkListForAll(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException;
}
