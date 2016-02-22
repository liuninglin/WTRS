package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Buildingsite;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-10 下午05:36:27
* 版本号： v1.0
*/
public interface BuildingsiteMapper extends SqlMapper
{
	public List<Buildingsite> getAllBuildingsiteList();
	
	public List<Buildingsite> getBuildingsiteList(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("orderValue")String orderValue, @Param("showtype")String showtype);
	
	public void addBuildingsite(Buildingsite buildingsite);
	
	public void editBuildingsite(Buildingsite buildingsite);
	
	public void removeBuildingsites(List<Integer> idList);
	
	public void reStartBuildingsites(List<Integer> idList);
	
	public int getTotalCount(@Param("filterValue")String filterValue, @Param("showtype")String showtype);
	
	public Buildingsite getBuildingsiteByBuildingsiteId(String buildingsiteId);
	
	public Buildingsite getBuildingsiteByBuildingsiteName(String buildingsiteName);
	
	public List<Buildingsite> getAllBuildingsiteListForWorkday(String projectid);
	
	public List<Buildingsite> getBuildingsiteListByFilterValue(String filterValue);
	
	public List<Buildingsite> getAllBuildingsiteListForPackagework(String projectid);
	
	public List<Buildingsite> getAllBuildingsiteListForFood(String projectid);
	
	public String getBuildingsiteNameByBuildingsiteId(String buildingsiteId);
	
	public int getBuildingsiteNumForAll(@Param("projectid")String projectid);
	
	public int getBuildingsiteIdByBuildingsiteName(@Param("buildingsiteName")String buildingsiteName);
}
