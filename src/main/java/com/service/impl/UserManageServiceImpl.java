package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.exception.ServiceException;
import com.dao.UserManageDao;
import com.entity.UserManage;
import com.service.UserManageService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:04:40
* 版本号： v1.0
*/
@Service("userService")
public class UserManageServiceImpl<T extends UserManage> implements UserManageService<T> {

	@Autowired
	private UserManageDao<T> userManageDao;
	
	//用户登录
	@Override
	public T checkLogin(String username, String password) throws ServiceException
	{
		if(null == username || "".equals(username.trim()) || null == password || "".equals(password.trim()))
		{
			throw new ServiceException("用户名或密码不能为空！");
		}
		return userManageDao.checkUserManageIsExist(username, password);
	}

	@Override
	public boolean checkUsernameIsExist(String username, String userid) throws ServiceException
	{
		return userManageDao.checkUsernameIsExist(username, userid);
	}

	@Override
	public boolean updateUser(T t) throws ServiceException
	{
		return userManageDao.editUser(t);
	}

	@Override
	public T getUserByUserId(String userid) throws ServiceException
	{
		return userManageDao.getUserByUserId(userid);
	}
}
