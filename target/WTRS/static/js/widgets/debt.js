function getDebtByBuilderId(builderId)
{
	$.ajax({
		type: "GET",
		url:'getDebtByBuilderId.html',
		data:
		{	
			builderId:builderId
		},
		dataType:"json",
		success:function(data, textStatus)
		{
			$("#editDebtWidget").find("#debt_builderid").val(data.builderid);
			$("#editDebtWidget").find("#debt_debtmoney").val(data.debtmoney);
			$("#editDebtWidget").find("#debt_debtdate").val(data.debtdate);
			$("#editDebtWidget").find("#debt_otherinfo").val(data.otherinfo);
			
			openWidget("editDebtWidget");
		}
	});
}