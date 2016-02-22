package com.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.json.JSONException;
import org.apache.commons.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.exception.ServiceException;
import com.common.util.UtilPackage;
import com.dao.BuildingsiteDao;
import com.dao.PackageworkDao;
import com.entity.Packagework;
import com.entity.PackageworkDetail;
import com.entity.PageWidget;
import com.service.PackageworkService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-17 下午02:36:30
* 版本号： v1.0
* @param <T>
*/
@Service("packageworkService")
public class PackageworkServiceImpl<T extends Packagework> implements PackageworkService<T> {

	@Autowired
	private PackageworkDao<T> packageworkDao;
	
	@Autowired
	private BuildingsiteDao<T> buildingsiteDao;

	@Override
	public List<T> showPackageworkList(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteId) throws ServiceException
	{
		int startIndex = (Integer.valueOf(currentPage) - 1)* Integer.valueOf(pageSize);
		int endIndex = (Integer.valueOf(currentPage))* Integer.valueOf(pageSize);
		
		filterValue = filterCharacter(filterValue);
		
		if("".equals(orderValue))
		{
			orderValue = "TIMESTAMP(p.updatetime) desc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		return packageworkDao.getPackageworkList(filterValue, startIndex, endIndex, orderValue, buildingsiteId);
	}

	@Override
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String buildingsiteId) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		int totalCount = packageworkDao.getTotalCount(filterValue, buildingsiteId);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		int startIndex = 0;
		int endIndex = 0;
		
		if(totalPages == Integer.valueOf(currentPage))
		{
			startIndex = Integer.valueOf(pageSize) * (totalPages - 1);
			endIndex = (totalPages % Integer.valueOf(currentPage)) + startIndex;
		}
		
		PageWidget pageWidget = new PageWidget();
		pageWidget.setPageSize(pageSize);
		pageWidget.setTotalCount(totalCount + "");
		pageWidget.setTotalPages(totalPages + "");
		pageWidget.setCurrentPage(currentPage);
		pageWidget.setStartIndex(startIndex + "");
		pageWidget.setEndIndex(endIndex + "");
		
