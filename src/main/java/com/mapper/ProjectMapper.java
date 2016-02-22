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

import com.entity.Project;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-7-16 下午04:48:36
* 版本号： v1.0
*/
public interface ProjectMapper extends SqlMapper
{
	public List<Project> getProjectListByFilterValue(String filterValue);
	
	public Project getProjectByProjectId(String projectId);
	
	public void addProject(Project project);
	
	public List<Project> getProjectList(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("orderValue")String orderValue);
	
	public int getTotalCount(String filterValue);
	
	public void removeProjects(List<Integer> idList);
	
	public void editProject(Project project);
}
