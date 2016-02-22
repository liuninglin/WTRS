function setInputNum_year(){
     $(".mask-num_year").keydown(function(event){
        if (navigator.userAgent.indexOf("MSIE")>0) {
               if ( ((event.keyCode > 47) && (event.keyCode < 58)) || ((event.keyCode >= 96) && (event.keyCode <= 105))
                         || (event.keyCode == 8) || (event.keyCode == 86) || (event.keyCode == 67) || (event.keyCode == 13)
                         || (event.keyCode == 27) || (event.keyCode == 45) || (event.ctrlKey && event.keyCode == 13) || (event.keyCode == 9)
                         || (event.keyCode == 37) || (event.keyCode == 38) || (event.keyCode == 39) || (event.keyCode == 40)) {
                      return true;
                } else {
                      return false;
               }
         } else {
            if ( ((event.which > 47) && (event.which < 58)) || ((event.keyCode >= 96) && (event.keyCode <= 105))
                      || (event.which == 8) || (event.which == 86) || (event.keyCode == 67) || (event.keyCode == 13)
                      || (event.keyCode == 27) || (event.keyCode == 45) || (event.ctrlKey && event.keyCode == 13) || (event.keyCode == 9)
                      || (event.keyCode == 37) || (event.keyCode == 38) || (event.keyCode == 39) || (event.keyCode == 40)) {
                     return true;
             } else {
                     return false;
             }
         }
    }).bind("blur", function() {
        if (!/\d+/.test(this.value) || this.value.indexOf(' ') >= 0 || 
        		(((this.value < new Date().getFullYear() -2 || this.value > new Date().getFullYear())) 
        	)) {
            this.value = "";
        }
    }).bind("input", function(e) {
        if (isNaN(this.value) || this.value.indexOf(' ') >= 0)
            this.value = this.value.replace(/\D/g, "");
    }).bind("propertychange", function(e) {
        if (isNaN(this.value) || this.value.indexOf(' ') >= 0)
            this.value = this.value.replace(/\D/g, "");
    }).focus(function() {
        this.style.imeMode='disabled';
    }).bind("dragenter",function(){
        return false;
    }).keyup(function(event){
        if(isNaN($(this).val()) || this.value.indexOf(' ') >= 0){
             $(this).val("");
              return;
        }
     });
}

function setInputNum_month(){
    $(".mask-num_month").keydown(function(event){
       if (navigator.userAgent.indexOf("MSIE")>0) {
              if ( ((event.keyCode > 47) && (event.keyCode < 58)) || ((event.keyCode >= 96) && (event.keyCode <= 105))
                        || (event.keyCode == 8) || (event.keyCode == 86) || (event.keyCode == 67) || (event.keyCode == 13)
                        || (event.keyCode == 27) || (event.keyCode == 45) || (event.ctrlKey && event.keyCode == 13) || (event.keyCode == 9)
                        || (event.keyCode == 37) || (event.keyCode == 38) || (event.keyCode == 39) || (event.keyCode == 40)) {
                     return true;
               } else {
                     return false;
              }
        } else {
           if ( ((event.which > 47) && (event.which < 58)) || ((event.keyCode >= 96) && (event.keyCode <= 105))
                     || (event.which == 8) || (event.which == 86) || (event.keyCode == 67) || (event.keyCode == 13)
                     || (event.keyCode == 27) || (event.keyCode == 45) || (event.ctrlKey && event.keyCode == 13) || (event.keyCode == 9)
                     || (event.keyCode == 37) || (event.keyCode == 38) || (event.keyCode == 39) || (event.keyCode == 40)) {
                    return true;
            } else {
                    return false;
            }
        }
   }).bind("blur", function() {
       if (!/\d+/.test(this.value) || this.value.indexOf(' ') >= 0 || 
       		(((this.value < 1 || this.value > 12)) 
       	)) {
           this.value = "";
       }
       else
       {
    	   if(this.value.length == 1)
    	   {
    		   var temp = this.value;
    		   this.value = "0"+temp;
    	   }
       }
   }).bind("input", function(e) {
       if (isNaN(this.value) || this.value.indexOf(' ') >= 0)
           this.value = this.value.replace(/\D/g, "");
   }).bind("propertychange", function(e) {
       if (isNaN(this.value) || this.value.indexOf(' ') >= 0)
           this.value = this.value.replace(/\D/g, "");
   }).focus(function() {
       this.style.imeMode='disabled';
   }).bind("dragenter",function(){
       return false;
   }).keyup(function(event){
       if(isNaN($(this).val()) || this.value.indexOf(' ') >= 0){
            $(this).val("");
             return;
       }
    });
}

