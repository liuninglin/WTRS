package com.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.exception.ServiceException;
import com.common.util.UtilPackage;
import com.entity.UserManage;
import com.service.OtherService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-25 下午02:16:06
* 版本号： v1.0
*/
@Controller
public class OtherController{

	@Resource(name = "otherService")
	private OtherService otherService;
	
	@RequestMapping("/getStartTimeAndEndTime")
	@ResponseBody
	public String getStartTimeAndEndTime(HttpServletRequest req, HttpServletResponse resp, UserManage usermanage, Model model, String remembervalue) throws IOException, JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("startyear", UtilPackage.startyear);
		jsonObject.put("startmonth", UtilPackage.startmonth);
		jsonObject.put("endyear", UtilPackage.endyear);
		jsonObject.put("endmonth", UtilPackage.endmonth);
		return jsonObject.toString();
	}
	
	@RequestMapping("/updateStartTimeAndEndTime")
	@ResponseBody
	public String updateStartTimeAndEndTime(String startyear,String startmonth, String endyear, String endmonth, HttpServletRequest req, HttpServletResponse resp, UserManage usermanage, Model model, String remembervalue) throws IOException, JSONException {
		if(!UtilPackage.updateStartTimeAndEndTime(startyear, startmonth, endyear, endmonth))
		{
			return "fail";
		}
		
		return "success";
	}
	
	@RequestMapping("/getAnalyseInfo")
	@ResponseBody
	public String getAnalyseInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException, JSONException {
		try
		{
			return otherService.getAnalyseInfo(UtilPackage.projectid).toString();
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
