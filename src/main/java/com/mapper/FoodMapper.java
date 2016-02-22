package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Food;
import com.entity.FoodDetail;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-20 上午12:15:31
* 版本号： v1.0
*/
public interface FoodMapper extends SqlMapper
{
	public List<Food> getFoodList(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("orderValue")String orderValue, @Param("buildingsiteid")String buildingsiteid, @Param("projectid")String projectid);
	
	public List<FoodDetail> getFoodDetailList(@Param("buildingsiteid")String buildingsiteid, @Param("builderid")String builderid, @Param("projectid")String projectid);
	
	public void addFood(Food food);
	
	public void editFood(Food food);
	
	public void editFoodInfo(Food food);
	
	public void editFoodDetailInfo(Food food);
	
	public void removeFood(Food food);
	
	public int getTotalCount(@Param("filterValue")String filterValue, @Param("buildingsiteid")String buildingsiteid, @Param("projectid")String projectid);
	
	public Food getFoodByFoodDetail(@Param("builderid")String builderid, @Param("buildingsiteid")String buildingsiteid, @Param("foodyear")String foodyear, @Param("foodmonth")String foodmonth);
	
	public List<Food> getFoodListForAll(@Param("filterValue")String filterValue, @Param("startIndex")int startIndex, @Param("endIndex")int endIndex, @Param("orderValue")String orderValue, @Param("projectid")String projectid);
	
	public List<FoodDetail> getFoodDetailListForAll(@Param("builderid")String builderid, @Param("projectid")String projectid);
	
	public int getTotalCountForAll(@Param("filterValue")String filterValue, @Param("projectid")String projectid);
	
	public FoodDetail getFoodDetailInfo(@Param("builderid")String builderid, @Param("buildingsiteid")String buildingsiteid, @Param("foodyear")String foodyear, @Param("foodmonth")String foodmonth);
}
