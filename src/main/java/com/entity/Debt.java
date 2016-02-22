package com.entity;

import java.io.Serializable;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-17 下午03:58:56
* 版本号： v1.0
*/
public class Debt implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private int builderid;
	private int projectid;
	private String buildername;
	private String debtmoney;
	private String debtdate;
	private String otherinfo;
	private String createtime;
	private String updatetime;
	
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
	public String getBuildername()
	{
		return buildername;
	}
	public void setBuildername(String buildername)
	{
		this.buildername = buildername;
	}
	public String getDebtmoney()
	{
		return debtmoney;
	}
	public void setDebtmoney(String debtmoney)
	{
		this.debtmoney = debtmoney;
	}
	public String getDebtdate()
	{
		return debtdate;
	}
	public void setDebtdate(String debtdate)
	{
		this.debtdate = debtdate;
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
}
