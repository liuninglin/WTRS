function showAddProjectWidget()
{
	$("#project_add_name").val("");
	$("#project_add_shortname").val("");
	$("#project_add_starttime").val("");
	$("#project_add_endtime").val("");
	$("#project_add_otherinfo").val("");
	openWidget('addProjectWidget');
}

function addProject(originURI)
{
	var dataInfo = {
		name:$("#project_add_name").val(),
		starttime:$("#project_add_starttime").val(),
		endtime:$("#project_add_endtime").val(),
		otherinfo:$("#project_add_otherinfo").val()
		};
	
	$.ajax({
		type: "POST",
		url:"addProject.html",
		dataType:"html",
		data:dataInfo,
		success:function(data, textStatus)
		{
			if(data == "success")
			{
				closeWidget('addProjectWidget');
				window.location.reload();
			}
			else if(data == "fail")
			{
			}
		}
	});
}