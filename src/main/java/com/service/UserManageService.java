package com.service;

import com.common.exception.ServiceException;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:04:31
* 版本号： v1.0
*/
public interface UserManageService<T> {
	public T checkLogin(String username, String password) throws ServiceException;
	
	public boolean checkUsernameIsExist(String username, String userid) throws ServiceException;
	
	public boolean updateUser(T t) throws ServiceException;
	
	public T getUserByUserId(String userid) throws ServiceException;
}
