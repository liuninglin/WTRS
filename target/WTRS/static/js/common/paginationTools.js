function loadPageForProject(currentPage)
{
	var filterValue = $('#project_filterValueInput').val();
	var pageSize = $('#project_pageSizeSelect').find('option:selected').val();
	
	if(parseInt(currentPage) < 1)
	{
		currentPage = "1";
	}
	
	$.ajax({
		type: "GET",
		url:"loadProjectPage.html",
		data:
		{	
			currentPage:currentPage,
			pageSize:pageSize,
			filterValue:filterValue,
			orderValue:$("#loadPageForProject_ordervalue").val()
		},
		dataType:"html",
		success:function(data, textStatus)
		{
			$("#project_grid_div").empty();
			$("#project_grid_div").html(data);
		}
	});
}

function loadPageForDistributioninfo(currentPage)
{
	var filterValue = $('#distributioninfo_filterValueInput').val();
	var pageSize = $('#distributioninfo_pageSizeSelect').find('option:selected').val();
	
	if(parseInt(currentPage) < 1)
	{
		currentPage = "1";
	}
	
	$.ajax({
		type: "GET",
		url:"loadDistributionInfoPage.html",
		data:
		{	
			currentPage:currentPage,
			pageSize:pageSize,
			filterValue:filterValue,
			orderValue:$("#loadPageForDistributioninfo_ordervalue").val()
		},
		dataType:"html",
		success:function(data, textStatus)
		{
			$("#distributioninfo_grid_div").empty();
			$("#distributioninfo_grid_div").html(data);
		}
	});
}

function loadPageForWorkday(currentPage, showType)
{
	if(showType == "checkin")
	{
		if(buildingsite_id != "" && buildingsite_id != "-1" && workyear_str != "" && workyear_str != "-1" && workmonth_str != "" && workmonth_str != "-1")
		{
			$.ajax({
				type: "GET",
				url:"loadWorkdayPage.html",
				data:
				{	
					showType:"checkin",
					currentPage:"1",
					pageSize:"-1",
					filterValue:"",
					orderValue:$("#loadPageForWorkday_ordervalue").val(),
					buildingsiteid:buildingsite_id,
					workyear:workyear_str,
					workmonth:workmonth_str
				},
				dataType:"html",
				success:function(data, textStatus)
				{
					$("#workdaycheckin_grid_div").empty();
					$("#workdaycheckin_grid_div").html(data);
					
					setInputNum();
					$('.tipsybox').tipsy({gravity: 's',html: true});
				}
			});
		}
		else
		{
			toastr.warning("请先选择工地、年、月信息！", '无法加载工时信息');
		}
	}
	else if(showType == "checkout")
	{
		var selectedworkmonth = $("#workday_monthselect").find("option:selected").val();
		var selectedworkyear = $("#workday_yearselect").find("option:selected").val();
		var selectedBuildingsiteId = $("#workday_buildingsiteselect").find("option:selected").val();
		
		if(selectedworkmonth == "-1" || selectedworkyear == "-1" || selectedBuildingsiteId == "-1")
		{
			openWidget("warninfoWidget");
			$("#warninfo").text("请先选择【工地】【年份】【月份】！");
			return;
		}
		
		var filterValue = $('#workday_filterValueInput').val();
		var pageSize = $('#workday_pageSizeSelect').find('option:selected').val();
		
		if(parseInt(currentPage) < 1)
		{
			currentPage = "1";
		}
		
		$.ajax({
			type: "GET",
			url:"loadWorkdayPage.html",
			data:
			{	
				showType:showType,
				currentPage:currentPage,
				pageSize:pageSize,
				filterValue:filterValue,
				orderValue:$("#loadPageForWorkday_ordervalue").val(),
				buildingsiteid:selectedBuildingsiteId,
				workyear:selectedworkyear,
				workmonth:selectedworkmonth
			},
			dataType:"html",
			success:function(data, textStatus)
			{
				$("#workdaycheckin_grid_div").empty();
				$("#workdaycheckin_grid_div").html(data);
			}
		});
	}
}

