package com.service;

import java.util.List;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;

import com.common.exception.ServiceException;
import com.entity.PageWidget;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-10 下午05:44:28
* 版本号： v1.0
* @param <T>
*/
public interface BuildingsiteService<T> {
	public List<T> showBuildingsiteList(String filterValue, String pageSize, String currentPage, String orderValue, String showtype) throws ServiceException;
	
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String showtype) throws ServiceException;
	
	public boolean addBuildingsite(T buildingsite) throws ServiceException;
	
	public JSONObject getBuildingsiteByBuildingsiteId(String buildingsiteId) throws ServiceException, JSONException;
	
	public boolean editBuildingsite(T buildingsite) throws ServiceException;
	
	public boolean reStartBuildingsites(String buildingsiteIds) throws ServiceException;
	
	public boolean removeBuildingsites(String buildingsiteIds) throws ServiceException;
	
	public JSONObject getAllBuildingsitesForWorkday(String projectid) throws ServiceException, JSONException;
	
	public JSONObject getBuildingsiteListByFilterValue(String filterValue) throws ServiceException, JSONException;
	
	public JSONObject getAllBuildingsitesForPackagework(String projectid) throws ServiceException, JSONException;
	
	public JSONObject getAllBuildingsitesForFood(String projectid) throws ServiceException, JSONException;
	
	public String getBuildingsiteNameByBuildingsiteId(String buildingsiteId);
	
	public int getBuildingsiteIdByBuildingsiteName(String buildingsiteName);
}
