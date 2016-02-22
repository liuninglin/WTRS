package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.entity.Buildingsite;
import com.entity.MonthEntity;
import com.entity.WorkdayDetail;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-12 下午01:16:11
* 版本号： v1.0
* @param <T>
*/
public interface WorkdayDao<T>
{
	public List<T> getWorkdayList(String filterValue, int startIndex, int endIndex, String orderValue, String projectid, String buildingsiteid, String workyear, String workmonth) throws DataAccessException;
	
	public List<WorkdayDetail> getWorkdayDetailList(String buildingsiteid, String builderid, String workyear, String workmonth) throws DataAccessException;
	
	public boolean addWorkday(T workday) throws DataAccessException;
	
	public boolean editWorkday(T workday) throws DataAccessException;
	
	public boolean editWorkdayDetailInfo(T workday) throws DataAccessException;
	
	public boolean editWorkdayInfo(T workday) throws DataAccessException;
	
	public boolean removeWorkday(T workday) throws DataAccessException;
	
	public int getTotalCount(String filterValue, String projectid, String buildingsiteid, String workyear, String workmonth) throws DataAccessException;
	
	public int getTotalCountForYear(String filterValue, String buildingsiteid, String projectid) throws DataAccessException;
	
	public T getWorkdayByWorkdayDetail(String builderid, String buildingsiteid, String workyear, String workmonth, String workday) throws DataAccessException;
	
	public List<String> getWorkyearListByBuildingsiteid(String buildingsiteid) throws DataAccessException;
	
	public List<String> getWorkmonthListByBuildingsiteidAndWorkyear(String buildingsiteid, String workyear) throws DataAccessException;
	
	public List<T> getWorkdayListForYear(String filterValue, int startIndex, int endIndex, String orderValue, String buildingsiteid, String projectid) throws DataAccessException;
	
	public List<WorkdayDetail> getWorkdayDetailListForYear(String buildingsiteid, String builderid, String projectid) throws DataAccessException;
	
	public List<T> getWorkdayListForAll(String filterValue, int startIndex, int endIndex, String orderValue, String projectid) throws DataAccessException;
	
	public List<WorkdayDetail> getWorkdayDetailListForAll(String builderid) throws DataAccessException;
	
	public int getTotalCountForAll(String filterValue, String projectid) throws DataAccessException;
	
	public WorkdayDetail getWorkdayDetailInfo(String builderid, String buildingsiteid, String workyear, String workmonth, String workday) throws DataAccessException;
	
	public List<MonthEntity> getMonthListForWorkdayYear(String filterValue, int startIndex, int endIndex, String buildingsiteid, String projectid) throws DataAccessException;
	
	public List<Buildingsite> getBuildingsiteListForWorkdayAll(String filterValue, int startIndex, int endIndex, String projectid) throws DataAccessException;
	
	public List<String> getWorkyearListByBuildingsiteidAndFilterValue(String filterValue, String buildingsiteid) throws DataAccessException;
	
	public List<String> getWorkmonthListByBuildingsiteidAndWorkyearAndFilterValue(String filterValue, String buildingsiteid, String workyear) throws DataAccessException;
}