function loadPageForWorkdayForYear(currentPage)
{
	var selectedBuildingsiteId = $("#workdayforyear_buildingsiteselect").find("option:selected").val();
	
	if(selectedBuildingsiteId == "-1")
	{
		alert("请先选择【工地】");
		return;
	}
	
	var filterValue = $('#workdayforyear_filterValueInput').val();
	var pageSize = $('#workdayforyear_pageSizeSelect').find('option:selected').val();
	
	if(parseInt(currentPage) < 1)
	{
		currentPage = "1";
	}
	
	$.ajax({
		type: "GET",
		url:"loadWorkdayForYearPage.html",
		data:
		{	
			currentPage:currentPage,
			pageSize:pageSize,
			filterValue:filterValue,
			orderValue:$("#loadPageForWorkdayForYear_ordervalue").val(),
			buildingsiteid:selectedBuildingsiteId
		},
		dataType:"html",
		success:function(data, textStatus)
		{
			$("#workdayforyearcheckout_grid_div").empty();
			$("#workdayforyearcheckout_grid_div").html(data);
		}
	});
}

function loadPageForWorkdayForAll(currentPage)
{
	var filterValue = $('#workdayforall_filterValueInput').val();
	var pageSize = $('#workdayforall_pageSizeSelect').find('option:selected').val();
	
	if(parseInt(currentPage) < 1)
	{
		currentPage = "1";
	}
	
	$.ajax({
		type: "GET",
		url:"loadWorkdayForAllPage.html",
		data:
		{	
			currentPage:currentPage,
			pageSize:pageSize,
			filterValue:filterValue,
			orderValue:$("#loadPageForWorkdayForAll_ordervalue").val()
		},
		dataType:"html",
		success:function(data, textStatus)
		{
			$("#workdayforall_grid_div").empty();
			$("#workdayforall_grid_div").html(data);
		}
	});
}
 
function loadPageForFoodForMonth(currentPage, type)
{
	var selectedBuildingsiteId = $("#foodformonth_buildingsiteselect").find("option:selected").val();
	
	if(selectedBuildingsiteId == "-1")
	{
		alert("请先选择【工地】");
		return;
	}
	
	var filterValue = $('#foodformonth_filterValueInput').val();
	var pageSize = $('#foodformonth_pageSizeSelect').find('option:selected').text();
	
	if(parseInt(currentPage) < 1)
	{
		currentPage = "1";
	}
	
	$.ajax({
		type: "GET",
		url:"loadFoodForMonthPage.html",
		data:
		{	
			type:type,
			currentPage:currentPage,
			pageSize:pageSize,
			filterValue:filterValue,
			orderValue:$("#loadPageForFoodForMonth_ordervalue").val(),
			buildingsiteid:selectedBuildingsiteId
		},
		dataType:"html",
		success:function(data, textStatus)
		{
			$("#foodformonth_grid_div").empty();
			$("#foodformonth_grid_div").html(data);
			
			setInputNum();
			$('.tipsybox').tipsy({gravity: 's',html: true});
		}
	});
}

function loadPageForFoodForAll(currentPage)
{
	var filterValue = $('#foodforall_filterValueInput').val();
	var pageSize = $('#foodformonth_pageSizeSelect').find('option:selected').text();
	
	if(parseInt(currentPage) < 1)
	{
		currentPage = "1";
	}
	
	$.ajax({
		type: "GET",
		url:"loadFoodForAllPage.html",
		data:
		{	
			currentPage:currentPage,
			pageSize:pageSize,
			filterValue:filterValue,
			orderValue:$("#loadPageForFoodForAll_ordervalue").val()
		},
		dataType:"html",
		success:function(data, textStatus)
		{
			$("#foodforall_grid_div").empty();
			$("#foodforall_grid_div").html(data);
		}
	});
}

