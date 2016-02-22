package com.entity;

import java.io.Serializable;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-19 下午05:14:14
* 版本号： v1.0
*/
public class FoodDetail implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private String buildingsiteid;
	private String buildingsitename;
	private String buildingsiteshortname;
	private String foodyear;
	private String foodmonth;
	private String foodmoney;
	private String otherinfo;
	private String createtime;
	private String updatetime;
	
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
	public String getFoodyear()
	{
		return foodyear;
	}
	public void setFoodyear(String foodyear)
	{
		this.foodyear = foodyear;
	}
	public String getFoodmonth()
	{
		return foodmonth;
	}
	public void setFoodmonth(String foodmonth)
	{
		this.foodmonth = foodmonth;
	}
	public String getFoodmoney()
	{
		return foodmoney;
	}
	public void setFoodmoney(String foodmoney)
	{
		this.foodmoney = foodmoney;
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
