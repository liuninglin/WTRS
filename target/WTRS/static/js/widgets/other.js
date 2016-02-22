function getAnalyseInfo()
{
	$.ajax({
		type: "GET",
		url:"getAnalyseInfo.html",
		dataType:"html",
		success:function(data, textStatus)
		{
			var json = eval("("+data+")");
			$("#buildernum").text(json.buildernum);
			$("#buildingsitenum").text(json.buildingsitenum);
			
			if(json.allmoney == null)
			{
				$("#allmoney").html("---");
			}
			else
			{
				$("#allmoney").html("&yen;"+json.allmoney);
			}
		}
	});
}