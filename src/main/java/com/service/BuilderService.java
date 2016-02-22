package com.service;

import java.util.List;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;

import com.common.exception.ServiceException;
import com.entity.PageWidget;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:04:31
* 版本号： v1.0
*/
public interface BuilderService<T> {
	public List<T> showBuilderList(String filterValue, String pageSize, String currentPage, String orderValue, String showtype) throws ServiceException;
	
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String showtype) throws ServiceException;
	
	public boolean addBuilder(T builder) throws ServiceException;
	
	public JSONObject getBuilderByBuilderId(String builderId) throws ServiceException, JSONException;
	
	public boolean editBuilder(T builder) throws ServiceException;
	
	public boolean removeBuilders(String builderIds) throws ServiceException;
	
	public boolean reStartBuilders(String builderIds) throws ServiceException;
	
	public JSONObject showBuilderListByFilterValue(String filterValue) throws ServiceException, JSONException;
	
	public JSONObject showBuilderListByFilterValueForPackagework(String filterValue) throws ServiceException, JSONException;
	
	public JSONObject showBuilderListByFilterValueForTimework(String filterValue) throws ServiceException, JSONException;
	
	public JSONObject showBuilderListByFilterValueForTimeworkForWorkdayCheckin(String filterValue, String buildingsiteid, String workyear, String workmonth) throws ServiceException, JSONException;
	
	public int getBuilderIdByBuilderName(String builderName);
}
