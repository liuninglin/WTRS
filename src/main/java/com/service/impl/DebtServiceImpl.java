package com.service.impl;

import java.util.List;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.exception.ServiceException;
import com.dao.DebtDao;
import com.entity.Debt;
import com.entity.PageWidget;
import com.service.DebtService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-17 下午04:23:47
* 版本号： v1.0
* @param <T>
*/
@Service("debtService")
public class DebtServiceImpl<T extends Debt> implements DebtService<T> {

	@Autowired
	private DebtDao<T> debtDao;

	@Override
	public List<T> showDebtList(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException
	{
		int startIndex = (Integer.valueOf(currentPage) - 1)* Integer.valueOf(pageSize);
		int endIndex = (Integer.valueOf(currentPage))* Integer.valueOf(pageSize);
		
		filterValue = filterCharacter(filterValue);
		
		if("".equals(orderValue))
		{
			orderValue = "TIMESTAMP(d.updatetime) desc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		return debtDao.getDebtList(filterValue, startIndex, endIndex, orderValue, projectid);
	}

	@Override
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String projectid) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		int totalCount = debtDao.getTotalCount(filterValue, projectid);
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
	public boolean addDebt(T debt) throws ServiceException
	{
		return debtDao.addDebt(debt);
	}

	@Override
	public JSONObject getDebtByBuilderId(String builderId) throws ServiceException, JSONException
	{
		Debt debt = debtDao.getDebtByBuilderId(builderId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("builderid", debt.getBuilderid());
		jsonObject.append("buildername", debt.getBuildername());
		jsonObject.append("debtmoney", debt.getDebtmoney());
		jsonObject.append("debtdate", debt.getDebtdate());
		jsonObject.append("otherinfo", debt.getOtherinfo());
		
		return jsonObject;
	}

	@Override
	public boolean editDebt(T debt) throws ServiceException
	{
		return debtDao.editDebt(debt);
	}

	@Override
	public boolean removeDebts(String builderIds, String projectid) throws ServiceException
	{
		return debtDao.removeDebts(builderIds, projectid);
	}
}
