package com.entity;

import java.io.Serializable;
import java.util.List;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-19 下午05:14:14
* 版本号： v1.0
*/
public class Food implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private int buildingsiteid;
	private int builderid;
	private int projectid;
	private String buildername;
	private String buildershortname;
	private String buildingsitename;
	private String buildingsiteshortname;
	private String foodyear;
	private String foodmonth;
	private String foodmoney;
	private String otherinfo;
	private String createtime;
	private String updatetime;
	
	private List<FoodDetail> foodDetailList;
	
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
	public List<FoodDetail> getFoodDetailList()
	{
		return foodDetailList;
	}
	public void setFoodDetailList(List<FoodDetail> foodDetailList)
	{
		this.foodDetailList = foodDetailList;
	}
}
