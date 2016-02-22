package com.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.dao.FoodDao;
import com.entity.Food;
import com.entity.FoodDetail;
import com.mapper.FoodMapper;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-20 上午12:25:25
* 版本号： v1.0
* @param <T>
*/
@SuppressWarnings("unchecked")
@Repository
public class FoodDaoImpl<T extends Food> implements FoodDao<T>
{
	@Autowired
    private FoodMapper mapper;

	@Override
	public boolean addFood(T food) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.addFood(food);
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public boolean editFood(T food) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.editFood(food);
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public List<T> getFoodList(String filterValue, int startIndex, int endIndex, String orderValue, String buildingsiteid, String projectid) throws DataAccessException
	{
		return (List<T>)mapper.getFoodList(filterValue, startIndex, endIndex, orderValue, buildingsiteid, projectid);
	}

	@Override
	public boolean removeFood(T food) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.removeFood(food);
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public int getTotalCount(String filterValue, String buildingsiteid, String projectid) throws DataAccessException
	{
		return mapper.getTotalCount(filterValue, buildingsiteid, projectid);
	}

	@Override
	public List<FoodDetail> getFoodDetailList(String buildingsiteid, String builderid, String projectid) throws DataAccessException
	{
		return (List<FoodDetail>)mapper.getFoodDetailList(buildingsiteid, builderid, projectid);
	}

	@Override
	public T getFoodByFoodDetail(String builderid, String buildingsiteid, String foodyear, String foodmonth) throws DataAccessException
	{
		return (T)mapper.getFoodByFoodDetail(builderid, buildingsiteid, foodyear, foodmonth);
	}

	@Override
	public List<FoodDetail> getFoodDetailListForAll(String builderid, String projectid) throws DataAccessException
	{
		return (List<FoodDetail>)mapper.getFoodDetailListForAll(builderid, projectid);
	}

	@Override
	public List<T> getFoodListForAll(String filterValue, int startIndex, int endIndex, String orderValue, String projectid) throws DataAccessException
	{
		return (List<T>)mapper.getFoodListForAll(filterValue, startIndex, endIndex, orderValue, projectid);
	}

	@Override
	public int getTotalCountForAll(String filterValue, String projectid) throws DataAccessException
	{
		return mapper.getTotalCountForAll(filterValue, projectid);
	}

	@Override
	public FoodDetail getFoodDetailInfo(String builderid, String buildingsiteid, String foodyear, String foodmonth) throws DataAccessException
	{
		return mapper.getFoodDetailInfo(builderid, buildingsiteid, foodyear, foodmonth);
	}

	@Override
	public boolean editFoodDetailInfo(T food) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.editFoodDetailInfo(food);
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}

	@Override
	public boolean editFoodInfo(T food) throws DataAccessException
	{
		boolean flag = false;
		try
		{
			mapper.editFoodInfo(food);
			flag = true;
		}
		catch (DataAccessException e)
		{
			flag = false;
			throw e;
		}
		return flag;
	}
}