function setInputNum_day(){
    $(".mask-num_day").keydown(function(event){
       if (navigator.userAgent.indexOf("MSIE")>0) {
              if ( ((event.keyCode > 47) && (event.keyCode < 58)) || ((event.keyCode >= 96) && (event.keyCode <= 105))
                        || (event.keyCode == 8) || (event.keyCode == 86) || (event.keyCode == 67) || (event.keyCode == 13)
                        || (event.keyCode == 27) || (event.keyCode == 45) || (event.ctrlKey && event.keyCode == 13) || (event.keyCode == 9)
                        || (event.keyCode == 37) || (event.keyCode == 38) || (event.keyCode == 39) || (event.keyCode == 40)) {
                     return true;
               } else {
                     return false;
              }
        } else {
           if ( ((event.which > 47) && (event.which < 58)) || ((event.keyCode >= 96) && (event.keyCode <= 105))
                     || (event.which == 8) || (event.which == 86) || (event.keyCode == 67) || (event.keyCode == 13)
                     || (event.keyCode == 27) || (event.keyCode == 45) || (event.ctrlKey && event.keyCode == 13) || (event.keyCode == 9)
                     || (event.keyCode == 37) || (event.keyCode == 38) || (event.keyCode == 39) || (event.keyCode == 40)) {
                    return true;
            } else {
                    return false;
            }
        }
   }).bind("blur", function() {
       if (!/\d+/.test(this.value) || this.value.indexOf(' ') >= 0 || 
       		(((this.value < 1 || this.value > 31)) 
       	)) {
           this.value = "";
       }
       else
       {
    	   if(this.value.length == 1)
    	   {
    		   var temp = this.value;
    		   this.value = "0"+temp;
    	   }
       }
   }).bind("input", function(e) {
       if (isNaN(this.value) || this.value.indexOf(' ') >= 0)
           this.value = this.value.replace(/\D/g, "");
   }).bind("propertychange", function(e) {
       if (isNaN(this.value) || this.value.indexOf(' ') >= 0)
           this.value = this.value.replace(/\D/g, "");
   }).focus(function() {
       this.style.imeMode='disabled';
   }).bind("dragenter",function(){
       return false;
   }).keyup(function(event){
       if(isNaN($(this).val()) || this.value.indexOf(' ') >= 0){
            $(this).val("");
             return;
       }
    });
}

//只可输入数字,小数点
function setInputNum(){
     $(".mask-num").keydown(function(event){
        if (navigator.userAgent.indexOf("MSIE")>0) {
               if ( ((event.keyCode > 47) && (event.keyCode < 58)) || ((event.keyCode >= 96) && (event.keyCode <= 105))
                         || (event.keyCode == 8) || (event.keyCode == 86) || (event.keyCode == 67) || (event.keyCode == 13)
                         || (event.keyCode == 27) || (event.keyCode == 45) || (event.ctrlKey && event.keyCode == 13) || (event.keyCode == 9)
                         || (event.keyCode == 37) || (event.keyCode == 38) || (event.keyCode == 39) || (event.keyCode == 40)
                         || (event.keyCode == 46) || (event.keyCode == 190)) {
                      return true;
                } else {
                      return false;
               }
         } else {
            if ( ((event.which > 47) && (event.which < 58)) || ((event.keyCode >= 96) && (event.keyCode <= 105))
                      || (event.which == 8) || (event.which == 86) || (event.keyCode == 67) || (event.keyCode == 13)
                      || (event.keyCode == 27) || (event.keyCode == 45) || (event.ctrlKey && event.keyCode == 13) || (event.keyCode == 9)
                      || (event.keyCode == 37) || (event.keyCode == 38) || (event.keyCode == 39) || (event.keyCode == 40)
                      || (event.keyCode == 46) || (event.keyCode == 190)) {
                     return true;
             } else {
                     return false;
             }
         }
    }).bind("blur", function() {
        if (!/\d+/.test(this.value) || this.value.indexOf(' ') >= 0) {
            this.value = "";
        }
    }).bind("input", function(e) {
        if (isNaN(this.value) || this.value.indexOf(' ') >= 0)
            this.value = this.value.replace(/\D/g, "");
    }).bind("propertychange", function(e) {
        if (isNaN(this.value) || this.value.indexOf(' ') >= 0)
            this.value = this.value.replace(/\D/g, "");
    }).focus(function() {
        this.style.imeMode='disabled';
    }).bind("dragenter",function(){
        return false;
    }).keyup(function(event){
        if(isNaN($(this).val()) || this.value.indexOf(' ') >= 0){
             $(this).val("");
              return;
        }
     });
}

