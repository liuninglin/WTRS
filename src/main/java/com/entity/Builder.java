/**
* Copyright ? 2014-1-13 liuninglin
* WorkingTimeRecordSystem 下午02:10:55
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
 * 创建时间： 2014-1-13 下午02:10:55
 * 版本号： v1.0
 */
public class Builder implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private int id;
	private String type;//timework点工   packagework包工
	private String name;
	private String shortname;
	private String age;
	private String sex;
	private String idcard;
	private String hometown;
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
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
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
	public String getAge()
	{
		return age;
	}
	public void setAge(String age)
	{
		this.age = age;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getIdcard()
	{
		return idcard;
	}
	public void setIdcard(String idcard)
	{
		this.idcard = idcard;
	}
	public String getHometown()
	{
		return hometown;
	}
	public void setHometown(String hometown)
	{
		this.hometown = hometown;
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
