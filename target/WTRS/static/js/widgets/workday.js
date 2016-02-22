var workyear = "";
var workmonth = "";
var builderid = ""
var buildingsiteid = "";
var workday = "";
var workcount = "";
function recordWorkinfo(thisObj, workday, builderid, flag)
{
	if(buildingsite_id != "" && buildingsite_id != "-1" && workyear_str != "" && workyear_str != "-1" && workmonth_str != "" && workmonth_str != "-1")
	{
		if(flag)
		{
			this.workday = workday;
			this.builderid = builderid;
			thisObj.readOnly = false;
		}
		else
		{
			var builderid = $("#builderidInput_checkin_" + builderid).val();
			if(builderid != "" && builderid != "-1")
			{
				this.workday = workday;
				thisObj.readOnly = false;
			}
			else
			{
				toastr.warning("请先选择当前工日对应的工人！", '无法编辑');
			}
		}
	}
	else
	{
		toastr.warning("请先输入工地、年、月信息！", '无法编辑');
		return false;
	}
}

function recordWorkcount(thisObj)
{
	workcount = $(thisObj).val();
}

function showeditbox(idname)
{
	$("#"+idname).show();
}

function hideeditbox(idname)
{
	$("#"+idname).hide();
}

function showWorkdayDetailInfo(idname, builderid, workday, thisObj)
{
	$("#"+idname).hide();
	
	var tmp = $("#"+idname).siblings("input").val();
	if($.trim(tmp) != "")
	{
		$("#buildinfoInput_info").val("");
		$("#otherinfoInput_info").val("");
		
		workyear = workyear_str;
		workmonth = workmonth_str;
		this.builderid = builderid;
		this.workday = workday;
		this.buildingsiteid = buildingsite_id;
		
		dataInfo = {builderid:builderid,buildingsiteid:buildingsiteid,workyear:workyear,workmonth:workmonth,workday:workday};
		
		openWidget('changeWorkdayInfoWidget');
		$.ajax({
			type: "POST",
			url:"getWorkdayDetailInfo.html",
			dataType:"json",
			data:dataInfo,
			success:function(data, textStatus)
			{
				var json = eval(data);
				$("#buildinfoInput_info").val(data.buildinfo);
				$("#otherinfoInput_info").val(data.otherinfo);
			}
		});
	}
}

function editWorkdayDetailInfo(page)
{
	dataInfo = {
		builderid:this.builderid,
		buildingsiteid:buildingsite_id,
		workyear:workyear_str,
		workmonth:workmonth_str,
		workday:this.workday,
		workcount:"#",
		otherinfo:$("#otherinfoInput_info").val(),
		buildinfo:$("#buildinfoInput_info").val()
	};
	
	$.ajax({
		type: "GET",
		url:"changeWorkday.html",
		dataType:"html",
		data:dataInfo,
		success:function(data, textStatus)
		{
			if(data == "success")
			{
				closeWidget('changeWorkdayInfoWidget');
				loadPageForWorkday(page, 'checkin');
			}
			else if(data == "fail")
			{
			}
		}
	});
	
	$("#"+this.builderid+"_"+this.workday).siblings("input").attr("title", "工作说明："+$("#buildinfoInput_info").val()+"<br/>其他："+$("#otherinfoInput_info").val())
}

function editWorkday(page)
{
	dataInfo = {
		builderid:$("#builderidInput").val(),
		buildingsiteid:$("#buildingsiteidInput").val(),
		workyear:$("#workyearInput").val(),
		workmonth:$("#workmonthInput").val(),
		workday:$("#workdayInput").val(),
		workcount:$("#workcountInput").val(),
		otherinfo:$("#otherinfoInput").val(),
		buildinfo:$("#buildinfoInput").val()
	};
	
	$.ajax({
		type: "GET",
		url:"changeWorkday.html",
		dataType:"html",
		data:dataInfo,
		success:function(data, textStatus)
		{
			if(data == "success")
			{
				closeWidget('addWorkdayWidget');
				loadPageForWorkday(page, 'checkin');
			}
			else if(data == "fail")
			{
			}
		}
	});
}

