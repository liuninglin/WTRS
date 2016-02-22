var accountUsernameFlag = true;
var accountPasswordFlag = true;

String.prototype.trim=function(){
　　    return this.replace(/(^\s*)|(\s*$)/g, "");
}

function checkusernameexist()
{
	var userid = $("#userid").val();
	var username = $("#username").val();
	$.ajax({
		type: "GET",
		url:"checkUsernameExist.html",
		data:{
			username:username,
			userid:userid
		},
		dataType:"html",
		success:function(data, textStatus)
		{
			if(data == "exist")
			{
				$("#usernameinfoicon").attr("class", "icon-remove");
				$("#usernameinfoicon").attr("style", "color:red");
				$("#usernameinfocontent").text("用户名已存在！");
				$("#usernameinfocontent").attr("style", "color:red");
				accountPasswordFlag = false;
			}
			else
			{
				$("#usernameinfoicon").attr("class", "icon-ok");
				$("#usernameinfoicon").attr("style", "color:green");
				$("#usernameinfocontent").text("用户名可以使用！");
				$("#usernameinfocontent").attr("style", "color:green");
				accountPasswordFlag = true;
			}
		}
	});
}

function checkpassword()
{
	var password = $("#password").val();
	var password2 = $("#password2").val();
	
	if(password.trim() == "" || password2.trim() == "")
	{
		$("#passwordinfoicon").attr("class", "icon-remove");
		$("#passwordinfoicon").attr("style", "color:red");
		$("#passwordinfocontent").text("密码不能为空！");
		$("#passwordinfocontent").attr("style", "color:red");
		
		accountPasswordFlag = false;
		return false;
	}
	
	if(password != password2)
	{
		$("#passwordinfoicon").attr("class", "icon-remove");
		$("#passwordinfoicon").attr("style", "color:red");
		$("#passwordinfocontent").text("密码不一致！");
		$("#passwordinfocontent").attr("style", "color:red");
		
		accountPasswordFlag = false;
	}
	else
	{
		$("#passwordinfoicon").attr("class", "");
		$("#passwordinfoicon").attr("style", "");
		$("#passwordinfocontent").text("");
		$("#passwordinfocontent").attr("style", "");
		
		accountPasswordFlag = true;
	}
}

function checkAccountForm()
{
	checkusernameexist();
	checkpassword();
	
	if(!accountPasswordFlag || !accountUsernameFlag)
	{
		openWidget("warninfoWidget");
		$("#warninfo").text("请将信息填写完整！");
		return false;
	}
}