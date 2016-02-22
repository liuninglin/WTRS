/**
* Copyright ? 2014-7-16 liuninglin
* WorkingTimeRecordSystem 下午03:45:58
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
 * 创建时间： 2014-7-16 下午03:45:58
 * 版本号： v1.0
 */
public class Project implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String shortname;
	private String starttime;
	private String endtime;
	private String status;
	private String otherinfo;
	private String showtype;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getShortname()
	{
		return shortname;
	}
	public void setShortname(String shortname)
	{
		this.shortname = shortname;
	}
	public String getStarttime()
	{
		return starttime;
	}
	public void setStarttime(String starttime)
	{
		this.starttime = starttime;
	}
	public String getEndtime()
	{
		return endtime;
	}
	public void setEndtime(String endtime)
	{
		this.endtime = endtime;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getOtherinfo()
	{
		return otherinfo;
	}
	public void setOtherinfo(String otherinfo)
	{
		this.otherinfo = otherinfo;
	}
	public String getShowtype()
	{
		return showtype;
	}
	public void setShowtype(String showtype)
	{
		this.showtype = showtype;
	}
}
