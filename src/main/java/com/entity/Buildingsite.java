package com.entity;

import java.io.Serializable;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-10 下午05:12:36
* 版本号： v1.0
*/
public class Buildingsite implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String shortname;
	private String starttime;
	private String endtime;
	private String address;
	private String otherinfo;
	private String createtime;
	private String updatetime;
	private String showtype;
	private String pinyin;
	
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
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
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
	public String getShowtype()
	{
		return showtype;
	}
	public void setShowtype(String showtype)
	{
		this.showtype = showtype;
	}
	public String getPinyin()
	{
		return pinyin;
	}
	public void setPinyin(String pinyin)
	{
		this.pinyin = pinyin;
	}
}
