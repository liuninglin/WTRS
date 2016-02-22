package com.entity;

import java.io.Serializable;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-20 下午04:12:26
* 版本号： v1.0
*/
public class PackageworkDetail implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private String buildingsiteid;
	private String buildingsitename;
	private String packageworkmoney;
	
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
	public String getPackageworkmoney()
	{
		return packageworkmoney;
	}
	public void setPackageworkmoney(String packageworkmoney)
	{
		this.packageworkmoney = packageworkmoney;
	}
}
