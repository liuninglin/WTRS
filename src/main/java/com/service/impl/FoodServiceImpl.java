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
import com.dao.FoodDao;
import com.entity.Food;
import com.entity.FoodDetail;
import com.entity.PageWidget;
import com.service.FoodService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecordSystem
* 创建时间： 2014-2-20 上午12:32:06
* 版本号： v1.0
* @param <T>
*/
@Service("foodService")
public class FoodServiceImpl<T extends Food> implements FoodService<T> {

	@Autowired
	private FoodDao<T> foodDao;
	
	@Autowired
	private BuildingsiteDao<T> buildingsiteDao;

	@Override
	public List<T> showFoodList(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteid, String projectid) throws ServiceException
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
		
		List<T> foodList = foodDao.getFoodList(filterValue, startIndex, endIndex, orderValue, buildingsiteid, projectid);
		for(Food food : foodList)
		{
			food.setFoodDetailList(foodDao.getFoodDetailList(buildingsiteid, food.getBuilderid() + "", projectid));
		}
		
		return foodList;
	}
		
	@Override
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String buildingsiteid, String projectid) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		int totalCount = foodDao.getTotalCount(filterValue, buildingsiteid, projectid);
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
	public boolean changeFood(T food) throws ServiceException
	{
		Food foodTemp = (Food)food;
		foodTemp.setFoodmonth(UtilPackage.formatMonthOrDay(Integer.valueOf(foodTemp.getFoodmonth())));
		if("".equals(food.getFoodmoney().trim()) || "0".equals(food.getFoodmoney().trim()))
		{
			return foodDao.removeFood(food);
		}
		
		Food foodTemp2 = foodDao.getFoodByFoodDetail(foodTemp.getBuilderid() + "", foodTemp.getBuildingsiteid() + "", foodTemp.getFoodyear(), foodTemp.getFoodmonth());
		if(foodTemp2 == null)
		{
			return foodDao.addFood(food);
		}
		else
		{
			if("".equals(food.getFoodmoney().trim()) || "0".equals(food.getFoodmoney().trim()))
			{
				return foodDao.removeFood(food);
			}
			else
			{
				if("#".equals(food.getFoodmoney().trim()))
				{
					return foodDao.editFoodDetailInfo(food);
				}
				else
				{
					if(food.getOtherinfo() == null)
					{
						return foodDao.editFood(food);
					}
					else
					{
						return foodDao.editFoodInfo(food);
					}
				}
			}
		}
	}

	@Override
	public PageWidget getPageWidgetForAll(String filterValue, String pageSize, String currentPage, String projectid) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		int totalCount = foodDao.getTotalCountForAll(filterValue, projectid);
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
	public List<T> showFoodListForAll(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException
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
		
		List<T> foodList = foodDao.getFoodListForAll(filterValue, startIndex, endIndex, orderValue, projectid);
		for(Food food : foodList)
		{
			food.setFoodDetailList(foodDao.getFoodDetailListForAll(food.getBuilderid() + "", projectid));
		}
		
		return foodList;
	}

	@Override
	public HSSFWorkbook getFoodListForMonth(String filterValue, String pageSize, String currentPage, String orderValue, String buildingsiteid, String projectid)
			throws ServiceException
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
		
		int totalCount = foodDao.getTotalCount(filterValue, buildingsiteid, projectid);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		try
        {
			HSSFWorkbook workBook = new HSSFWorkbook(this.getClass().getResourceAsStream("/../../static/templet/月伙食费模板.xls"));
            HSSFSheet sheet = workBook.getSheet("模板");
            workBook.setSheetName(0, "报表");
            
//            for(int i = totalPages*26; i < totalPages*26+(8-totalPages)*26; i++)
//            {
//            	if(null != sheet.getRow(i))
//            	{
//            		POIUtils.removeRow(sheet, i);
//            	}
//            }
            workBook.setPrintArea(0, 0, 12, 0, totalPages*25-1);
            
            for(int i = 1; i <= totalPages; i++)
            {
	      		int startIndex = (Integer.valueOf(i) - 1)* Integer.valueOf(pageSize);
        		int endIndex = (Integer.valueOf(i))* Integer.valueOf(pageSize);
                List<T> foodList = foodDao.getFoodList(filterValue, startIndex, endIndex, orderValue, buildingsiteid, projectid);
        		for(Food food : foodList)
        		{
        			food.setFoodDetailList(foodDao.getFoodDetailList(buildingsiteid, food.getBuilderid() + "", projectid));
        		}
	      		
	      		sheet.getRow((i-1)*25).getCell(1).setCellValue(buildingsiteDao.getBuildingsiteNameByBuildingsiteId(buildingsiteid)+"伙食费");
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
	      		
	      		for(int j = 0; j < foodList.size(); j++)
	      		{
	      			Food food = foodList.get(j);
	      			
	      			sheet.getRow((i-1)*25 + 2 + j).getCell(1).setCellValue(food.getBuildername());
	      			
	      			List<FoodDetail> foodDetailList = food.getFoodDetailList();
	      			for(int w = 0; w < foodDetailList.size(); w++)
	      			{
	      				FoodDetail foodDetail = foodDetailList.get(w);
	      				int yearNumber = Integer.valueOf(foodDetail.getFoodyear());
	      				int monthNumber = Integer.valueOf(foodDetail.getFoodmonth());
	      				int monthTemp = UtilPackage.getMonth(UtilPackage.startyear, UtilPackage.startmonth, yearNumber + "", monthNumber + "");
	      				
	      				sheet.getRow((i-1)*25 + 2 + j).getCell(monthTemp+1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	      				sheet.getRow((i-1)*25 + 2 + j).getCell(monthTemp+1).setCellValue(Double.valueOf(foodDetail.getFoodmoney()));
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
	public HSSFWorkbook getFoodListForAll(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException
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
		
		int totalCount = foodDao.getTotalCountForAll(filterValue, projectid);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		try
        {
			HSSFWorkbook workBook = new HSSFWorkbook(this.getClass().getResourceAsStream("/../../static/templet/伙食费汇总模板.xls"));
            HSSFSheet sheet = workBook.getSheet("模板");
            workBook.setSheetName(0, "报表");
            
            workBook.setPrintArea(0, 0, 8, 0, totalPages*25-1);
            
            for(int i = 1; i <= totalPages; i++)
            {
	      		int startIndex = (Integer.valueOf(i) - 1)* Integer.valueOf(pageSize);
        		int endIndex = (Integer.valueOf(i))* Integer.valueOf(pageSize);
                List<T> foodList = foodDao.getFoodListForAll(filterValue, startIndex, endIndex, orderValue, projectid);
        		for(Food food : foodList)
        		{
        			food.setFoodDetailList(foodDao.getFoodDetailListForAll(food.getBuilderid() + "", projectid));
        		}
	      		
        		List<Map<String, String>> buildingsiteList = null;
        		buildingsiteList = UtilPackage.formatBuildingsitenameList(foodList, "food");
        		if(buildingsiteList.size() > 5)
        		{
        			return null;
        		}
        		
        		for(int x = 0; x < buildingsiteList.size(); x++)
        		{
        			sheet.getRow((i-1)*25+1).getCell(2+x).setCellValue(buildingsiteList.get(x).get("name"));
        		}
        		
	      		for(int j = 0; j < foodList.size(); j++)
	      		{
	      			Food food = foodList.get(j);
	      			
	      			sheet.getRow((i-1)*25 + 2 + j).getCell(1).setCellValue(food.getBuildername());
	      			
	      			List<FoodDetail> foodDetailList = food.getFoodDetailList();
	      			for(int w = 0; w < foodDetailList.size(); w++)
	      			{
	      				FoodDetail foodDetail = foodDetailList.get(w);
	      				String buildingsiteName = foodDetail.getBuildingsitename();
	      				
	      				if(sheet.getRow((i-1)*25 + 1).getCell(2).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(2).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(2).setCellValue(Double.valueOf(foodDetail.getFoodmoney()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(3).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(3).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(3).setCellValue(Double.valueOf(foodDetail.getFoodmoney()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(4).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(4).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(4).setCellValue(Double.valueOf(foodDetail.getFoodmoney()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(5).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(5).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(5).setCellValue(Double.valueOf(foodDetail.getFoodmoney()));
	      				}
	      				if(sheet.getRow((i-1)*25 + 1).getCell(6).getStringCellValue().trim().equals(buildingsiteName))
	      				{
	      					sheet.getRow((i-1)*25 + 2 + j).getCell(6).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		      				sheet.getRow((i-1)*25 + 2 + j).getCell(6).setCellValue(Double.valueOf(foodDetail.getFoodmoney()));
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
	public JSONObject getFoodDetailInfo(String builderid, String buildingsiteid, String foodyear, String foodmonth) throws ServiceException, JSONException
	{
		FoodDetail fd = foodDao.getFoodDetailInfo(builderid, buildingsiteid, foodyear, foodmonth);
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("otherinfo", fd.getOtherinfo());
		return jsonObject;
	}
}