function changeWorkday(thisObj, type, page, flag)
{
	if(isNaN(workcount))
	{
		thisObj.readOnly = true;
		return false;
	}
	
	if(flag)
	{
		dataInfo = {builderid:builderid,buildingsiteid:buildingsite_id,workyear:workyear_str,workmonth:workmonth_str,workday:workday,workcount:workcount};
	}
	else
	{
		if(buildingsite_id != "" && buildingsite_id != "-1" && workyear_str != "" && workyear_str != "-1" && workmonth_str != "" && workmonth_str != "-1" && workday != "" && workday != "-1" && workcount != "" && workcount != "0")
		{
			dataInfo = {builderid:builder_id,buildingsiteid:buildingsite_id,workyear:workyear_str,workmonth:workmonth_str,workday:workday,workcount:workcount};
		}
		else
		{
			thisObj.readOnly = true;
			return false;
		}
	}
	
	$.ajax({
		type: "GET",
		url:"changeWorkday.html",
		dataType:"html",
		data:dataInfo,
		success:function(data, textStatus)
		{
			if(data == "success")
			{
				//loadPage('1', 'loadPageForWorkday', 'checkin');
				refreshData();
			}
			else if(data == "fail")
			{
			}
		}
	});
	
	thisObj.readOnly = true;
}

function changeWorkyear(thisSelect)
{
	var selectedworkyear = $(thisSelect).find("option:selected").text();
	var selectedworkyearvalue = $(thisSelect).find("option:selected").val();
	var selectedBuildingsiteId = $("#workday_buildingsiteselect").find("option:selected").val();
	
	if(selectedworkyearvalue == '-1' || selectedBuildingsiteId == '-1')
	{
		return false;
	}
	
	$.ajax({
		type: "GET",
		url:'getWorkmonthListByBuildingsiteidAndWorkyear.html',
		dataType:"json",
		data:
		{
			buildingsiteid:selectedBuildingsiteId,
			workyear:selectedworkyear
		},
		success:function(data, textStatus)
		{
			if(data.workmonthArray[0].length > 0)
			{
				var options = "<option value='-1'>请选择</option>";
				for(var i = 0; i < data.workmonthArray[0].length; i++)
				{
					var value = data.workmonthArray[0][i];
					options += ("<option value='"+value+"'>"+value+"</option>");
				}
			}
			$("#workday_monthselect").html(options);
		}
	});
}

function getAllBuildingsitesForWorkdaySelect(type)
{
	$.ajax({
		type: "GET",
		url:'getAllBuildingsitesForWorkday.html',
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
				$("#workday_buildingsiteselect").html(options);
			}
			else if(type == "foryear")
			{
				$("#workdayforyear_buildingsiteselect").html(options);
			}
		}
	});
}

function openWidgetAddWorkday()
{
	var selectedworkyearText = $("#workday_yearselect").find("option:selected").text();
	var selectedworkyearVal = $("#workday_yearselect").find("option:selected").val();
	var selectedworkmonthText = $("#workday_monthselect").find("option:selected").text();
	var selectedworkmonthVal = $("#workday_monthselect").find("option:selected").val();
	var selectedBuildingsiteIdVal = $("#workday_buildingsiteselect").find("option:selected").val();
	var selectedBuildingsiteIdText = $("#workday_buildingsiteselect").find("option:selected").text();
	
	if(selectedworkyearVal != "-1" && selectedworkmonthVal != "-1" && selectedBuildingsiteIdVal != "-1")
	{
		$("#buildingsiteidInput").select2("data", {id: selectedBuildingsiteIdVal, text: selectedBuildingsiteIdText});
		$(".select2-choice").find("span").text(selectedBuildingsiteIdText);
		
		$("#workyearInput").val(selectedworkyearText);
		$("#workmonthInput").val(selectedworkmonthText);
	}
	else
	{
		$("#buildingsiteidInput").select2("val", "");
		$("#workyearInput").val("");
		$("#workmonthInput").val("");
	}
	
	$("#builderidInput").select2("val", "");
	$("#workdayInput").val("");
	$("#workcountInput").val("");
	$("#buildinfoInput").val("");
	$("#otherinfoInput").val("");
	
	openWidget('addWorkdayWidget');
}

