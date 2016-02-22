/**
* Copyright ? 2014-1-7 liuninglin
* WorkingTimeRecordSystem 下午05:08:57
* Version 1.0
* All right reserved.
*
*/

package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.UserManage;

/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-1-7 下午05:08:57
 * 版本号： v1.0
 */
public class SecurityFilter extends HttpServlet implements Filter
{
	private static final long serialVersionUID = 1L;

	private String[] logonStringsArray;
	private String[] includeStringsArray;
	private String redirectPath;
	private String disabletestfilter;
	
	
	@Override
	public void doFilter(ServletRequest sq, ServletResponse sr, FilterChain fc) throws IOException, ServletException
	{
		HttpServletRequest httpServletRequest = (HttpServletRequest)sq;
		HttpServletResponse httpServletResponse = (HttpServletResponse)sr;
		String requestUrl = httpServletRequest.getRequestURI();
		
		if("Y".equals(disabletestfilter.trim()))
		{
			fc.doFilter(sq, sr);
			return;
		}
		if(containStr(logonStringsArray, requestUrl))
		{
			if(requestUrl.trim().contains("loginPage.html"))
			{
				HttpSession httpSession = httpServletRequest.getSession();
				UserManage usermanage = httpSession.getAttribute("usermanage") == null ? null : (UserManage)httpSession.getAttribute("usermanage");
				if(null != usermanage)
				{
					httpServletResponse.sendRedirect("indexPage.html");
					return;
				}
			}
			
			fc.doFilter(sq, sr);
			return;
		}
		if(!containStr(includeStringsArray, requestUrl))
		{
			fc.doFilter(sq, sr);
			return;
		}
		
		HttpSession httpSession = httpServletRequest.getSession();
		UserManage usermanage = httpSession.getAttribute("usermanage") == null ? null : (UserManage)httpSession.getAttribute("usermanage");
		if(null == usermanage)
		{
			httpServletResponse.sendRedirect(redirectPath);
			return;
		}
		
		fc.doFilter(sq, sr);
		return;
	}

	@Override
	public void init(FilterConfig fc) throws ServletException
	{
		logonStringsArray = fc.getInitParameter("logonStrings").split(";");
		includeStringsArray = fc.getInitParameter("includeStrings").split(";");
		redirectPath = fc.getInitParameter("redirectPath");
		disabletestfilter = fc.getInitParameter("disabletestfilter");
	}
	
	private boolean containStr(String[] strArray, String str)
	{
		for(String s : strArray)
		{
			if(str.contains(s))
			{
				return true;
			}
		}
		
		return false;
	}
}
