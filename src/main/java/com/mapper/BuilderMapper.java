/**
* Copyright ? 2014-1-13 liuninglin
* WorkingTimeRecordSystem 下午02:51:49
* Version 1.0
* All right reserved.
*
*/

package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Builder;

/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-1-13 下午02:51:49
 * 版本号： v1.0
 */
public interface BuilderMapper extends SqlMapper
{
	public List<Builder> getBuilderList(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("orderValue")String orderValue, @Param("showtype")String showtype);
	
	public void addBuilder(Builder builder);
	
	public void editBuilder(Builder builder);
	
	public void removeBuilders(List<Integer> idList);
	
	public void reStartBuilders(List<Integer> idList);
	
	public int getTotalCount(@Param("filterValue")String filterValue, @Param("showtype")String showtype);
	
	public Builder getBuilderByBuilderId(String builderId);
	
	public Builder getBuilderByBuilderName(String builderName);
	
	public List<Builder> getBuilderListByFilterValue(String filterValue);
	
	public List<Builder> getBuilderListByFilterValueForTimework(String filterValue);
	
	public List<Builder> getBuilderListByFilterValueForTimeworkForWorkdayCheckin(@Param("filterValue")String filterValue, @Param("buildingsiteid")String buildingsiteid, @Param("workyear")String workyear,  @Param("workmonth")String workmonth);
	
	public List<Builder> getBuilderListByFilterValueForPackagework(String filterValue);
	
	public int getBuilderNumForAll(@Param("projectid")String projectid);
	
	public int getBuilderIdByBuilderName(@Param("builderName")String builderName);
	
}