function addBuildingsite(name)
{
	var result = "";
	$.ajax({
		type: "POST",
		url:'addBuildingsiteReturnBuildingsiteId.html',
		dataType:"json",
		async:false,
		data:{
			name:name
		},
		success:function(data, textStatus)
		{
			if(data != "-1")
			{
				result = data;
			}	
		}
	});
	
	return result;
}

function addBuilder(name)
{
	var result = "";
	$.ajax({
		type: "POST",
		url:'addBuilderReturnBuilderId.html',
		dataType:"json",
		async:false,
		data:{
			name:name
		},
		success:function(data, textStatus)
		{
			if(data != "-1")
			{
				result = data;
			}	
		}
	});
	
	return result;
}


function loadWorkdayForCheckin()
{
	if(buildingsite_id != "" && buildingsite_id != "-1" && workyear_str != "" && workyear_str != "-1" && workmonth_str != "" && workmonth_str != "-1")
	{
		$.ajax({
			type: "POST",
			url:'loadWorkdayPage.html',
			dataType:"html",
			async:true,
			data:{
				buildingsiteid:buildingsite_id,
				workyear:workyear_str,
				workmonth:workmonth_str,
				showType:"checkin",
				currentPage:"1",
				pageSize:"-1",
				filterValue:"",
				orderValue:""
			},
			success:function(data, textStatus)
			{
				$("#workdaycheckin_grid_div").html(data);
				
				$(".builderidInput_checkin").select2("enable");
				
				refreshData();
				$("#workdaycheckin_rightDiv_middleDiv").scroll(function(){
					var rightDiv_middleDiv_topLength = $("#workdaycheckin_rightDiv_middleDiv").scrollTop();
					$("#workdaycheckin_leftDiv_middleDiv").scrollTop(rightDiv_middleDiv_topLength);
					$("#workdaycheckin_middleDiv_middleDiv").scrollTop(rightDiv_middleDiv_topLength);
				});	
				
				setInputNum();
				$('.tipsybox').tipsy({gravity: 's',html: true});
			}
		});
	}
}

function refreshData()
{
	for(var i = 0; i < $("#workdaycheckin_middleDiv_middleDiv").find("table tr").length; i++)
	{
		var tr_totalCount = 0;
		$("#workdaycheckin_middleDiv_middleDiv").find("table tr:eq("+i+")").find("td input").each(function(){
			var temp = $(this).val();
			if($.trim(temp) == "")
			{
				temp = "0";
			}
			tr_totalCount = tr_totalCount*1000000000000 + (parseFloat(temp))*1000000000000;
			tr_totalCount = tr_totalCount/1000000000000;
		});
		
		$("#workdaycheckin_rightDiv_middleDiv").find("table tr:eq("+i+")").find("td").html(tr_totalCount);
	}
	
	
	for(var i = 1; i <= 32; i++)
	{
		var totalCount = 0;
		$(".columnworkday"+i).each(function()
		{
			if(i == 32)
			{
				var htmlTemp = $(this).html();
				if($.trim(htmlTemp) == "")
				{
					htmlTemp = "0";
				}
				
				totalCount = totalCount*1000000000000 + (parseFloat(htmlTemp))*1000000000000;
			}
			else
			{
				var valTemp = $(this).val();
				if($.trim(valTemp) == "")
				{
					valTemp = "0";
				}
				
				totalCount = totalCount*1000000000000 + (parseFloat(valTemp))*1000000000000;
			}
			totalCount = totalCount/1000000000000;
		});
		
		if(totalCount != 0)
		{
			$("#totalColumnworkday"+i).html(totalCount);
		}
	}
}

			function resultFormatSelectionForBuilderForWorkdayCheckin(item, container, escapeMarkup) { 
				if(item.id == "-1")
				{
					var id = addBuilder(builder_filterStr);
					builder_id = id;
					builder_name = builder_filterStr;
					$("#"+container.prevObject.prevObject[0].nextElementSibling.id).val(id);
					var itemFmt = builder_filterStr + "";
				}
				else
				{
					builder_id = item.id;
					builder_name = item.name;
					var itemFmt = item.name + "";
				}
				
				return itemFmt;  
    		}  
			function formatAsTextForBuilderForWorkdayCheckin(item){  
				if(item.id == "-1")
				{
					var itemFmt ="<div style='display:inline;'>点击创建新工人："+builder_filterStr+"</div><div style='float:right;color:#4F4F4F;display:inline'></div>";	
				}
				else
				{
					var itemFmt ="<div style='display:inline;'>"+item.name+"</div><div style='float:right;color:#4F4F4F;display:inline'></div>";
				}
				
			    return itemFmt;  
			} 

			
