package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;


/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-10 下午05:39:02
* 版本号： v1.0
* @param <T>
*/
public interface BuildingsiteDao<T>
{
	public List<T> getAllBuildingsiteList() throws DataAccessException;
	
	public List<T> getBuildingsiteList(String filterValue, int startIndex, int endIndex, String orderValue, String showtype) throws DataAccessException;
	
	public boolean addBuildingsite(T buildingsite) throws DataAccessException;
	
	public boolean editBuildingsite(T buildingsite) throws DataAccessException;
	
	public boolean removeBuildingsites(String ids) throws DataAccessException;
	
	public boolean reStartBuildingsites(String ids) throws DataAccessException;
	
	public int getTotalCount(String filterValue, String showtype) throws DataAccessException;
	
	public T getBuildingsiteByBuildingsiteId(String buildingsiteId) throws DataAccessException;
	
	public T getBuildingsiteByBuildingsiteName(String buildingsiteName) throws DataAccessException;
	
	public List<T> getAllBuildingsiteListForWorkday(String projectid) throws DataAccessException;
	
	public List<T> getBuildingsiteListByFilterValue(String filterValue) throws DataAccessException;
	
	public List<T> getAllBuildingsiteListForPackagework(String projectid) throws DataAccessException;
	
	public List<T> getAllBuildingsiteListForFood(String projectid) throws DataAccessException;
	
	public String getBuildingsiteNameByBuildingsiteId(String buildingsiteId) throws DataAccessException;
	
	public int getBuildingsiteNumForAll(String projectid) throws DataAccessException;
	
	public int getBuildingsiteIdByBuildingsiteName(String buildingsiteName) throws DataAccessException;
}
