package com.service.impl;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.exception.ServiceException;
import com.common.util.UtilPackage;
import com.dao.BuilderDao;
import com.dao.BuildingsiteDao;
import com.dao.DistributionInfoDao;
import com.service.OtherService;

/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-6-18 下午03:24:58
 * 版本号： v1.0
 */
@Service("otherService")
public class OtherServiceImpl implements OtherService {

	@Autowired
	private BuilderDao<T> builderDao;
	
	@Autowired
	private BuildingsiteDao<T> buildingsiteDao;
	
	@Autowired
	private DistributionInfoDao<T> distributioninfoDao;

	@Override
	public JSONObject getAnalyseInfo(String projectid) throws ServiceException, JSONException
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("buildernum", builderDao.getBuilderNumForAll(projectid));
		jsonObject.put("buildingsitenum", buildingsiteDao.getBuildingsiteNumForAll(projectid));
		jsonObject.put("allmoney", distributioninfoDao.getAllMoney(projectid));
		return jsonObject;
	}

}
