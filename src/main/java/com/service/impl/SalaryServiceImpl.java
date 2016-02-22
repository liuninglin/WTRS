package com.service.impl;

import java.util.List;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.exception.ServiceException;
import com.dao.SalaryDao;
import com.entity.PageWidget;
import com.entity.Salary;
import com.service.SalaryService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-16 下午02:27:59
* 版本号： v1.0
* @param <T>
*/
@Service("salaryService")
public class SalaryServiceImpl<T extends Salary> implements SalaryService<T> {

	@Autowired
	private SalaryDao<T> salaryDao;

	@Override
	public List<T> showSalaryList(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException
	{
		int startIndex = (Integer.valueOf(currentPage) - 1)* Integer.valueOf(pageSize);
		int endIndex = (Integer.valueOf(currentPage))* Integer.valueOf(pageSize);
		
		filterValue = filterCharacter(filterValue);
		
		if("".equals(orderValue))
		{
			orderValue = "TIMESTAMP(s.updatetime) desc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		return salaryDao.getSalaryList(filterValue, startIndex, endIndex, orderValue, projectid);
	}

	@Override
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String projectid) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		int totalCount = salaryDao.getTotalCount(filterValue, projectid);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		int startIndex = 0;
		int endIndex = 0;
		
		if(totalPages == Integer.valueOf(currentPage))
		{
			startIndex = Integer.valueOf(pageSize) * (totalPages - 1);
			endIndex = (totalPages % Integer.valueOf(currentPage)) + startIndex;
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
	public boolean addSalary(T salary) throws ServiceException
	{
		return salaryDao.addSalary(salary);
	}

	@Override
	public JSONObject getSalaryByBuilderId(String builderId, String projectid) throws ServiceException, JSONException
	{
		Salary salary = salaryDao.getSalaryByBuilderId(builderId, projectid);
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("builderid", salary.getBuilderid());
		jsonObject.append("buildername", salary.getBuildername());
		jsonObject.append("salarymoney", salary.getSalarymoney());
		jsonObject.append("otherinfo", salary.getOtherinfo());
		
		return jsonObject;
	}

	@Override
	public boolean editSalary(T salary) throws ServiceException
	{
		return salaryDao.editSalary(salary);
	}

	@Override
	public boolean removeSalaries(String ids, String projectid) throws ServiceException
	{
		return salaryDao.removeSalaries(ids, projectid);
	}
}
