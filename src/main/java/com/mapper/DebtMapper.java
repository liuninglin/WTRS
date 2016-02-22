package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Debt;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-17 下午04:18:24
* 版本号： v1.0
*/
public interface DebtMapper extends SqlMapper
{
	public List<Debt> getDebtList(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("orderValue")String orderValue, @Param("projectid")String projectid);
	
	public void addDebt(Debt debt);
	
	public void editDebt(Debt debt);
	
	public void removeDebts(@Param("idList")List<Integer> idList, @Param("projectid")String projectid);
	
	public int getTotalCount(@Param("filterValue")String filterValue, @Param("projectid")String projectid);
	
	public Debt getDebtByBuilderId(String builderId);
}
