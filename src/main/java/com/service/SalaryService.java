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
* 创建时间： 2014-2-16 下午02:27:41
* 版本号： v1.0
* @param <T>
*/
public interface SalaryService<T> {
	public List<T> showSalaryList(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException;
	
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String projectid) throws ServiceException;
	
	public boolean addSalary(T salary) throws ServiceException;
	
	public JSONObject getSalaryByBuilderId(String builderId, String projectid) throws ServiceException, JSONException;
	
	public boolean editSalary(T salary) throws ServiceException;
	
	public boolean removeSalaries(String salaryIds, String projectid) throws ServiceException;
}
