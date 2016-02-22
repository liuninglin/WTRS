package com.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.dao.DistributionInfoDao;
import com.entity.DistributionInfo;
import com.mapper.DistributionInfoMapper;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-21 下午04:24:20
* 版本号： v1.0
* @param <T>
*/
@SuppressWarnings("unchecked")
@Repository
public class DistributionInfoDaoImpl<T extends DistributionInfo> implements DistributionInfoDao<T>
{
	@Autowired
    private DistributionInfoMapper mapper;

	@Override
	public List<T> getDistributionInfoList(String filterValue, int startIndex, int endIndex, String orderValue, String projectid) throws DataAccessException
	{
		return (List<T>)mapper.getDistributionInfoList(filterValue, startIndex, endIndex, orderValue, projectid);
	}

	@Override
	public int getTotalCount(String filterValue, String projectid) throws DataAccessException
	{
		return mapper.getTotalCount(filterValue, projectid);
	}

	@Override
	public Integer getAllMoney(String projectid)
	{
		return mapper.getAllMoney(projectid);
	}
}
