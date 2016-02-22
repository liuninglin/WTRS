/**
* Copyright ? 2014-1-13 liuninglin
* WorkingTimeRecordSystem 下午02:51:49
* Version 1.0
* All right reserved.
*
*/

package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Buildingsite;
import com.entity.MonthEntity;
import com.entity.Workday;
import com.entity.WorkdayDetail;

/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-1-13 下午02:51:49
 * 版本号： v1.0
 */
public interface WorkdayMapper extends SqlMapper
{
	public List<Workday> getWorkdayList(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("orderValue")String orderValue, @Param("projectid")String projectid, @Param("buildingsiteid")String buildingsiteid, @Param("workyear")String workyear, @Param("workmonth")String workmonth);
	
	public List<WorkdayDetail> getWorkdayDetailList(@Param("buildingsiteid")String buildingsiteid, @Param("builderid")String builderid, @Param("workyear")String workyear, @Param("workmonth")String workmonth);
	
	public void addWorkday(Workday workday);
	
	public void editWorkday(Workday workday);
	
	public void editWorkdayDetailInfo(Workday workday);
	
	public void editWorkdayInfo(Workday workday);
	
	public void removeWorkday(Workday workday);
	
	public int getTotalCount(@Param("filterValue")String filterValue, @Param("projectid")String projectid, @Param("buildingsiteid")String buildingsiteid, @Param("workyear")String workyear, @Param("workmonth")String workmonth);
	
	public int getTotalCountForYear(@Param("filterValue")String filterValue, @Param("buildingsiteid")String buildingsiteid, @Param("projectid")String projectid);
	
	public Workday getWorkdayByWorkdayDetail(@Param("builderid")String builderid, @Param("buildingsiteid")String buildingsiteid, @Param("workyear")String workyear, @Param("workmonth")String workmonth, @Param("workday")String workday);
	
	public List<String> getWorkyearListByBuildingsiteid(String buildingsiteid);
	
	public List<String> getWorkmonthListByBuildingsiteidAndWorkyear(@Param("buildingsiteid")String buildingsiteid, @Param("workyear")String workyear);
	
	public List<Workday> getWorkdayListForYear(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("orderValue")String orderValue, @Param("buildingsiteid")String buildingsiteid, @Param("projectid")String projectid);
	
	public List<WorkdayDetail> getWorkdayDetailListForYear(@Param("buildingsiteid")String buildingsiteid, @Param("builderid")String builderid, @Param("projectid")String projectid);
	
	public List<Workday> getWorkdayListForAll(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("orderValue")String orderValue, @Param("projectid")String projectid);
	
	public List<WorkdayDetail> getWorkdayDetailListForAll(@Param("builderid")String builderid);
	
	public int getTotalCountForAll(@Param("filterValue")String filterValue, @Param("projectid")String projectid);
	
	public WorkdayDetail getWorkdayDetailInfo(@Param("builderid")String builderid, @Param("buildingsiteid")String buildingsiteid, @Param("workyear")String workyear, @Param("workmonth")String workmonth, @Param("workday")String workday);
	
	public List<MonthEntity> getMonthListForWorkdayYear(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("buildingsiteid")String buildingsiteid, @Param("projectid")String projectid);
	
	public List<Buildingsite> getBuildingsiteListForWorkdayAll(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("projectid")String projectid);
	
	public List<String> getWorkyearListByBuildingsiteidAndFilterValue(@Param("filterValue")String filterValue, @Param("buildingsiteid")String buildingsiteid);
	
	public List<String> getWorkmonthListByBuildingsiteidAndWorkyearAndFilterValue(@Param("filterValue")String filterValue, @Param("buildingsiteid")String buildingsiteid, @Param("workyear")String workyear);
}
