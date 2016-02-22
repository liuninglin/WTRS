package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Salary;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-14 下午05:24:58
* 版本号： v1.0
*/
public interface SalaryMapper extends SqlMapper
{
	public List<Salary> getSalaryList(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("orderValue")String orderValue, @Param("projectid")String projectid);
	
	public void addSalary(Salary salary);
	
	public void editSalary(Salary salary);
	
	public void removeSalaries(List<Integer> idList, @Param("projectid")String projectid);
	
	public int getTotalCount(@Param("filterValue")String filterValue, @Param("projectid")String projectid);
	
	public Salary getSalaryByBuilderId(@Param("builderId")String builderId, @Param("projectid")String projectid);
}
