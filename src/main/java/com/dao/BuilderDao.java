/**
* Copyright ? 2014-1-9 liuninglin
* WorkingTimeRecordSystem 上午01:02:53
* Version 1.0
* All right reserved.
*
*/

package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;


/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-1-9 上午01:02:53
 * 版本号： v1.0
 */
public interface BuilderDao<T>
{
	public List<T> getBuilderList(String filterValue, int startIndex, int endIndex, String orderValue, String showtype) throws DataAccessException;
	
	public boolean addBuilder(T builder) throws DataAccessException;
	
	public boolean editBuilder(T builder) throws DataAccessException;
	
	public boolean removeBuilders(String ids) throws DataAccessException;
	
	public boolean reStartBuilders(String ids) throws DataAccessException;
	
	public int getTotalCount(String filterValue, String showtype) throws DataAccessException;
	
	public T getBuilderByBuilderId(String builderId) throws DataAccessException;
	
	public T getBuilderByBuilderName(String builderName) throws DataAccessException;
	
	public List<T> getBuilderListByFilterValue(String filterValue) throws DataAccessException;
	
	public List<T> getBuilderListByFilterValueForPackagework(String filterValue) throws DataAccessException;
	
	public List<T> getBuilderListByFilterValueForTimework(String filterValue) throws DataAccessException;
	
	public List<T> getBuilderListByFilterValueForTimeworkForWorkdayCheckin(String filterValue, String buildingsiteid, String workyear, String workmonth);
	
	public int getBuilderNumForAll(String projectid) throws DataAccessException;
	
	public int getBuilderIdByBuilderName(String builderName) throws DataAccessException;
}