function refreshBuilderForWorkdayCheckin(flag)
{
			$(".builderidInput_checkin").select2({
				placeholder:"工人",//文本框的提示信息  
			    minimumInputLength:1,   //至少输入n个字符，才去加载数据  
			    allowClear: true,   //是否允许用户清除文本信息  
			    ajax:{  
			        url:'getBuilderListByFilterValueForTimeworkForWorkdayCheckin.html',    //地址  
			        dataType:'json',    //接收的数据类型  
			        data: function (term, pageNo) {     //在查询时向服务器端传输的数据  
			            term = $.trim(term);  
			        	builder_filterStr = term;
			                    return {  
			                         filterValue: encodeURI(term),
			                         buildingsiteid:buildingsite_id,
			                         workyear:workyear_str,
			                         workmonth:workmonth_str
			                     }  
			        },  
			        results:function(data,pageNo){
			        	if(data.builderArray[0].length > 0)
						{
			        		return{
			        			results:data.builderArray[0]
			        		};
						}
			        	else
			        	{
			        		return{
			        			results:data
			        		};
			        	}
			        }  
			    },
			    initSelection:function(element,callback){           //初始化，其中doName是自定义的一个属性，用来存放text的值  
               		var id=$(element).val();  
			    	$.ajax({
						type: "POST",
						url:'getBuilderByBuilderId.html',
						dataType:"json",
						data:{
							builderId:id
						},
						success:function(data, textStatus)
						{
							var result = eval(data);
							callback({id:id,name:result.name[0]});  
						}
					});
        		},  
        		formatSelection: resultFormatSelectionForBuilderForWorkdayCheckin,
    			formatResult: formatAsTextForBuilderForWorkdayCheckin  //渲染查询结果项 
			});
	
	if(flag)
	{
		$(".builderidInput_checkin").select2("disable");
	}
	else
	{
		$(".builderidInput_checkin").select2("enable");
	}
}
			

function addTrForWorkdayCheckin()
{
	var j = 0;
	var temp = $("#numberForBuilderTr").val();
	if($.trim(temp) == "")
	{
		j = 1;
	}
	else
	{
		j = parseInt(temp);
	}
	
	for(var numberTr = 1; numberTr <= j; numberTr++)
	{
		$("#workdaycheckin_leftDiv_middleDiv_table").append('<tr style="height: 45px;"><td class="center " style="width: 30%;text-align: center;">'+number_index+'</td><td class="center " style="width: 70%;text-align: center;" ><input type="text" id="builderidInput_checkin_'+number_index+'" class="builderidInput_checkin" style="width: 120px;"></td></tr>')
		
		$("#workdaycheckin_middleDiv_middleDiv").find("tbody").append('<tr style="height: 45px;"></tr>');
		for(var i = 1; i <= 31; i++)
		{
			$("#workdaycheckin_middleDiv_middleDiv").find("tbody tr:last").append('<td style="width: 2.9%;"><input class="columnworkday'+i+' mask-num" onblur="changeWorkday(this, \'add\', \'1\', false)"  onkeyup="recordWorkcount(this)" onclick="recordWorkinfo(this, \''+i+'\', \''+number_index+'\', false)" type="text" value="" readonly="readonly" style="width:25px;text-align: center;border: none;"></td>');
		}
		
		$("#workdaycheckin_rightDiv_middleDiv").find("tbody").append('<tr height="45px;"><td class="columnworkday32 w_bold"></td></tr>');
		
		number_index++;
	}
	
	
	if(buildingsite_id != "" && buildingsite_id != "-1" && workyear_str != "" && workyear_str != "-1" && workmonth_str != "" && workmonth_str != "-1")
	{
		refreshBuilderForWorkdayCheckin(false);
	}
	else
	{
		refreshBuilderForWorkdayCheckin(true);
	}
	refreshData();
}