		return pageWidget;
	}
	
	public String filterCharacter(String filterValue)
	{
		boolean flag = false;
		
		if(null == filterValue || "".equals(filterValue))
		{
			filterValue = "%";
			flag = true;
		}
		if(filterValue.contains("*"))
		{
			filterValue = filterValue.replaceAll("\\*", "%");
			flag = true;
		}
		if(filterValue.contains("?"))
		{
			filterValue = filterValue.replaceAll("\\?", "_");
			flag = true;
		}
		if(filterValue.contains(" "))
		{
			filterValue = filterValue.replaceAll(" ", "%");
			flag = true;
		}
		
		if(!flag)
		{
			filterValue = "%" + filterValue + "%";
		}
		
		return filterValue;
	}

	@Override
	public JSONObject getPackageworkByBuilderidAndBuildingsiteid(String buildingsiteId, String builderId) throws ServiceException, JSONException
	{
		Packagework packagework = packageworkDao.getPackageworkByBuilderidAndBuildingsiteid(buildingsiteId, builderId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("builderid", packagework.getBuilderid());
		jsonObject.append("buildingsiteid", packagework.getBuildingsiteid());
		jsonObject.append("buildername", packagework.getBuildername());
		jsonObject.append("buildingsitename", packagework.getBuildingsitename());
		jsonObject.append("packageworkmoney", packagework.getPackageworkmoney());
		jsonObject.append("packageworkendyear", packagework.getPackageworkendyear());
		jsonObject.append("packageworkendmonth", packagework.getPackageworkendmonth());
		jsonObject.append("otherinfo", packagework.getOtherinfo());
		
		return jsonObject;
	}

	@Override
	public boolean removePackageworks(String builderIds, String buildingsiteId, String projectid) throws ServiceException
	{
		return packageworkDao.removePackageworks(builderIds, buildingsiteId, projectid);
	}

	@Override
	public PageWidget getPageWidgetForAll(String filterValue, String pageSize, String currentPage, String projectid) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		int totalCount = packageworkDao.getTotalCountForAll(filterValue, projectid);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		int startIndex = 0;
		int endIndex = 0;
		
		if(totalPages == Integer.valueOf(currentPage))
		{
			startIndex = Integer.valueOf(pageSize) * (totalPages - 1);
			endIndex = (totalPages % Integer.valueOf(currentPage)) + startIndex;
		}
		
		PageWidget pageWidget = new PageWidget();
		pageWidget.setPageSize(pageSize);
		pageWidget.setTotalCount(totalCount + "");
		pageWidget.setTotalPages(totalPages + "");
		pageWidget.setCurrentPage(currentPage);
		pageWidget.setStartIndex(startIndex + "");
		pageWidget.setEndIndex(endIndex + "");
		
		return pageWidget;
	}

	@Override
	public List<T> showPackageworkListForAll(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException
	{
		int startIndex = (Integer.valueOf(currentPage) - 1)* Integer.valueOf(pageSize);
		int endIndex = (Integer.valueOf(currentPage))* Integer.valueOf(pageSize);
		
		filterValue = filterCharacter(filterValue);
		
		if("".equals(orderValue))
		{
			orderValue = "tmp.buildername asc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		List<T> packageworkList = packageworkDao.getPackageworkListForAll(filterValue, startIndex, endIndex, orderValue, projectid);
		for(Packagework packagework : packageworkList)
		{
			packagework.setPackageworkDetailList(packageworkDao.getPackageworkDetailListForAll(packagework.getBuilderid() + ""));
		}
		
		return packageworkList;
	}

	@Override
	public HSSFWorkbook getPackageworkList(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteid, String projectid) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		if("".equals(orderValue))
		{
			orderValue = "b.name asc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		int totalCount = packageworkDao.getTotalCount(filterValue, buildingsiteid);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		try
        {
			HSSFWorkbook workBook = new HSSFWorkbook(this.getClass().getResourceAsStream("/../../static/templet/XX工地包工工资汇总模板.xls"));
            HSSFSheet sheet = workBook.getSheet("模板");
            workBook.setSheetName(0, "报表");
            
            workBook.setPrintArea(0, 0, 15, 0, totalPages*25-1);
            
            for(int i = 1; i <= totalPages; i++)
            {
	      		int startIndex = (Integer.valueOf(i) - 1)* Integer.valueOf(pageSize);
        		int endIndex = (Integer.valueOf(i))* Integer.valueOf(pageSize);
                List<T> packageworkList = packageworkDao.getPackageworkList(filterValue, startIndex, endIndex, orderValue, buildingsiteid);
	      		
	      		sheet.getRow((i-1)*25).getCell(2).setCellValue(buildingsiteDao.getBuildingsiteNameByBuildingsiteId(buildingsiteid)+"包工工资汇总表");
	      		int monthcount = 0;
      			for(int year = Integer.valueOf(UtilPackage.startyear); year <= Integer.valueOf(UtilPackage.endyear); year++)
      			{
      				if(UtilPackage.startyear.equals(UtilPackage.endyear))
      				{
      					for(int month = Integer.valueOf(UtilPackage.startmonth); month <= Integer.valueOf(UtilPackage.endmonth); month++)
          				{
          					sheet.getRow((i-1)*25+1).getCell(2+monthcount).setCellValue(month + "月");
          					monthcount++;
          				}
      				}
      				else
      				{
      					if(year == Integer.valueOf(UtilPackage.startyear))
      					{
      						for(int month = Integer.valueOf(UtilPackage.startmonth); month <= 12; month++)
              				{
              					sheet.getRow((i-1)*25+1).getCell(2+monthcount).setCellValue(month + "月");
              					monthcount++;
              				}
      					}
      					else if(year == Integer.valueOf(UtilPackage.endyear))
      					{
      						for(int month = 1; month <= Integer.valueOf(UtilPackage.endmonth); month++)
              				{
              					sheet.getRow((i-1)*25+1).getCell(2+monthcount).setCellValue(month + "月");
              					monthcount++;
              				}
      					}
      					else
      					{
      						for(int month = 1; month <= 12; month++)
              				{
              					sheet.getRow((i-1)*25+1).getCell(2+monthcount).setCellValue(month + "月");
              					monthcount++;
              				}
      					}
      				}
      			}
	      		
	      		for(int j = 0; j < packageworkList.size(); j++)
	      		{
	      			Packagework packagework = packageworkList.get(j);
	      			
	      			sheet.getRow((i-1)*25 + 2 + j).getCell(1).setCellValue(packagework.getBuildername());
	      			
	      			int yearNumber = Integer.valueOf(packagework.getPackageworkendyear());
      				int monthNumber = Integer.valueOf(packagework.getPackageworkendmonth());
      				int monthTemp = UtilPackage.getMonth(UtilPackage.startyear, UtilPackage.startmonth, yearNumber + "", monthNumber + "");
      				
      				sheet.getRow((i-1)*25 + 2 + j).getCell(monthTemp+1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
      				sheet.getRow((i-1)*25 + 2 + j).getCell(monthTemp+1).setCellValue(Double.valueOf(packagework.getPackageworkmoney()));
	      		}
            }
            sheet.setForceFormulaRecalculation(true);
            return (workBook);
        }
        catch (Exception e)
        {
        	System.out.println(e);
        }
		return null;
	}

	@Override
	public HSSFWorkbook getPackageworkListForAll(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		if("".equals(orderValue))
		{
			orderValue = "tmp.buildername asc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		int totalCount = packageworkDao.getTotalCountForAll(filterValue, projectid);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		try
        {
			HSSFWorkbook workBook = new HSSFWorkbook(this.getClass().getResourceAsStream("/../../static/templet/包工工资汇总模板.xls"));
            HSSFSheet sheet = workBook.getSheet("模板");
            workBook.setSheetName(0, "报表");
            
            workBook.setPrintArea(0, 0, 8, 0, totalPages*25-1);
            
            for(int i = 1; i <= totalPages; i++)
            {
	      		int startIndex = (Integer.valueOf(i) - 1)* Integer.valueOf(pageSize);
        		int endIndex = (Integer.valueOf(i))* Integer.valueOf(pageSize);
                List<T> packageworkList = packageworkDao.getPackageworkListForAll(filterValue, startIndex, endIndex, orderValue, projectid);
        		for(Packagework packagework : packageworkList)
        		{
        			packagework.setPackageworkDetailList(packageworkDao.getPackageworkDetailListForAll(packagework.getBuilderid() + ""));
        		}
	      		
        		List<Map<String, String>> buildingsiteList = null;
        		buildingsiteList = UtilPackage.formatBuildingsitenameList(packageworkList, "packagework");
        		if(buildingsiteList.size() > 5)
        		{
        			return null;
        		}
        		
        		for(int x = 0; x < buildingsiteList.size(); x++)
        		{
        			sheet.getRow((i-1)*25+1).getCell(2+x).setCellValue(buildingsiteList.get(x).get("name"));
        		}
        		
	      		for(int j = 0; j < packageworkList.size(); j++)
	      		{
	      			Packagework packagework = packageworkList.get(j);
	      			
	      			sheet.getRow((i-1)*25 + 2 + j).getCell(1).setCellValue(packagework.getBuildername());
	      			
	      			List<PackageworkDetail> packageworkDetailList = packagework.getPackageworkDetailList();
	      			for(int w = 0; w < packageworkDetailList.size(); w++)
	      			{
	      				PackageworkDetail packageworkDetail = packageworkDetailList.get(w);
	      				String buildingsiteName = packageworkDetail.getBuildingsitename();
	      				
	      				if(sheet.getRow((i-1)*25 + 1).getCell(2).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(2).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(2).setCellValue(Double.valueOf(packageworkDetail.getPackageworkmoney()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(3).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(3).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(3).setCellValue(Double.valueOf(packageworkDetail.getPackageworkmoney()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(4).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(4).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(4).setCellValue(Double.valueOf(packageworkDetail.getPackageworkmoney()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(5).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(5).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(5).setCellValue(Double.valueOf(packageworkDetail.getPackageworkmoney()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(6).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(6).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(6).setCellValue(Double.valueOf(packageworkDetail.getPackageworkmoney()));
	      				}
	      			}
	      		}
            }
            sheet.setForceFormulaRecalculation(true);
            return (workBook);
        }
        catch (Exception e)
        {
        }
		return null;
	}

	@Override
	public boolean changePackagework(T packagework) throws ServiceException
	{
		Packagework packageworkTemp = (Packagework)packagework;
		packageworkTemp.setPackageworkendmonth(UtilPackage.formatMonthOrDay(Integer.valueOf(packageworkTemp.getPackageworkendmonth())));
		
		Packagework packageworkTemp2 = packageworkDao.getPackageworkByPackageworkDetail(packageworkTemp.getBuilderid() + "", packageworkTemp.getBuildingsiteid() + "");
		if(packageworkTemp2 == null)
		{
			return packageworkDao.addPackagework(packagework);
		}
		else
		{
			return packageworkDao.editPackagework(packagework);
		}
	}
}
