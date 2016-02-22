function editPackagework(page)
{
	$.ajax({
		type: "POST",
		url:'changePackagework.html',
		dataType:"html",
		data:
		{
			builderid:$("#packagework_builderid").val(),
			buildingsiteid:$("#packagework_buildingsiteid").val(),
			packageworkendyear:$("#packagework_packageworkendyear").val(),
			packageworkendmonth:$("#packagework_packageworkendmonth").val(),
			packageworkmoney:$("#packagework_packageworkmoney").val(),
			otherinfo:$("#packagework_otherinfo").val()
		},
		success:function(data, textStatus)
		{
			if(data == "success")
			{
				loadPageForPackagework(page);
				closeWidget('editPackageworkWidget');
			}
			else if(data == "fail")
			{
			}
		}
	});
}

function getAllBuildingsitesForPackageworkSelect()
{
	$.ajax({
		type: "GET",
		url:'getAllBuildingsitesForPackagework.html',
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
			$("#packagework_buildingsiteselect").html(options);
		}
	});
}

function deleteEntryByIdsForPackagework(ids, removeURL, parameterName, buildingsiteId)
{
	if(ids == "")//多选
	{
		var selectedbuildingsiteText = $("#buildingsiteselect").find("option:selected").text();
		var selectedbuildingsiteVal = $("#buildingsiteselect").find("option:selected").val();
	
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
				window.location.href = removeURL + "?" + parameterName + "=" + tableIds + "&buildingsiteId=" + selectedbuildingsiteVal;
			}
		}
	}
	else//单个删除
	{
		if(confirm("确定要删除吗？一旦删除将不能恢复！"))
		{
			window.location.href = removeURL + "?" + parameterName + "=" + ids + "&buildingsiteId=" + buildingsiteId;
		}
	}
}

function getPackageworkByBuilderidAndBuildingsiteid(builderId, buildingsiteId)
{
	$.ajax({
		type: "GET",
		url:'getPackageworkByBuilderidAndBuildingsiteid.html',
		data:
		{	
			buildingsiteId:buildingsiteId,
			builderId:builderId
		},
		dataType:"json",
		success:function(data, textStatus)
		{
			$("#editPackageworkWidget").find("#packagework_builderid").val(data.builderid);
			$("#editPackageworkWidget").find("#packagework_buildingsiteid").val(data.buildingsiteid);
			$("#editPackageworkWidget").find("#packagework_packageworkendyear").val(data.packageworkendyear);
			$("#editPackageworkWidget").find("#packagework_packageworkendmonth").val(data.packageworkendmonth);
			$("#editPackageworkWidget").find("#packagework_packageworkmoney").val(data.packageworkmoney);
			$("#editPackageworkWidget").find("#packagework_otherinfo").val(data.otherinfo);
			
			openWidget("editPackageworkWidget");
		}
	});
}

function openWidgetChangePackagework()
{
	var selectedBuildingsiteIdVal = $("#packagework_buildingsiteselect").find("option:selected").val();
	var selectedBuildingsiteIdText = $("#packagework_buildingsiteselect").find("option:selected").text();
	
	if(selectedBuildingsiteIdVal != "-1")
	{
		$("#buildingsiteidInput").select2("data", {id: selectedBuildingsiteIdVal, text: selectedBuildingsiteIdText});
		$(".select2-choice").find("span").text(selectedBuildingsiteIdText);
	}
	else
	{
		$("#buildingsiteidInput").select2("val", "");
	}
	
	$("#builderidInput").select2("val", "");
	$("#packageworkendyearInput").val("");
	$("#packageworkendmonthInput").val("");
	$("#packageworkmoneyInput").val("");
	$("#packageworkmoneyTextarea").val("");
	
	openWidget('addPackageworkWidget');
}