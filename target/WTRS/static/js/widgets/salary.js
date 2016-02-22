function getSalaryByBuilderId(builderId)
{
	$.ajax({
		type: "GET",
		url:'getSalaryByBuilderId.html',
		data:
		{	
			builderId:builderId
		},
		dataType:"json",
		success:function(data, textStatus)
		{
			$("#editSalaryWidget").find("#salary_builderid").val(data.builderid);
			$("#editSalaryWidget").find("#salary_salarymoney").val(data.salarymoney);
			$("#editSalaryWidget").find("#salary_otherinfo").val(data.otherinfo);
			
			openWidget("editSalaryWidget");
		}
	});
}