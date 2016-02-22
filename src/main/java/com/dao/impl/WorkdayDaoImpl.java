package com.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.common.util.UtilPackage;
import com.dao.WorkdayDao;
import com.entity.Buildingsite;
import com.entity.MonthEntity;
import com.entity.Workday;
import com.entity.WorkdayDetail;
import com.mapper.WorkdayMapper;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-12 下午01:43:23
* 版本号： v1.0
* @param <T>
*/
@SuppressWarnings("unchecked")
@Repository
public class WorkdayDaoImpl<T extends Workday> implements WorkdayDao<T>
{
	@Autowired
    private WorkdayMapper mapper;

	@Override
	public boolean addWorkday(T workday) throws DataAccessException
	{
		workday.setWorkmonth(UtilPackage.formatMonthOrDay(Integer.valueOf(workday.getWorkmonth())));
		workday.setWorkday(UtilPackage.formatMonthOrDay(Integer.valueOf(workday.getWorkday())));
		workday.setWorkdate(workday.getWorkyear() + "-" + workday.getWorkmonth() + "-" + workday.getWorkday());
		
		boolean flag = false;
		try
		{
			workday.setWorkdate(workday.getWorkyear() + "-" + workday.getWorkmonth() + "-" + workday.getWorkday());
			workday.setBuildinfo("");
			workday.setOtherinfo("");
			
			mapper.addWorkday(workday);
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public boolean editWorkday(T workday) throws DataAccessException
	{
		workday.setWorkmonth(UtilPackage.formatMonthOrDay(Integer.valueOf(workday.getWorkmonth())));
		workday.setWorkday(UtilPackage.formatMonthOrDay(Integer.valueOf(workday.getWorkday())));
		workday.setWorkdate(workday.getWorkyear() + "-" + workday.getWorkmonth() + "-" + workday.getWorkday());
		
		boolean flag = false;
		try
		{
			mapper.editWorkday(workday);
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public List<T> getWorkdayList(String filterValue, int startIndex, int endIndex, String orderValue, String projectid, String buildingsiteid, String workyear, String workmonth) throws DataAccessException
	{
		return (List<T>)mapper.getWorkdayList(filterValue, startIndex, endIndex, orderValue, projectid, buildingsiteid, workyear, workmonth);
	}

	@Override
	public boolean removeWorkday(T workday) throws DataAccessException
	{
		workday.setWorkmonth(UtilPackage.formatMonthOrDay(Integer.valueOf(workday.getWorkmonth())));
		workday.setWorkday(UtilPackage.formatMonthOrDay(Integer.valueOf(workday.getWorkday())));
		workday.setWorkdate(workday.getWorkyear() + "-" + workday.getWorkmonth() + "-" + workday.getWorkday());
		
		boolean flag = false;
		try
		{
			mapper.removeWorkday(workday);
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public int getTotalCount(String filterValue, String proejctid, String buildingsiteid, String workyear, String workmonth) throws DataAccessException
	{
		return mapper.getTotalCount(filterValue, proejctid, buildingsiteid, workyear, workmonth);
	}

	@Override
	public T getWorkdayByWorkdayDetail(String builderid, String buildingsiteid, String workyear, String workmonth, String workday) throws DataAccessException
	{
		workmonth = UtilPackage.formatMonthOrDay(Integer.valueOf(workmonth));
		workday = UtilPackage.formatMonthOrDay(Integer.valueOf(workday));
		
		return (T)mapper.getWorkdayByWorkdayDetail(builderid, buildingsiteid, workyear, workmonth, workday);
	}

	@Override
	public List<String> getWorkyearListByBuildingsiteid(String buildingsiteid) throws DataAccessException
	{
		return mapper.getWorkyearListByBuildingsiteid(buildingsiteid);
	}

	@Override
	public List<String> getWorkmonthListByBuildingsiteidAndWorkyear(String buildingsiteid, String workyear) throws DataAccessException
	{
		return mapper.getWorkmonthListByBuildingsiteidAndWorkyear(buildingsiteid,workyear);
	}

	@Override
	public List<WorkdayDetail> getWorkdayDetailList(String buildingsiteid, String builderid, String workyear, String workmonth) throws DataAccessException
	{
		return (List<WorkdayDetail>)mapper.getWorkdayDetailList(buildingsiteid, builderid, workyear, workmonth);
	}

	@Override
	public List<WorkdayDetail> getWorkdayDetailListForYear(String buildingsiteid, String builderid, String projectid) throws DataAccessException
	{
		return (List<WorkdayDetail>)mapper.getWorkdayDetailListForYear(buildingsiteid, builderid, projectid);
	}

	@Override
	public List<T> getWorkdayListForYear(String filterValue, int startIndex, int endIndex, String orderValue, String buildingsiteid, String projectid) throws DataAccessException
	{
		return (List<T>)mapper.getWorkdayListForYear(filterValue, startIndex, endIndex, orderValue, buildingsiteid, projectid);
	}

	@Override
	public int getTotalCountForYear(String filterValue, String buildingsiteid, String projectid) throws DataAccessException
	{
		return mapper.getTotalCountForYear(filterValue, buildingsiteid, projectid);
	}

	@Override
	public int getTotalCountForAll(String filterValue, String projectid) throws DataAccessException
	{
		return mapper.getTotalCountForAll(filterValue, projectid);
	}

	@Override
	public List<WorkdayDetail> getWorkdayDetailListForAll(String builderid) throws DataAccessException
	{
		return (List<WorkdayDetail>)mapper.getWorkdayDetailListForAll(builderid);
	}

	@Override
	public List<T> getWorkdayListForAll(String filterValue, int startIndex, int endIndex, String orderValue, String projectid) throws DataAccessException
	{
		return (List<T>)mapper.getWorkdayListForAll(filterValue, startIndex, endIndex, orderValue, projectid);
	}

	@Override
	public boolean editWorkdayInfo(T workday) throws DataAccessException
	{
		workday.setWorkmonth(UtilPackage.formatMonthOrDay(Integer.valueOf(workday.getWorkmonth())));
		workday.setWorkday(UtilPackage.formatMonthOrDay(Integer.valueOf(workday.getWorkday())));
		workday.setWorkdate(workday.getWorkyear() + "-" + workday.getWorkmonth() + "-" + workday.getWorkday());
		
		boolean flag = false;
		try
		{
			mapper.editWorkdayInfo(workday);
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public WorkdayDetail getWorkdayDetailInfo(String builderid, String buildingsiteid, String workyear, String workmonth, String workday) throws DataAccessException
	{
		return mapper.getWorkdayDetailInfo(builderid, buildingsiteid, workyear, workmonth, workday);
	}

	@Override
	public boolean editWorkdayDetailInfo(T workday) throws DataAccessException
	{
		workday.setWorkmonth(UtilPackage.formatMonthOrDay(Integer.valueOf(workday.getWorkmonth())));
		workday.setWorkday(UtilPackage.formatMonthOrDay(Integer.valueOf(workday.getWorkday())));
		workday.setWorkdate(workday.getWorkyear() + "-" + workday.getWorkmonth() + "-" + workday.getWorkday());
		
		boolean flag = false;
		try
		{
			mapper.editWorkdayDetailInfo(workday);
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public List<MonthEntity> getMonthListForWorkdayYear(String filterValue, int startIndex, int endIndex, String buildingsiteid, String projectid) throws DataAccessException
	{
		return mapper.getMonthListForWorkdayYear(filterValue, startIndex, endIndex, buildingsiteid, projectid);
	}

	@Override
	public List<Buildingsite> getBuildingsiteListForWorkdayAll(String filterValue, int startIndex, int endIndex, String projectid) throws DataAccessException
	{
		return mapper.getBuildingsiteListForWorkdayAll(filterValue, startIndex, endIndex, projectid);
	}

	@Override
	public List<String> getWorkmonthListByBuildingsiteidAndWorkyearAndFilterValue(String filterValue, String buildingsiteid, String workyear) throws DataAccessException
	{
		return mapper.getWorkmonthListByBuildingsiteidAndWorkyearAndFilterValue(filterValue, buildingsiteid, workyear);
	}

	@Override
	public List<String> getWorkyearListByBuildingsiteidAndFilterValue(String filterValue, String buildingsiteid) throws DataAccessException
	{
		return mapper.getWorkyearListByBuildingsiteidAndFilterValue(filterValue, buildingsiteid);
	}
}
