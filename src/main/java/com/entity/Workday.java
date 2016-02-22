/**
* Copyright ? 2014-2-11 liuninglin
* WorkingTimeRecordSystem 下午05:12:41
* Version 1.0
* All right reserved.
*
*/

package com.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-2-11 下午05:12:41
 * 版本号： v1.0
 */
public class Workday implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int buildingsiteid;
	private int builderid;
	private int projectid;
	private String buildinfo;
	private String workyear;
	private String workmonth;
	
	private List<WorkdayDetail> workdayDetailList;
	
	private String workday;
	private String workdate;
	private String workcount;
	private String otherinfo;
	private String createtime;
	private String updatetime;
	
	private String buildername;
	private String buildershortname;
	private String builderpinyin;
	private String buildingsitename;
	private String buildingsiteshortname;
	private String buildingsitepinyin;
	
	private String totalworkday;
	private String salarymoney;
	private String totalmoney;
	
	public int getBuildingsiteid()
	{
		return buildingsiteid;
	}
	public void setBuildingsiteid(int buildingsiteid)
	{
		this.buildingsiteid = buildingsiteid;
	}
	public int getBuilderid()
	{
		return builderid;
	}
	public void setBuilderid(int builderid)
	{
		this.builderid = builderid;
	}
	public int getProjectid()
	{
		return projectid;
	}
	public void setProjectid(int projectid)
	{
		this.projectid = projectid;
	}
	public String getBuildinfo()
	{
		return buildinfo;
	}
	public void setBuildinfo(String buildinfo)
	{
		this.buildinfo = buildinfo;
	}
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
	public List<WorkdayDetail> getWorkdayDetailList()
	{
		return workdayDetailList;
	}
	public void setWorkdayDetailList(List<WorkdayDetail> workdayDetailList)
	{
		this.workdayDetailList = workdayDetailList;
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
	public String getBuildername()
	{
		return buildername;
	}
	public void setBuildername(String buildername)
	{
		this.buildername = buildername;
	}
	public String getBuildershortname()
	{
		return buildershortname;
	}
	public void setBuildershortname(String buildershortname)
	{
		this.buildershortname = buildershortname;
	}
	public String getBuilderpinyin()
	{
		return builderpinyin;
	}
	public void setBuilderpinyin(String builderpinyin)
	{
		this.builderpinyin = builderpinyin;
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
	public String getBuildingsitepinyin()
	{
		return buildingsitepinyin;
	}
	public void setBuildingsitepinyin(String buildingsitepinyin)
	{
		this.buildingsitepinyin = buildingsitepinyin;
	}
	public String getTotalworkday()
	{
		return totalworkday;
	}
	public void setTotalworkday(String totalworkday)
	{
		this.totalworkday = totalworkday;
	}
	public String getSalarymoney()
	{
		return salarymoney;
	}
	public void setSalarymoney(String salarymoney)
	{
		this.salarymoney = salarymoney;
	}
	public String getTotalmoney()
	{
		return totalmoney;
	}
	public void setTotalmoney(String totalmoney)
	{
		this.totalmoney = totalmoney;
	}
}
