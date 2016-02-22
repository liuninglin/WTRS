package com.entity;

import java.io.Serializable;
import java.util.List;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-17 下午01:48:25
* 版本号： v1.0
*/
public class Packagework implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private int builderid;
	private int buildingsiteid;
	private int projectid;
	private String buildername;
	private String buildingsitename;
	private String packageworkendyear;
	private String packageworkendmonth;
	private String packageworkmoney;
	private String otherinfo;
	private String createtime;
	private String updatetime;
	
	private List<PackageworkDetail> packageworkDetailList;
	
	public int getBuilderid()
	{
		return builderid;
	}
	public void setBuilderid(int builderid)
	{
		this.builderid = builderid;
	}
	public int getBuildingsiteid()
	{
		return buildingsiteid;
	}
	public void setBuildingsiteid(int buildingsiteid)
	{
		this.buildingsiteid = buildingsiteid;
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
	public String getBuildingsitename()
	{
		return buildingsitename;
	}
	public void setBuildingsitename(String buildingsitename)
	{
		this.buildingsitename = buildingsitename;
	}
	public String getPackageworkendyear()
	{
		return packageworkendyear;
	}
	public void setPackageworkendyear(String packageworkendyear)
	{
		this.packageworkendyear = packageworkendyear;
	}
	public String getPackageworkendmonth()
	{
		return packageworkendmonth;
	}
	public void setPackageworkendmonth(String packageworkendmonth)
	{
		this.packageworkendmonth = packageworkendmonth;
	}
	public String getPackageworkmoney()
	{
		return packageworkmoney;
	}
	public void setPackageworkmoney(String packageworkmoney)
	{
		this.packageworkmoney = packageworkmoney;
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
	public List<PackageworkDetail> getPackageworkDetailList()
	{
		return packageworkDetailList;
	}
	public void setPackageworkDetailList(List<PackageworkDetail> packageworkDetailList)
	{
		this.packageworkDetailList = packageworkDetailList;
	}
}
