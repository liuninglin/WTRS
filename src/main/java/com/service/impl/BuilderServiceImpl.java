package com.service.impl;

import java.util.List;

import org.apache.commons.json.JSONArray;
import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.exception.ServiceException;
import com.dao.BuilderDao;
import com.entity.Builder;
import com.entity.Buildingsite;
import com.entity.PageWidget;
import com.service.BuilderService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:04:40
* 版本号： v1.0
*/
@Service("builderService")
public class BuilderServiceImpl<T extends Builder> implements BuilderService<T> {

	@Autowired
	private BuilderDao<T> builderDao;

	@Override
	public List<T> showBuilderList(String filterValue, String pageSize, String currentPage, String orderValue, String showtype) throws ServiceException
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
		
		return builderDao.getBuilderList(filterValue, startIndex, endIndex, orderValue, showtype);
	}

	@Override
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String showtype) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		int totalCount = builderDao.getTotalCount(filterValue, showtype);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		if(Integer.valueOf(pageSize) == -1)
		{
			totalPages = 1;
		}
		
		int startIndex = 0;
		int endIndex = 0;
		
		if(totalPages == Integer.valueOf(currentPage) && Integer.valueOf(currentPage) != 0)
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
	public boolean addBuilder(T builder) throws ServiceException
	{
		return builderDao.addBuilder(builder);
	}

	@Override
	public JSONObject getBuilderByBuilderId(String builderId) throws ServiceException, JSONException
	{
		Builder builder = builderDao.getBuilderByBuilderId(builderId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("id", builder.getId());
		jsonObject.append("name", builder.getName());
		jsonObject.append("shortname", builder.getShortname());
		jsonObject.append("type", builder.getType());
		jsonObject.append("age", builder.getAge());
		jsonObject.append("sex", builder.getSex());
		jsonObject.append("idcard", builder.getIdcard());
		jsonObject.append("hometown", builder.getHometown());
		jsonObject.append("otherinfo", builder.getOtherinfo());
		jsonObject.append("showtype", builder.getShowtype());
		
		return jsonObject;
	}

	@Override
	public boolean editBuilder(T builder) throws ServiceException
	{
		return builderDao.editBuilder(builder);
	}

	@Override
	public boolean removeBuilders(String builderIds) throws ServiceException
	{
		return builderDao.removeBuilders(builderIds);
	}
	
	@Override
	public boolean reStartBuilders(String builderIds) throws ServiceException
	{
		return builderDao.reStartBuilders(builderIds);
	}

	@Override
	public JSONObject showBuilderListByFilterValue(String filterValue) throws ServiceException, JSONException
	{
		filterValue = filterCharacter(filterValue);
		List<T> builderList = builderDao.getBuilderListByFilterValue(filterValue);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(Builder builder : builderList)
		{
			JSONObject jsonObjectTemp = new JSONObject();
			jsonObjectTemp.append("id", builder.getId());
			jsonObjectTemp.append("name", builder.getName());
			jsonObjectTemp.append("shortname", builder.getShortname());
			jsonObjectTemp.append("type", builder.getType());
			jsonArray.add(jsonObjectTemp);
		}
		jsonObject.append("builderArray", jsonArray);
		return jsonObject;
	}

	@Override
	public JSONObject showBuilderListByFilterValueForPackagework(String filterValue) throws ServiceException, JSONException
	{
		filterValue = filterCharacter(filterValue);
		List<T> builderList = builderDao.getBuilderListByFilterValueForPackagework(filterValue);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(Builder builder : builderList)
		{
			JSONObject jsonObjectTemp = new JSONObject();
			jsonObjectTemp.append("id", builder.getId());
			jsonObjectTemp.append("name", builder.getName());
			jsonObjectTemp.append("shortname", builder.getShortname());
			jsonObjectTemp.append("type", builder.getType());
			jsonArray.add(jsonObjectTemp);
		}
		jsonObject.append("builderArray", jsonArray);
		return jsonObject;
	}

	@Override
	public JSONObject showBuilderListByFilterValueForTimework(String filterValue)
			throws ServiceException, JSONException {
		filterValue = filterCharacter(filterValue);
		List<T> builderList = builderDao.getBuilderListByFilterValueForTimework(filterValue);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if(builderList.size() != 0)
		{
			for(Builder builder : builderList)
			{
				jsonArray.add(builder);
			}
		}
		else
		{
			Builder builder = new Builder();
			builder.setId(-1);
			builder.setName("-1");
			jsonArray.add(builder);
		}
		jsonObject.append("builderArray", jsonArray);
		return jsonObject;
	}

	@Override
	public int getBuilderIdByBuilderName(String builderName)
	{
		return builderDao.getBuilderIdByBuilderName(builderName);
	}

	@Override
	public JSONObject showBuilderListByFilterValueForTimeworkForWorkdayCheckin(String filterValue, String buildingsiteid, String workyear, String workmonth)
			throws ServiceException, JSONException
	{
		filterValue = filterCharacter(filterValue);
		List<T> builderList = builderDao.getBuilderListByFilterValueForTimeworkForWorkdayCheckin(filterValue, buildingsiteid, workyear, workmonth);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if(builderList.size() != 0)
		{
			for(Builder builder : builderList)
			{
				jsonArray.add(builder);
			}
		}
		else
		{
			Builder builder = new Builder();
			builder.setId(-1);
			builder.setName("-1");
			jsonArray.add(builder);
		}
		jsonObject.append("builderArray", jsonArray);
		return jsonObject;
	}
}