function loadPageForPackagework(currentPage, showtype)
{
	var selectedBuildingsiteId = $("#packagework_buildingsiteselect").find("option:selected").val();
	
	if(selectedBuildingsiteId == "-1")
	{
		alert("请先选择【工地】");
		return;
	}
	
	var filterValue = $('#packagework_filterValueInput').val();
	var pageSize = $('#packagework_pageSizeSelect').find('option:selected').text();
	
	if(parseInt(currentPage) < 1)
	{
		currentPage = "1";
	}
	
	$.ajax({
		type: "GET",
		url:"loadPackageworkPage.html",
		data:
		{	
			type:showtype,
			currentPage:currentPage,
			pageSize:pageSize,
			filterValue:filterValue,
			orderValue:$("#loadPageForPackagework_ordervalue").val(),
			buildingsiteid:selectedBuildingsiteId
		},
		dataType:"html",
		success:function(data, textStatus)
		{
			$("#packagework_grid_div").empty();
			$("#packagework_grid_div").html(data);
		}
	});
}

function loadPageForPackageworkForAll(currentPage)
{
	var filterValue = $('#packageworkforall_filterValueInput').val();
	var pageSize = $('#packageworkforall_pageSizeSelect').find('option:selected').text();
	
	if(parseInt(currentPage) < 1)
	{
		currentPage = "1";
	}
	
	$.ajax({
		type: "GET",
		url:"loadPackageworkForAllPage.html",
		data:
		{	
			currentPage:currentPage,
			pageSize:pageSize,
			filterValue:filterValue,
			orderValue:$("#loadPageForPackageworkForAll_ordervalue").val()
		},
		dataType:"html",
		success:function(data, textStatus)
		{
			$("#packageworkforall_grid_div").empty();
			$("#packageworkforall_grid_div").html(data);
		}
	});
}

function loadPageForBuilder(currentPage)
{
	var filterValue = $('#filterValueInput').val();
	var pageSize = $('#pageSizeSelect').find('option:selected').val();
	var showtype = $("#buildershowtype").attr("checked") == "checked" ? "1" : "0";
	
	if(parseInt(currentPage) < 1)
	{
		currentPage = "1";
	}
	
	$.ajax({
		type: "GET",
		url:"loadBuilderPage.html",
		data:
		{	
			currentPage:currentPage,
			pageSize:pageSize,
			filterValue:filterValue,
			orderValue:$("#loadPageForBuilder_ordervalue").val(),
			showtype:showtype
		},
		dataType:"html",
		success:function(data, textStatus)
		{
			$("#buildercheckin_grid_div").empty();
			$("#buildercheckin_grid_div").html(data);
		}
	});
}

function loadPageForBuildingsite(currentPage)
{
	var filterValue = $('#filterValueInput').val();
	var pageSize = $('#pageSizeSelect').find('option:selected').val();
	var showtype = $("#buildingsiteshowtype").attr("checked") == "checked" ? "1" : "0";
	
	if(parseInt(currentPage) < 1)
	{
		currentPage = "1";
	}
	
	$.ajax({
		type: "GET",
		url:"loadBuildingsitePage.html",
		data:
		{	
			currentPage:currentPage,
			pageSize:pageSize,
			filterValue:filterValue,
			orderValue:$("#loadPageForBuildingsite_ordervalue").val(),
			showtype:showtype
		},
		dataType:"html",
		success:function(data, textStatus)
		{
			$("#buildingsitecheckin_grid_div").empty();
			$("#buildingsitecheckin_grid_div").html(data);
		}
	});
}

function loadPageForSalary(currentPage)
{
	var filterValue = $('#filterValueInput').val();
	var pageSize = $('#pageSizeSelect').find('option:selected').text();
	
	if(parseInt(currentPage) < 1)
	{
		currentPage = "1";
	}
	
	$.ajax({
		type: "GET",
		url:"loadSalaryPage.html",
		data:
		{	
			currentPage:currentPage,
			pageSize:pageSize,
			filterValue:filterValue,
			orderValue:$("#loadPageForSalary_ordervalue").val()
		},
		dataType:"html",
		success:function(data, textStatus)
		{
			$("#salarycheckin_grid_div").empty();
			$("#salarycheckin_grid_div").html(data);
		}
	});
}

