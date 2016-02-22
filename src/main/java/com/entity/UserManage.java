package com.entity;

import java.io.Serializable;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:05:10
* 版本号： v1.0
*/
public class UserManage implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private int userid;
	private String username;
	private String usertype;
	private String password;
	private String otherinfo;
	private String showtype;
	private String lastlogintime;
	private String createtime;
	private String updatetime;
	
	public int getUserid()
	{
		return userid;
	}
	public void setUserid(int userid)
	{
		this.userid = userid;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUsertype()
	{
		return usertype;
	}
	public void setUsertype(String usertype)
	{
		this.usertype = usertype;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
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
	public String getLastlogintime()
	{
		return lastlogintime;
	}
	public void setLastlogintime(String lastlogintime)
	{
		this.lastlogintime = lastlogintime;
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
