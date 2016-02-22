package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.entity.PackageworkDetail;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-17 下午02:25:05
* 版本号： v1.0
* @param <T>
*/
public interface PackageworkDao<T>
{
	public List<T> getPackageworkList(String filterValue, int startIndex, int endIndex, String orderValue, String buildingsiteId) throws DataAccessException;
	
	public boolean addPackagework(T packagework) throws DataAccessException;
	
	public boolean editPackagework(T packagework) throws DataAccessException;
	
	public boolean removePackageworks(String ids, String buildingsiteId, String projectid) throws DataAccessException;
	
	public int getTotalCount(String filterValue, String buildingsiteId) throws DataAccessException;
	
	public T getPackageworkByPackageworkDetail(String builderid, String buildingsiteid) throws DataAccessException;
	
	public T getPackageworkByBuilderidAndBuildingsiteid(String buildingsiteId, String builderId) throws DataAccessException;
	
	public List<T> getPackageworkListForAll(String filterValue, int startIndex, int endIndex, String orderValue, String projectid) throws DataAccessException;
	
	public List<PackageworkDetail> getPackageworkDetailListForAll(String builderid) throws DataAccessException;
	
	public int getTotalCountForAll(String filterValue, String projectid) throws DataAccessException;
}
