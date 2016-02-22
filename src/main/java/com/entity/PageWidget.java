/**
* Copyright ? 2014-1-13 liuninglin
* WorkingTimeRecordSystem 下午05:41:30
* Version 1.0
* All right reserved.
*
*/

package com.entity;

import java.io.Serializable;

/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-1-13 下午05:41:30
 * 版本号： v1.0
 */
public class PageWidget implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String totalPages;
	private String totalCount;
	private String currentPage;
	private String startIndex;
	private String endIndex;
	private String pageSize;
	
	public String getTotalPages()
	{
		return totalPages;
	}
	public void setTotalPages(String totalPages)
	{
		this.totalPages = totalPages;
	}
	public String getTotalCount()
	{
		return totalCount;
	}
	public void setTotalCount(String totalCount)
	{
		this.totalCount = totalCount;
	}
	public String getStartIndex()
	{
		return startIndex;
	}
	public void setStartIndex(String startIndex)
	{
		this.startIndex = startIndex;
	}
	public String getEndIndex()
	{
		return endIndex;
	}
	public void setEndIndex(String endIndex)
	{
		this.endIndex = endIndex;
	}
	public String getCurrentPage()
	{
		return currentPage;
	}
	public void setCurrentPage(String currentPage)
	{
		this.currentPage = currentPage;
	}
	public String getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(String pageSize)
	{
		this.pageSize = pageSize;
	}
}