function loadPageForDebt(currentPage)
{
	var filterValue = $('#filterValueInput').val();
	var pageSize = $('#pageSizeSelect').find('option:selected').text();
	
	if(parseInt(currentPage) < 1)
	{
		currentPage = "1";
	}
	
	$.ajax({
		type: "GET",
		url:"loadDebtPage.html",
		data:
		{	
			currentPage:currentPage,
			pageSize:pageSize,
			filterValue:filterValue,
			orderValue:$("#loadPageForDebt_ordervalue").val()
		},
		dataType:"html",
		success:function(data, textStatus)
		{
			$("#debtcheckin_grid_div").empty();
			$("#debtcheckin_grid_div").html(data);
		}
	});
}

function loadPage(currentPage, type, showtype)
{
	if(type == "loadPageForBuilder")
	{
		loadPageForBuilder(currentPage);
	}
	else if(type == "loadPageForBuildingsite")
	{
		loadPageForBuildingsite(currentPage);
	}
	else if(type == "loadPageForWorkday")
	{
		loadPageForWorkday(currentPage,showtype);
	}
	else if(type == "loadPageForWorkdayForYear")
	{
		loadPageForWorkdayForYear(currentPage);
	}
	else if(type == "loadPageForWorkdayForAll")
	{
		loadPageForWorkdayForAll(currentPage);
	}
	else if(type == "loadPageForSalary")
	{
		loadPageForSalary(currentPage);
	}
	else if(type == "loadPageForPackagework")
	{
		loadPageForPackagework(currentPage, showtype);
	}
	else if(type == "loadPageForPackageworkForAll")
	{
		loadPageForPackageworkForAll(currentPage);
	}
	else if(type == "loadPageForDebt")
	{
		loadPageForDebt(currentPage);
	}
	else if(type == "loadPageForFoodForMonth")
	{
		loadPageForFoodForMonth(currentPage, showtype);
	}
	else if(type == "loadPageForFoodForAll")
	{
		loadPageForFoodForAll(currentPage);
	}
	else if(type == "loadPageForDistributioninfo")
	{
		loadPageForDistributioninfo(currentPage);
	}
	else if(type == "loadPageForProject")
	{
		loadPageForProject(currentPage);
	}
}

function changeOrder(thisTag, currentPage, type, showtype)
{
	var columnName = $(thisTag).children("input").val();
	var thisTagClass = $(thisTag).children("i").attr("class");
	
	orderValue = (columnName + "_");
	
	$("#columnOrder").find("i").each(function(){
		$(this).attr("class","");		
	});
	
	if(thisTagClass == "")//不排序->升序
	{
		orderValue += "asc";
		thisTagClass = $(thisTag).children("i").attr("class", "icon-chevron-up");
	}
	else if(thisTagClass == "icon-chevron-up")//升序->降序
	{
		orderValue += "desc";
		thisTagClass = $(thisTag).children("i").attr("class", "icon-chevron-down");
	}
	else if(thisTagClass == "icon-chevron-down")//降序->不排序
	{
		orderValue = "";
		thisTagClass = $(thisTag).children("i").attr("class", "");
	}
	
	$("#" + type + "_ordervalue").val(orderValue);
	
	loadPage(currentPage, type, showtype);
}

function selectAllDisSelectAll(selectInput)
{
	if($(selectInput).attr("checked"))//选中
	{
		$("#tableContent").find("#inputSelect").each(function(){
			$(this).attr("checked",true);			
		});
	}
	else//未选中
	{
		$("#tableContent").find("#inputSelect").each(function(){
			$(this).attr("checked",false);			
		});
	}
}