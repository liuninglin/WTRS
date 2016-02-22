package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Packagework;
import com.entity.PackageworkDetail;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-17 下午02:22:16
* 版本号： v1.0
*/
public interface PackageworkMapper extends SqlMapper
{
	public List<Packagework> getPackageworkList(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("orderValue")String orderValue, @Param("buildingsiteid")String buildingsiteid);
	
	public void addPackagework(Packagework packagework);
	
	public void editPackagework(Packagework packagework);
	
	public void removePackageworks(@Param("idList")List<Integer> idList, @Param("buildingsiteid")String buildingsiteid, @Param("projectid")String projectid);
	
	public int getTotalCount(@Param("filterValue")String filterValue, @Param("buildingsiteid")String buildingsiteid);
	
	public Packagework getPackageworkByBuilderidAndBuildingsiteid(@Param("buildingsiteid")String buildingsiteid, @Param("builderid")String builderid);
	
	public Packagework getPackageworkByPackageworkDetail(@Param("builderid")String builderid, @Param("buildingsiteid")String buildingsiteid);
	
	public List<Packagework> getPackageworkListForAll(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("orderValue")String orderValue, @Param("projectid")String projectid);
	
	public List<PackageworkDetail> getPackageworkDetailListForAll(String builderid);
	
	public int getTotalCountForAll(@Param("filterValue")String filterValue, @Param("projectid")String projectid);
}