function getProjectByProjectId(projectId)
{
	$.ajax({
		type: "GET",
		url:'getProjectByProjectId.html',
		data:
		{	
			projectId:projectId
		},
		dataType:"json",
		success:function(data, textStatus)
		{
			$("#editProjectWidget").find("#project_edit_id").val(data.id);
			$("#editProjectWidget").find("#project_edit_name").val(data.name);
			$("#editProjectWidget").find("#project_edit_shortname").val(data.shortname);
			$("#editProjectWidget").find("#project_edit_starttime").val(data.starttime);
			$("#editProjectWidget").find("#project_edit_endtime").val(data.endtime);
			$("#editProjectWidget").find("#project_edit_otherinfo").val(data.otherinfo);
			if(data.status == "1")
			{
				$("#editProjectWidget").find("#project_edit_status_1").attr("checked","checked");
			}
			else if(data.status == "0")
			{
				$("#editProjectWidget").find("#project_edit_status_0").attr("checked","checked");
			}
			
			openWidget("editProjectWidget");
		}
	});
}

function getBuilderByBuilderId(builderId)
{
	$.ajax({
		type: "GET",
		url:'getBuilderByBuilderId.html',
		data:
		{	
			builderId:builderId
		},
		dataType:"json",
		success:function(data, textStatus)
		{
			$("#editBuilderWidget").find("#builder_id").val(data.id);
			$("#editBuilderWidget").find("#builder_name").val(data.name);
			$("#editBuilderWidget").find("#builder_shortname").val(data.shortname);
			$("#editBuilderWidget").find("#builder_age").val(data.age);
			if(data.sex == "male")
			{
				$("#editBuilderWidget").find("#builder_sex_male").attr("checked","checked");
			}
			else if(data.sex == "female")
			{
				$("#editBuilderWidget").find("#builder_sex_female").attr("checked","checked");
			}
			
			if(data.type == "timework")
			{
				$("#editBuilderWidget").find("#builder_type_timework").attr("checked","checked");
			}
			else if(data.type == "packagework")
			{
				$("#editBuilderWidget").find("#builder_type_packagework").attr("checked","checked");
			}
			else if(data.type == "all")
			{
				$("#editBuilderWidget").find("#builder_type_all").attr("checked","checked");
			}
			$("#editBuilderWidget").find("#builder_idcard").val(data.idcard);
			$("#editBuilderWidget").find("#builder_hometown").val(data.hometown);
			$("#editBuilderWidget").find("#builder_otherinfo").val(data.otherinfo);
			
			openWidget("editBuilderWidget");
		}
	});
}

function getBuildingsiteByBuildingsiteId(buildingsiteId)
{
	$.ajax({
		type: "GET",
		url:'getBuildingsiteByBuildingsiteId.html',
		data:
		{	
			buildingsiteId:buildingsiteId
		},
		dataType:"json",
		success:function(data, textStatus)
		{
			$("#editBuildingsiteWidget").find("#buildingsite_id").val(data.id);
			$("#editBuildingsiteWidget").find("#buildingstie_name").val(data.name);
			$("#editBuildingsiteWidget").find("#buildingstie_shortname").val(data.shortname);
			$("#editBuildingsiteWidget").find("#buildingsite_address").val(data.address);
			$("#editBuildingsiteWidget").find("#buildingsite_starttime").val(data.starttime);
			$("#editBuildingsiteWidget").find("#buildingsite_endtime").val(data.endtime);
			$("#editBuildingsiteWidget").find("#buildingsite_otherinfo").val(data.otherinfo);
			
			openWidget("editBuildingsiteWidget");
		}
	});
}

