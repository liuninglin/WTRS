/**
* Copyright ? 2014-2-21 liuninglin
* WorkingTimeRecordSystem 下午03:59:57
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
 * 创建时间： 2014-2-21 下午03:59:57
 * 版本号： v1.0
 */
public class DistributionInfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String builderid;
	private String buildername;
	private String buildershortname;
	private String timeworktotalmoney;
	private String packageworktotalmoney;
	private String debttotalmoney;
	private String foodtotalmoney;
	
	public String getBuilderid()
	{
		return builderid;
	}
	public void setBuilderid(String builderid)
	{
		this.builderid = builderid;
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
	public String getTimeworktotalmoney()
	{
		return timeworktotalmoney;
	}
	public void setTimeworktotalmoney(String timeworktotalmoney)
	{
		this.timeworktotalmoney = timeworktotalmoney;
	}
	public String getPackageworktotalmoney()
	{
		return packageworktotalmoney;
	}
	public void setPackageworktotalmoney(String packageworktotalmoney)
	{
		this.packageworktotalmoney = packageworktotalmoney;
	}
	public String getDebttotalmoney()
	{
		return debttotalmoney;
	}
	public void setDebttotalmoney(String debttotalmoney)
	{
		this.debttotalmoney = debttotalmoney;
	}
	public String getFoodtotalmoney()
	{
		return foodtotalmoney;
	}
	public void setFoodtotalmoney(String foodtotalmoney)
	{
		this.foodtotalmoney = foodtotalmoney;
	}
}
