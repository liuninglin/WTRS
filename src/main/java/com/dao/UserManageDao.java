/**
* Copyright ? 2014-1-9 liuninglin
* WorkingTimeRecordSystem 上午01:02:53
* Version 1.0
* All right reserved.
*
*/

package com.dao;

import org.springframework.dao.DataAccessException;


/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-1-9 上午01:02:53
 * 版本号： v1.0
 */
public interface UserManageDao<T>
{
	public T checkUserManageIsExist(String username, String password) throws DataAccessException;
	
	public boolean addUserManage(T t) throws DataAccessException;
	
	public boolean editUserManageUpdateTime(String username) throws DataAccessException;
	
	public boolean editUser(T t) throws DataAccessException;
	
	public boolean checkUsernameIsExist(String username, String userid) throws DataAccessException;
	
	public T getUserByUserId(String userid) throws DataAccessException;
}
