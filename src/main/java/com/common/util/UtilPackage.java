/**
* Copyright ? 2014-2-19 liuninglin
* WorkingTimeRecordSystem 下午02:44:52
* Version 1.0
* All right reserved.
*
*/

package com.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.entity.Buildingsite;
import com.entity.Food;
import com.entity.FoodDetail;
import com.entity.MonthEntity;
import com.entity.Packagework;
import com.entity.PackageworkDetail;
import com.entity.Workday;
import com.entity.WorkdayDetail;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

/**
 * 类描述： 
 * 创建者：刘宁林
 * 项目名称： WorkingTimeRecordSystem
 * 创建时间： 2014-2-19 下午02:44:52
 * 版本号： v1.0
 */
public class UtilPackage
{
	public static String starttime;
	public static String endtime;
	
	public static String startyear;
	public static String startmonth;
	public static String endyear;
	public static String endmonth;
	
	public static String projectid;
	
	static
	{
        try
        {   
        	Properties prop = new Properties(); 
        	InputStream in = UtilPackage.class.getResourceAsStream("/others.properties");   
            prop.load(in);  
            
            startyear = prop.getProperty("startyear");
            startmonth = prop.getProperty("startmonth");
            endyear = prop.getProperty("endyear");
            endmonth = prop.getProperty("endmonth");
            
            starttime = startyear + "-" + startmonth + "-01 00:00:00";
            endtime = endyear + "-" + endmonth + "-01 00:00:00";
        }
        catch (IOException e) 
        {   
            e.printStackTrace();   
        }
	}
	
	public static boolean updateStartTimeAndEndTime(String startyear, String startmonth, String endyear, String endmonth)
	{
		try
		{
			Properties prop = new Properties(); 
			OutputStream outputStream = new FileOutputStream("/others.properties");
			prop.setProperty("startyear", startyear);
			prop.setProperty("startmonth", startmonth);
			prop.setProperty("endyear", endyear);
			prop.setProperty("endmonth", endmonth);
			prop.store(outputStream, "1");
			outputStream.close();
			
			UtilPackage.startyear = startyear;
			UtilPackage.startmonth = startmonth;
			UtilPackage.endyear = endyear;
			UtilPackage.endmonth = endmonth;
			
			starttime = startyear + "-" + startmonth + "-01 00:00:00";
			endtime = endyear + "-" + endmonth + "-01 00:00:00";
		}
		catch (Exception e) {
			return false;
		}
    	
		return true;
	}
	
