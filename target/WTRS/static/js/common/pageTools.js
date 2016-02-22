function openWidget(divName)
{
	$("#" + divName).modal({keyboard:true});
}

function closeWidget(divName)
{
	$("#" + divName).modal('hide')
}

function changePage(thisObj)
{
	if($(thisObj).attr("class") == "icon-chevron-right")
	{
		$("#leftwidget").animate({opacity: "show"}, "slow");
		$(thisObj).attr("class", "icon-chevron-left");
		$("#rightwidget").attr("class","span9");
	}
	else if($(thisObj).attr("class") == "icon-chevron-left")
	{
		//$("#leftwidget").animate({opacity: "hide"}, "slow");
		$("#leftwidget").hide();
		$(thisObj).attr("class", "icon-chevron-right");
		$("#rightwidget").attr("class","span12");
	}
}