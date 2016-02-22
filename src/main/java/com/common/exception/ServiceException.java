/**
* Copyright ? 2014-1-9 liuninglin
* WorkingTimeRecordSystem 上午01:19:31
* Version 1.0
* All right reserved.
*
*/

package com.common.exception;

/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-1-9 上午01:19:31
 * 版本号： v1.0
 */
public class ServiceException extends Exception
{
	private static final long serialVersionUID = 1L;

	public ServiceException(String message)
	{
		super(message);
	}
}
