/**
* Copyright ? 2014-5-27 liuninglin
* WorkingTimeRecordSystem 下午04:12:10
* Version 1.0
* All right reserved.
*
*/

package com.entity;

/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-5-27 下午04:12:10
 * 版本号： v1.0
 */
public class Analyzation
{
	private static final long serialVersionUID = 1L;
	
	private String totalBuilderNum;
	private String totalBuildingsiteNum;
	private String totalWorkdayMoney;
	private String totalPackageworkMoney;
	
	public String getTotalBuilderNum()
	{
		return totalBuilderNum;
	}
	public void setTotalBuilderNum(String totalBuilderNum)
	{
		this.totalBuilderNum = totalBuilderNum;
	}
	public String getTotalBuildingsiteNum()
	{
		return totalBuildingsiteNum;
	}
	public void setTotalBuildingsiteNum(String totalBuildingsiteNum)
	{
		this.totalBuildingsiteNum = totalBuildingsiteNum;
	}
	public String getTotalWorkdayMoney()
	{
		return totalWorkdayMoney;
	}
	public void setTotalWorkdayMoney(String totalWorkdayMoney)
	{
		this.totalWorkdayMoney = totalWorkdayMoney;
	}
	public String getTotalPackageworkMoney()
	{
		return totalPackageworkMoney;
	}
	public void setTotalPackageworkMoney(String totalPackageworkMoney)
	{
		this.totalPackageworkMoney = totalPackageworkMoney;
	}
}
