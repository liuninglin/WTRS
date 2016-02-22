package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.DistributionInfo;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-21 下午04:22:27
* 版本号： v1.0
*/
public interface DistributionInfoMapper extends SqlMapper
{
	public List<DistributionInfo> getDistributionInfoList(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("orderValue")String orderValue, @Param("projectid")String projectid);
	
	public int getTotalCount(@Param("filterValue")String filterValue, @Param("projectid")String projectid);
	
	public Integer getAllMoney(@Param("projectid")String projectid);
}
