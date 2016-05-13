/**
 * 用户登录js验证
 */
$(document).ready(function(){
	$(".btn-submit").click(function(){
		
		var uname = $("#uname");
		var upwd =$("#upwd");
		var role =$("#role");
		var returnURL = $("#return_uri");
		var link_pwd = $("#link-pwd");
		var alert_warning = $("#alert_warning");
		
		if(uname.val() ==""){
			uname.focus();
			return false;
		}
		if(upwd.val() ==""){
			upwd.focus();
			return false;
		}else{
			var pwd = $.md5(upwd.val());
		}
		
		function GetJSON(){
			var json={"uname":uname.val(),"upwd":pwd,"role":role.val(),"time":(new Date()).getTime()};
			return json;
		}
		$.ajax({
			url:"Check.do",
			type:"POST",
			contentType: "application/json",
			data:JSON.stringify(GetJSON()),
			dataType:"json",
			success:function(msg){
				if(msg=="1"){
					alert_warning[0].style.display="block";
					link_pwd[0].innerHTML="username or password is NULL!";
				}else if(msg=="2"){
					alert_warning[0].style.display="block";
					link_pwd[0].innerHTML="username or password is incorrect!";
				}else if(msg=="3"){
					console.log(returnURL.val());
					if(returnURL.val().length==0){
						location.href="/Dashboard/index.jsp";
					}else{
						location.href=returnURL.val();
					}
					
				}else if(msg=="4"){
					if(returnURL.val().length==0){
						location.href="/Dashboard/index.jsp";
					}else{
						location.href=returnURL.val();
					}
				}else if(msg == "5"){
					alert_warning[0].style.display="block";
					link_pwd[0].innerHTML="Email Address is NOT Verified!";
				}
			},
			error:function(er){
				alert(er);
			}
		});
	});
});