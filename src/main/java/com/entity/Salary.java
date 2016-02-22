package com.entity;

import java.io.Serializable;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-14 下午05:03:29
* 版本号： v1.0
*/
public class Salary implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private int builderid;
	private int projectid;
	private String buildername;
	private String salarymoney;
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
	public String getSalarymoney()
	{
		return salarymoney;
	}
	public void setSalarymoney(String salarymoney)
	{
		this.salarymoney = salarymoney;
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