function reStartEntryByIds(ids, reStartURL, parameterName)
{
	if(ids == "")//多选
	{
		var tableIds = "";
		$("#tableContent").find("#inputSelect").each(function(){
			if($(this).attr("checked"))
			{
				var tableId = $(this).attr("tableid");
				tableIds += (tableId + ",");
			}
		});
		tableIds = tableIds.substr(0,tableIds.length-1);
		if(tableIds != "")
		{
				window.location.href = reStartURL + "?" + parameterName + "=" + tableIds;
		}
	}
	else//单个删除
	{
			window.location.href = reStartURL + "?" + parameterName + "=" + ids;
	}
}

function deleteEntryByIds(ids, removeURL, parameterName)
{
	if(ids == "")//多选
	{
		var tableIds = "";
		$("#tableContent").find("#inputSelect").each(function(){
			if($(this).attr("checked"))
			{
				var tableId = $(this).attr("tableid");
				tableIds += (tableId + ",");
			}
		});
		tableIds = tableIds.substr(0,tableIds.length-1);
		if(tableIds != "")
		{
			if(confirm("确定要删除吗？一旦删除将不能恢复！"))
			{
				window.location.href = removeURL + "?" + parameterName + "=" + tableIds;
			}
		}
	}
	else//单个删除
	{
		if(confirm("确定要删除吗？一旦删除将不能恢复！"))
		{
			window.location.href = removeURL + "?" + parameterName + "=" + ids;
		}
	}
}

function changeBuildingsite(thisSelect)
{
	var selectedBuildingsiteName = $(thisSelect).find("option:selected").text();
	var selectedBuildingsiteId = $(thisSelect).find("option:selected").val();
	
	if(selectedBuildingsiteId == "-1")
	{
		return false;
	}
	
	$.ajax({
		type: "GET",
		url:'getWorkyearListByBuildingsiteid.html',
		dataType:"json",
		data:
		{
			buildingsiteid:selectedBuildingsiteId
		},
		success:function(data, textStatus)
		{
			if(data.workyearArray[0].length > 0)
			{
				var options = "<option value='-1'>请选择</option>";
				for(var i = 0; i < data.workyearArray[0].length; i++)
				{
					var value = data.workyearArray[0][i];
					options += ("<option value='"+value+"'>"+value+"</option>");
				}
			}
			$("#workday_yearselect").html(options);
		}
	});
}



function showBuildernameDiv(inputObj)
{
	$("#buildernamehiddendiv").show();
	
	var inputName = $(inputObj).val();
	if(inputName != "")
	{
		$.ajax({
		type: "GET",
		url:'getBuilderListByFilterValue.html',
		dataType:"json",
		data:
		{
			filterValue:inputName
		},
		success:function(data, textStatus)
		{
			if(data.builderArray[0].length > 0)
			{
				var options = "";
				for(var i = 0; i < data.builderArray[0].length; i++)
				{
					var builderObj = data.builderArray[0][i];
					options += ("<option onclick='changeBuilder(this)' value='"+builderObj.id+"'>"+builderObj.name+"</option>");
				}
				$("#buildernamehiddendiv").empty();
				$("#buildernamehiddendiv").html(options);
			}
		}
	});
	}
}

function hideBuildernameDiv()
{
	$("#buildernamehiddendiv").hide();
}

function queryBuilderByBuildername(inputObj)
{
	var inputName = $(inputObj).val();
	
	$.ajax({
		type: "GET",
		url:'getBuilderListByFilterValue.html',
		dataType:"json",
		data:
		{
			filterValue:inputName
		},
		success:function(data, textStatus)
		{
			if(data.builderArray[0].length > 0)
			{
				var options = "";
				for(var i = 0; i < data.builderArray[0].length; i++)
				{
					var builderObj = data.builderArray[0][i];
					options += ("<option onclick='changeBuilder(this)' value='"+builderObj.id+"'>"+builderObj.name+"</option>");
				}
				$("#buildernamehiddendiv").empty();
				$("#buildernamehiddendiv").html(options);
			}
		}
	});
	
}

function changeBuilder(thisObj)
{
	var builderid = $(thisObj).val();
	var buildername = $(thisObj).text();
	
	alert(builderid + ":" + buildername);
}

//将form转为AJAX提交
function ajaxSubmit(frm, fn) {
    var dataPara = getFormJson(frm);
    $.ajax({
        url: frm.action,
        type: frm.method,
        data: dataPara,
        success: fn
    });
}

//将form中的值转换为键值对。
function getFormJson(frm) {
    var o = {};
    var a = $(frm).serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });

    return o;
}







