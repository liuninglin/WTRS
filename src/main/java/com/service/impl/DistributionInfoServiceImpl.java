package com.service.impl;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.exception.ServiceException;
import com.dao.DistributionInfoDao;
import com.entity.DistributionInfo;
import com.entity.PageWidget;
import com.service.DistributionInfoService;

/**
* 类描述： 
* 创建者：刘宁林
* 项目名称： WorkingTimeRecodSystem
* 创建时间： 2013-12-11 下午05:04:40
* 版本号： v1.0
*/
@Service("distributioninfoService")
public class DistributionInfoServiceImpl<T extends DistributionInfo> implements DistributionInfoService<T> {

	@Autowired
	private DistributionInfoDao<T> distributioninfoDao;

	@Override
	public List<T> showDistributionInfoList(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException
	{
		int startIndex = (Integer.valueOf(currentPage) - 1)* Integer.valueOf(pageSize);
		int endIndex = (Integer.valueOf(currentPage))* Integer.valueOf(pageSize);
		if(Integer.valueOf(pageSize) == -1)
		{
			startIndex = 0;
			endIndex = 10000000;
		}
		
		filterValue = filterCharacter(filterValue);
		
		if("".equals(orderValue))
		{
			orderValue = "TIMESTAMP(tmp.builderupdatetime) desc";
		}
		else
		{
			orderValue = orderValue.replaceAll("_", " ");
		}
		
		return distributioninfoDao.getDistributionInfoList(filterValue, startIndex, endIndex, orderValue, projectid);
	}

	@Override
	public PageWidget getPageWidget(String filterValue, String pageSize, String currentPage, String projectid) throws ServiceException
	{
		filterValue = filterCharacter(filterValue);
		
		int totalCount = distributioninfoDao.getTotalCount(filterValue, projectid);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		if(Integer.valueOf(pageSize) == -1)
		{
			totalPages = 1;
		}
		
		int startIndex = 0;
		int endIndex = 0;
		
		if(totalPages == Integer.valueOf(currentPage))
		{
			startIndex = Integer.valueOf(pageSize) * (totalPages - 1);
			endIndex = (totalPages % Integer.valueOf(currentPage)) + startIndex;
			
			if(Integer.valueOf(pageSize) == -1)
			{
				endIndex = totalCount - 1;
			}
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
	public HSSFWorkbook getDistributionInfoListForAll(String filterValue, String pageSize, String currentPage, String orderValue, String projectid) throws ServiceException
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
		
		int totalCount = distributioninfoDao.getTotalCount(filterValue, projectid);
		int totalPages = Double.valueOf(Math.ceil((double)totalCount / Double.valueOf(pageSize))).intValue();
		
		try
        {
			HSSFWorkbook workBook = new HSSFWorkbook(this.getClass().getResourceAsStream("/../../static/templet/工资分配表模板.xls"));
            HSSFSheet sheet = workBook.getSheet("模板");
            workBook.setSheetName(0, "报表");
            
            workBook.setPrintArea(0, 0, 11, 0, totalPages*24-1);
            
            for(int i = 1; i <= totalPages; i++)
            {
	      		int startIndex = (Integer.valueOf(i) - 1)* Integer.valueOf(pageSize);
        		int endIndex = (Integer.valueOf(i))* Integer.valueOf(pageSize);
        		
        		List<DistributionInfo> distributionInfoList = (List<DistributionInfo>)distributioninfoDao.getDistributionInfoList(filterValue, startIndex, endIndex, orderValue, projectid);
        		
	      		for(int j = 0; j < distributionInfoList.size(); j++)
	      		{
	      			DistributionInfo distributionInfo = distributionInfoList.get(j);
	      			
	      			sheet.getRow((i-1)*24 + 3 + j).getCell(1).setCellValue(distributionInfo.getBuildername());
	      			
	      			if(distributionInfo.getTimeworktotalmoney() != null)
	      			{
	      				sheet.getRow((i-1)*24 + 3 + j).getCell(2).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	      				sheet.getRow((i-1)*24 + 3 + j).getCell(2).setCellValue(Double.valueOf(distributionInfo.getTimeworktotalmoney()));
	      			}
	      			if(distributionInfo.getPackageworktotalmoney() != null)
	      			{
	      				sheet.getRow((i-1)*24 + 3 + j).getCell(3).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	      				sheet.getRow((i-1)*24 + 3 + j).getCell(3).setCellValue(Double.valueOf(distributionInfo.getPackageworktotalmoney()));
	      			}
	      			if(distributionInfo.getPackageworktotalmoney() != null)
	      			{
	      				sheet.getRow((i-1)*24 + 3 + j).getCell(3).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	      				sheet.getRow((i-1)*24 + 3 + j).getCell(3).setCellValue(Double.valueOf(distributionInfo.getPackageworktotalmoney()));
	      			}
	      			if(distributionInfo.getDebttotalmoney() != null)
	      			{
	      				sheet.getRow((i-1)*24 + 3 + j).getCell(6).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	      				sheet.getRow((i-1)*24 + 3 + j).getCell(6).setCellValue(-Double.valueOf(distributionInfo.getDebttotalmoney()));
	      			}
	      			if(distributionInfo.getFoodtotalmoney() != null)
	      			{
	      				sheet.getRow((i-1)*24 + 3 + j).getCell(7).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	      				sheet.getRow((i-1)*24 + 3 + j).getCell(7).setCellValue(-Double.valueOf(distributionInfo.getFoodtotalmoney()));
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
}
