package com.service;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;

import com.common.exception.ServiceException;

/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-6-18 下午03:24:58
 * 版本号： v1.0
 */
public interface OtherService {
	public JSONObject getAnalyseInfo(String projectid) throws ServiceException, JSONException;
}