	public static String formatDate(String year, String month, String type)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if("start".equals(type))
		{
			return year + "-" + month + "-01 00:00:00";
		}
		else if("end".equals(type))
		{
			String strTemp = year + "-" + month + "-01 00:00:00";
			try
			{
				Date dateTemp = sdf.parse(strTemp);
				Calendar calendarTemp = Calendar.getInstance();   
				calendarTemp.setTime(dateTemp); 
				calendarTemp.add(Calendar.MONTH, 1);
				dateTemp = calendarTemp.getTime();
				return sdf.format(dateTemp);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
		return null;
	}
	
	public static List<Map<String, String>> formatMonthForChineseCharacter(String startworkyear, String startworkmonth, String endworkyear, String endworkmonth)
	{
		List<Map<String, String>> monthList = new ArrayList<Map<String, String>>();
		List<String> numberMonthList = new ArrayList<String>();
		
		for(int i = Integer.valueOf(startworkyear); i <= Integer.valueOf(endworkyear); i++)
		{
			if(i == Integer.valueOf(startworkyear) && i == Integer.valueOf(endworkyear))
			{
				for(int j = Integer.valueOf(startworkmonth); j <= Integer.valueOf(endworkmonth); j++)
				{
					Map<String, String> mapTemp = new HashMap<String, String>();
					if(numberMonthList.contains(j + ""))
					{
						mapTemp.put("chinese", UtilPackage.getChineseMonth(j) + "("+i+")");
					}
					else
					{
						mapTemp.put("chinese", UtilPackage.getChineseMonth(j));
					}
					
					mapTemp.put("number", i+"-"+formatMonthOrDay(j));
					mapTemp.put("year", i+"");
					mapTemp.put("month", formatMonthOrDay(j));
					monthList.add(mapTemp);
					numberMonthList.add(formatMonthOrDay(j));
				}
			}
			else if(i == Integer.valueOf(startworkyear) && i != Integer.valueOf(endworkyear))
			{
				for(int j = Integer.valueOf(startworkmonth); j <= 12; j++)
				{
					Map<String, String> mapTemp = new HashMap<String, String>();
					if(numberMonthList.contains(j + ""))
					{
						mapTemp.put("chinese", UtilPackage.getChineseMonth(j) + "("+i+")");
					}
					else
					{
						mapTemp.put("chinese", UtilPackage.getChineseMonth(j));
					}
					
					mapTemp.put("number", i+"-"+formatMonthOrDay(j));
					mapTemp.put("year", i+"");
					mapTemp.put("month", formatMonthOrDay(j));
					monthList.add(mapTemp);
					numberMonthList.add(formatMonthOrDay(j));
				}
			}
			else if(i == Integer.valueOf(endworkyear) && i != Integer.valueOf(startworkyear))
			{
				for(int j = 1; j <= Integer.valueOf(endworkmonth); j++)
				{
					Map<String, String> mapTemp = new HashMap<String, String>();
					if(numberMonthList.contains(j + ""))
					{
						mapTemp.put("chinese", UtilPackage.getChineseMonth(j) + "("+i+")");
					}
					else
					{
						mapTemp.put("chinese", UtilPackage.getChineseMonth(j));
					}
					
					mapTemp.put("number", i+"-"+formatMonthOrDay(j));
					mapTemp.put("year", i+"");
					mapTemp.put("month", formatMonthOrDay(j));
					monthList.add(mapTemp);
					numberMonthList.add(formatMonthOrDay(j));
				}
			}
			else
			{
				for(int j = 1; j <= 12; j++)
				{
					Map<String, String> mapTemp = new HashMap<String, String>();
					if(numberMonthList.contains(j + ""))
					{
						mapTemp.put("chinese", UtilPackage.getChineseMonth(j) + "("+i+")");
					}
					else
					{
						mapTemp.put("chinese", UtilPackage.getChineseMonth(j));
					}
					
					mapTemp.put("number", i+"-"+formatMonthOrDay(j));
					mapTemp.put("year", i+"");
					mapTemp.put("month", formatMonthOrDay(j));
					monthList.add(mapTemp);
					numberMonthList.add(formatMonthOrDay(j));
				}
			}
		}
		
		return monthList;
	}
	
	public static String formatMonthOrDay(int month)
	{
		if(String.valueOf(month).length() <= 1)
		{
			return "0" + month;
		}
		else
		{
			return "" + month;
		}
	}
	
	public static String getChineseMonth(int month)
	{
		switch(month)
		{
		case 1:
			return "元月";
		case 2:
			return "2月";
		case 3:
			return "3月";
		case 4:
			return "4月";
		case 5:
			return "5月";
		case 6:
			return "6月";
		case 7:
			return "7月";
		case 8:
			return "8月";
		case 9:
			return "9月";
		case 10:
			return "10月";
		case 11:
			return "11月";
		case 12:
			return "12月";
		}
		
		return null;
	}
	
	public static List<Map<String, String>> formatBuildingsitenameList(List list, String type)
	{
		List<Map<String, String>> buildingsiteList = new ArrayList<Map<String, String>>();
		List<String> buildingsiteidList = new ArrayList<String>();
		
		if("food".equals(type))
		{
			for(Food food : (List<Food>)list)
			{
				for(FoodDetail foodDetail : food.getFoodDetailList())
				{
					if(!buildingsiteidList.contains(foodDetail.getBuildingsiteid()))
					{
						buildingsiteidList.add(foodDetail.getBuildingsiteid());
						Map<String, String> buildingsiteMap = new HashMap<String, String>();
						buildingsiteMap.put("id", foodDetail.getBuildingsiteid());
						buildingsiteMap.put("name", foodDetail.getBuildingsitename());
						buildingsiteList.add(buildingsiteMap);
					}
				}
			}
		}
		else if("packagework".equals(type))
		{
			for(Packagework packagework : (List<Packagework>)list)
			{
				for(PackageworkDetail packageworkDetail : packagework.getPackageworkDetailList())
				{
					if(!buildingsiteidList.contains(packageworkDetail.getBuildingsiteid()))
					{
						buildingsiteidList.add(packageworkDetail.getBuildingsiteid());
						Map<String, String> buildingsiteMap = new HashMap<String, String>();
						buildingsiteMap.put("id", packageworkDetail.getBuildingsiteid());
						buildingsiteMap.put("name", packageworkDetail.getBuildingsitename());
						buildingsiteList.add(buildingsiteMap);
					}
				}
			}
		}
		else if("workday".equals(type))
		{
			for(Workday workday : (List<Workday>)list)
			{
				for(WorkdayDetail workdayDetail : workday.getWorkdayDetailList())
				{
					if(!buildingsiteidList.contains(workdayDetail.getBuildingsiteid()))
					{
						buildingsiteidList.add(workdayDetail.getBuildingsiteid());
						Map<String, String> buildingsiteMap = new HashMap<String, String>();
						buildingsiteMap.put("id", workdayDetail.getBuildingsiteid());
						buildingsiteMap.put("name", workdayDetail.getBuildingsitename());
						buildingsiteList.add(buildingsiteMap);
					}
				}
			}
		}
		
		return buildingsiteList;
	}
	
	public static String encodeFilename(HttpServletRequest request, String fileName)
    {
        String agent = request.getHeader("USER-AGENT");
        try
        {
            // IE
            if (null != agent && -1 != agent.indexOf("MSIE"))
            {
                fileName = URLEncoder.encode(fileName, "UTF8");
                // Firefox
            }
            else if (null != agent && -1 != agent.indexOf("Mozilla"))
            {
                fileName = MimeUtility.encodeText(fileName, "UTF8", "B");
            }
        }
        catch (UnsupportedEncodingException e)
        {
            try
            {
                fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            }
            catch (UnsupportedEncodingException e1)
            {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return fileName;
    }
	
	public static int getMonth(String startyear, String startmonth, String endyear, String endmonth) {
		
		int result = 0;
		
		if(startyear.equals(endyear))
	    {
			result = Integer.valueOf(endmonth) - Integer.valueOf(startmonth);
	    }
	    else
	    {
	    	result = 12 * (Integer.valueOf(endyear) - Integer.valueOf(startyear)) + Integer.valueOf(endmonth) - Integer.valueOf(startmonth);
	    }
		
		return result + 1;
    }
	
	public static String[] processYearAndMonth(String yearAndMonthStr)
	{
		String[] strArray = new String[]{};
		String year = yearAndMonthStr.split("年")[0];
		String month = yearAndMonthStr.split("年")[1].split("月")[0];
		strArray[0] = year;
		strArray[1] = month;
		
		return strArray;
	}
	
	
	/**
	 * 汉字转拼音缩写
	 * 
	 * @param str
	 *            要转换的汉字字符串
	 * @return String 拼音缩写
	 */
	public static String getPYString(String str) {
		str = UtilPackage.replaceBlank(str);
		
		String tempStr = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 33 && c <= 126) {// 字母和符号原样保留
				tempStr += String.valueOf(c);
			} else {// 累加拼音声母
				tempStr += getPYChar(String.valueOf(c));
			}
		}
		return tempStr;
	}

	/**
	 * 取单个字符的拼音声母
	 * 
	 * @param c
	 *            //要转换的单个汉字
	 * @return String 拼音声母
	 */
	public static String getPYChar(String c) {
		byte[] array = new byte[2];
		array = String.valueOf(c).getBytes();
		int i = (short) (array[0] - '\0' + 256) * 256 + ((short) (array[1] - '\0' + 256));
		if (i < 0xB0A1)
			return "*";
		if (i < 0xB0C5)
			return "a";
		if (i < 0xB2C1)
			return "b";
		if (i < 0xB4EE)
			return "c";
		if (i < 0xB6EA)
			return "d";
		if (i < 0xB7A2)
			return "e";
		if (i < 0xB8C1)
			return "f";
		if (i < 0xB9FE)
			return "g";
		if (i < 0xBBF7)
			return "h";
		if (i < 0xBFA6)
			return "j";
		if (i < 0xC0AC)
			return "k";
		if (i < 0xC2E8)
			return "l";
		if (i < 0xC4C3)
			return "m";
		if (i < 0xC5B6)
			return "n";
		if (i < 0xC5BE)
			return "o";
		if (i < 0xC6DA)
			return "p";
		if (i < 0xC8BB)
			return "q";
		if (i < 0xC8F6)
			return "r";
		if (i < 0xCBFA)
			return "s";
		if (i < 0xCDDA)
			return "t";
		if (i < 0xCEF4)
			return "w";
		if (i < 0xD1B9)
			return "x";
		if (i < 0xD4D1)
			return "y";
		if (i < 0xD7FA)
			return "z";
		return "*";
	}
	
	public static List<Map<String, String>> formatMonthList(List<MonthEntity> monthEntityList)
	{
		List<Map<String, String>> monthList = new ArrayList<Map<String, String>>();
		for(MonthEntity me : monthEntityList)
		{
			Map<String, String> mapTemp = new LinkedHashMap<String, String>();
			mapTemp.put("chinese", me.getYearStr().subSequence(2, 4) + "-" + me.getMonthStr());
			mapTemp.put("number", me.getYearStr()+"-"+me.getMonthStr());
			mapTemp.put("year", me.getYearStr());
			mapTemp.put("month", me.getMonthStr());
			
			monthList.add(mapTemp);
		}
		
		return monthList;
	}
	
	public static List<Map<String, String>> formatBuildingsiteList(List<Buildingsite> buildingsiteList)
	{
		List<Map<String, String>> bMapList = new ArrayList<Map<String, String>>();
		for(Buildingsite buildingsite : buildingsiteList)
		{
			Map<String, String> mapTemp = new LinkedHashMap<String, String>();
			mapTemp.put("id", buildingsite.getId() + "");
			mapTemp.put("name", buildingsite.getName());
			
			bMapList.add(mapTemp);
		}
		
		return bMapList;
	}
	
	//去除字符串中所有空格、\t、\n、全角空格
	public static String replaceBlank(String str) 
	{
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
            dest = dest.replaceAll("[\\s\\p{Zs}]+", "");
        }
        return dest;
    }
}
