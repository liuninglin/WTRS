package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.json.JSONArray;
import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.exception.ServiceException;
import com.dao.BuildingsiteDao;
import com.entity.Buildingsite;
import com.entity.PageWidget;
import com.service.BuildingsiteService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-10 下午05:45:33
* 版本号： v1.0
* @param <T>
*/
@Service("buildingsiteService")
public class BuildingsiteServiceImpl<T extends Buildingsite> implements BuildingsiteService<T> {

	@Autowired
	private BuildingsiteDao<T> buildingsiteDao;

	@Override
	public List<T> showBuildingsiteList(String filterValue, String pageSize, String currentPage, String orderValue, String showtype) throws ServiceException
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
			orderValue = "TIMESTAMP(updatetime) desc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		return buildingsiteDao.getBuildingsiteList(filterValue, startIndex, endIndex, orderValue, showtype);
	}

	@Override
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String showtype) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		int totalCount = buildingsiteDao.getTotalCount(filterValue, showtype);
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
	public boolean addBuildingsite(T buildingsite) throws ServiceException
	{
		return buildingsiteDao.addBuildingsite(buildingsite);
	}

	@Override
	public JSONObject getBuildingsiteByBuildingsiteId(String buildingsiteId) throws ServiceException, JSONException
	{
		Buildingsite buildingsite = buildingsiteDao.getBuildingsiteByBuildingsiteId(buildingsiteId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("id", buildingsite.getId());
		jsonObject.append("name", buildingsite.getName());
		jsonObject.append("shortname", buildingsite.getShortname());
		jsonObject.append("starttime", buildingsite.getStarttime());
		jsonObject.append("endtime", buildingsite.getEndtime());
		jsonObject.append("address", buildingsite.getAddress());
		jsonObject.append("otherinfo", buildingsite.getOtherinfo());
		jsonObject.append("showtype", buildingsite.getShowtype());
		
		return jsonObject;
	}

	@Override
	public boolean editBuildingsite(T buildingsite) throws ServiceException
	{
		return buildingsiteDao.editBuildingsite(buildingsite);
	}

	@Override
	public boolean reStartBuildingsites(String buildingsiteIds) throws ServiceException
	{
		return buildingsiteDao.reStartBuildingsites(buildingsiteIds);
	}
	
	@Override
	public boolean removeBuildingsites(String buildingsiteIds) throws ServiceException
	{
		return buildingsiteDao.removeBuildingsites(buildingsiteIds);
	}

	@Override
	public JSONObject getAllBuildingsitesForWorkday(String projectid) throws ServiceException, JSONException
	{
		List<T> buildingsiteList = buildingsiteDao.getAllBuildingsiteListForWorkday(projectid);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		for(Buildingsite buildingsite : buildingsiteList)
		{
			jsonarray.add(buildingsite);
		}
		jsonObject.append("buildingsiteArray", jsonarray);
		return jsonObject;
	}

	@Override
	public JSONObject getBuildingsiteListByFilterValue(String filterValue) throws ServiceException, JSONException
	{
		filterValue = filterCharacter(filterValue);
		List<T> buildingsiteList = buildingsiteDao.getBuildingsiteListByFilterValue(filterValue);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		if(buildingsiteList.size() != 0)
		{
			for(Buildingsite buildingsite : buildingsiteList)
			{
				jsonarray.add(buildingsite);
			}
		}
		else
		{
			Buildingsite buildingsite = new Buildingsite();
			buildingsite.setId(-1);
			buildingsite.setName("-1");
			jsonarray.add(buildingsite);
		}
		jsonObject.append("buildingsiteArray", jsonarray);
		return jsonObject;
	}

	@Override
	public JSONObject getAllBuildingsitesForPackagework(String projectid) throws ServiceException, JSONException
	{
		List<T> buildingsiteList = buildingsiteDao.getAllBuildingsiteListForPackagework(projectid);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		for(Buildingsite buildingsite : buildingsiteList)
		{
			jsonarray.add(buildingsite);
		}
		jsonObject.append("buildingsiteArray", jsonarray);
		return jsonObject;
	}

	@Override
	public JSONObject getAllBuildingsitesForFood(String projectid) throws ServiceException, JSONException
	{
		List<T> buildingsiteList = buildingsiteDao.getAllBuildingsiteListForFood(projectid);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		for(Buildingsite buildingsite : buildingsiteList)
		{
			jsonarray.add(buildingsite);
		}
		jsonObject.append("buildingsiteArray", jsonarray);
		return jsonObject;
	}

	@Override
	public String getBuildingsiteNameByBuildingsiteId(String buildingsiteId)
	{
		return buildingsiteDao.getBuildingsiteNameByBuildingsiteId(buildingsiteId);
	}

	@Override
	public int getBuildingsiteIdByBuildingsiteName(String buildingsiteName)
	{
		return buildingsiteDao.getBuildingsiteIdByBuildingsiteName(buildingsiteName);
	}
}
