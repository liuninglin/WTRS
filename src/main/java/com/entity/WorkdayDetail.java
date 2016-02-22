/**
* Copyright ? 2014-2-13 liuninglin
* WorkingTimeRecordSystem 上午12:35:36
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
 * 创建时间： 2014-2-13 上午12:35:36
 * 版本号： v1.0
 */
public class WorkdayDetail implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String workyear;
	private String workmonth;
	private String workday;
	private String workdate;
	private String workcount;
	private String buildinfo;
	private String otherinfo;
	private String createtime;
	private String updatetime;
	
	private String buildingsiteid;
	private String buildingsitename;
	private String buildingsiteshortname;
	
	public String getWorkyear()
	{
		return workyear;
	}
	public void setWorkyear(String workyear)
	{
		this.workyear = workyear;
	}
	public String getWorkmonth()
	{
		return workmonth;
	}
	public void setWorkmonth(String workmonth)
	{
		this.workmonth = workmonth;
	}
	public String getWorkday()
	{
		return workday;
	}
	public void setWorkday(String workday)
	{
		this.workday = workday;
	}
	public String getWorkdate()
	{
		return workdate;
	}
	public void setWorkdate(String workdate)
	{
		this.workdate = workdate;
	}
	public String getWorkcount()
	{
		return workcount;
	}
	public void setWorkcount(String workcount)
	{
		this.workcount = workcount;
	}
	public String getBuildinfo()
	{
		return buildinfo;
	}
	public void setBuildinfo(String buildinfo)
	{
		this.buildinfo = buildinfo;
	}
	public String getOtherinfo()
	{
		return otherinfo;
	}
	public void setOtherinfo(String otherinfo)
	{
		this.otherinfo = otherinfo;
	}
	public String getCreatetime()
	{
		return createtime;
	}
	public void setCreatetime(String createtime)
	{
		this.createtime = createtime;
	}
	public String getUpdatetime()
	{
		return updatetime;
	}
	public void setUpdatetime(String updatetime)
	{
		this.updatetime = updatetime;
	}
	public String getBuildingsiteid()
	{
		return buildingsiteid;
	}
	public void setBuildingsiteid(String buildingsiteid)
	{
		this.buildingsiteid = buildingsiteid;
	}
	public String getBuildingsitename()
	{
		return buildingsitename;
	}
	public void setBuildingsitename(String buildingsitename)
	{
		this.buildingsitename = buildingsitename;
	}
	public String getBuildingsiteshortname()
	{
		return buildingsiteshortname;
	}
	public void setBuildingsiteshortname(String buildingsiteshortname)
	{
		this.buildingsiteshortname = buildingsiteshortname;
	}
}
