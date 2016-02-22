package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.entity.FoodDetail;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-20 上午12:21:02
* 版本号： v1.0
* @param <T>
*/
public interface FoodDao<T>
{
	public List<T> getFoodList(String filterValue, int startIndex, int endIndex, String orderValue, String buildingsiteid, String projectid) throws DataAccessException;
	
	public List<FoodDetail> getFoodDetailList(String buildingsiteid, String builderid, String projectid) throws DataAccessException;
	
	public boolean addFood(T food) throws DataAccessException;
	
	public boolean editFood(T food) throws DataAccessException;
	
	public boolean editFoodInfo(T food) throws DataAccessException;
	
	public boolean editFoodDetailInfo(T food) throws DataAccessException;
	
	public boolean removeFood(T food) throws DataAccessException;
	
	public int getTotalCount(String filterValue, String buildingsiteid, String projectid) throws DataAccessException;
	
	public T getFoodByFoodDetail(String builderid, String buildingsiteid, String foodyear, String foodmonth) throws DataAccessException;
	
	public List<T> getFoodListForAll(String filterValue, int startIndex, int endIndex, String orderValue, String projectid) throws DataAccessException;
	
	public List<FoodDetail> getFoodDetailListForAll(String builderid, String projectid) throws DataAccessException;
	
	public int getTotalCountForAll(String filterValue, String projectid) throws DataAccessException;
	
	public FoodDetail getFoodDetailInfo(String builderid, String buildingsiteid, String foodyear, String foodmonth) throws DataAccessException;
}
