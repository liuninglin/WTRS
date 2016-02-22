package com.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.common.exception.ServiceException;
import com.entity.PageWidget;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-21 下午04:25:49
* 版本号： v1.0
* @param <T>
*/
public interface DistributionInfoService<T> {
	public List<T> showDistributionInfoList(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException;
	
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String projectid) throws ServiceException;
	
	public HSSFWorkbook getDistributionInfoListForAll(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException;
}
