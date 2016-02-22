var foodyear = "";
var foodmonth = "";
var builderid = ""
var buildingsiteid = "";
var foodmoney = "";

function showeditbox(idname)
{
	$("#"+idname).show();
}

function hideeditbox(idname)
{
	$("#"+idname).hide();
}

function showFoodDetailInfo(idname, builderid, foodyear, foodmonth, thisObj)
{
	$("#"+idname).hide();
	
	this.foodyear = foodyear;
	this.foodmonth = foodmonth;
	this.builderid = builderid;
	this.buildingsiteid = $("#foodformonth_buildingsiteselect").find("option:selected").val();
	
	dataInfo = {builderid:this.builderid,buildingsiteid:this.buildingsiteid,foodyear:foodyear,foodmonth:foodmonth};
	
	openWidget('changeFoodInfoWidget');
	$.ajax({
		type: "POST",
		url:"getFoodDetailInfo.html",
		dataType:"json",
		data:dataInfo,
		success:function(data, textStatus)
		{
			var json = eval(data);
			$("#otherinfoInput_info").val(data.otherinfo);
		}
	});
}

function editFoodDetailInfo(page)
{
	dataInfo = {
		builderid:this.builderid,
		buildingsiteid:$("#foodformonth_buildingsiteselect").find("option:selected").val(),
		foodyear:this.foodyear,
		foodmonth:this.foodmonth,
		foodmoney:"#",
		otherinfo:$("#otherinfoInput_info").val(),
	};
	
	$.ajax({
		type: "GET",
		url:"changeFood.html",
		dataType:"html",
		data:dataInfo,
		success:function(data, textStatus)
		{
			if(data == "success")
			{
				closeWidget('changeFoodInfoWidget');
				loadPageForFoodForMonth(page, 'checkin');
			}
			else if(data == "fail")
			{
			}
		}
	});
}

function getAllBuildingsitesForFoodSelect(type)
{
	$.ajax({
		type: "GET",
		url:'getAllBuildingsitesForFood.html',
		dataType:"json",
		success:function(data, textStatus)
		{
			if(data.buildingsiteArray[0].length > 0)
			{
				var options = "<option value='-1'>请选择</option>";
				for(var i = 0; i < data.buildingsiteArray[0].length; i++)
				{
					var name = data.buildingsiteArray[0][i].name;
					var id = data.buildingsiteArray[0][i].id;
					options += ("<option value='"+id+"'>"+name+"</option>");
				}
			}
			
			if(type == "formonth")
			{
				$("#foodformonth_buildingsiteselect").html(options);
			}
			else if(type == "forall")
			{
				$("#foodforall_buildingsiteselect").html(options);
			}
		}
	});
}

function openWidgetAddFood()
{
	var selectedBuildingsiteIdVal = $("#foodformonth_buildingsiteselect").find("option:selected").val();
	var selectedBuildingsiteIdText = $("#foodformonth_buildingsiteselect").find("option:selected").text();
	
	if(selectedBuildingsiteIdVal != "-1")
	{
		$("#buildingsiteidInput").select2("data", {id: selectedBuildingsiteIdVal, text: selectedBuildingsiteIdText});
		$(".select2-choice").find("span").text(selectedBuildingsiteIdText);
	}
	else
	{
		$("#buildingsiteidInput").select2("val", "");
	}
	
	$("#foodyearInput").val("");
	$("#foodmonthInput").val("");
	$("#builderidInput").select2("val", "");
	$("#foodmoneyInput").val("");
	$("#otherinfoInput").val("");
	
	openWidget('addFoodWidget');
}

function editFood(page)
{
	dataInfo = {
		builderid:$("#builderidInput").val(),
		buildingsiteid:$("#buildingsiteidInput").val(),
		foodyear:$("#foodyearInput").val(),
		foodmonth:$("#foodmonthInput").val(),
		foodmoney:$("#foodmoneyInput").val(),
		otherinfo:$("#otherinfoInput").val(),
	};
	
	$.ajax({
		type: "GET",
		url:"changeFood.html",
		dataType:"html",
		data:dataInfo,
		success:function(data, textStatus)
		{
			if(data == "success")
			{
				closeWidget('addFoodWidget');
				loadPageForFoodForMonth(page, 'checkin');
			}
			else if(data == "fail")
			{
			}
		}
	});
}

function changeFood(thisObj, type, page)
{
	if(isNaN(foodmoney))
	{
		thisObj.readOnly = true;
		return false;
	}
	
	dataInfo = {builderid:builderid,buildingsiteid:buildingsiteid,foodyear:foodyear,foodmonth:foodmonth,foodmoney:foodmoney};
	
	$.ajax({
		type: "GET",
		url:"changeFood.html",
		dataType:"html",
		data:dataInfo,
		success:function(data, textStatus)
		{
			if(data == "success")
			{
				loadPageForFoodForMonth(page, 'checkin');
			}
			else if(data == "fail")
			{
			}
		}
	});
	
	thisObj.readOnly = true;
}

function recordFoodmoney(thisObj)
{
	foodmoney = $(thisObj).val();
}

function recordFoodinfo(thisObj, foodyear, foodmonth, builderid)
{
	this.foodyear = foodyear;
	this.foodmonth = foodmonth;
	this.buildingsiteid = $("#foodformonth_buildingsiteselect").find("option:selected").val();
	this.builderid = builderid;
	
	thisObj.readOnly = false;
}
