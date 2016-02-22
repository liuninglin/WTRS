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
* 创建时间： 2014-2-17 下午04:22:55
* 版本号： v1.0
* @param <T>
*/
public interface DebtService<T> {
	public List<T> showDebtList(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException;
	
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String projectid) throws ServiceException;
	
	public boolean addDebt(T debt) throws ServiceException;
	
	public JSONObject getDebtByBuilderId(String builderId) throws ServiceException, JSONException;
	
	public boolean editDebt(T debt) throws ServiceException;
	
	public boolean removeDebts(String builderIds, String projectid) throws ServiceException;
}
