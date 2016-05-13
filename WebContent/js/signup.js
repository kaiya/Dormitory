/**
 * 用户注册js
 */
$(document).ready(function(){
	
	$(".btn-submit").click(function(){
		
		var uname = $("#uname");
		var upwd =$("#upwd");
		var email = $("#email");
		var link_pwd = $("#link-pwd");
		var alert_warning = $("#alert_warning");
		
		if(uname.val() ==""){
			uname.focus();
			return false;
		}
		if(email.val() == ""){
			email.focus();
			return false;
		}else{
			var tempVal = email.val();
			var Regex = /^(?:\w+\.?)*\w+@(?:\w+\.)*\w+$/;
			if (!Regex.test(tempVal)){
				alert_warning[0].style.display="block";
				link_pwd[0].innerHTML="Please enter a valid Email Address!";
				return false;
			}
		}
		if(upwd.val() ==""){
			upwd.focus();
			return false;
		}else{
			var pwd = $.md5(upwd.val());
		}
		
		function GetJSON(){
			var json={"uname":uname.val(),"upwd":pwd,"email":email.val(),"time":(new Date()).getTime()};
			return json;
		}
		$.ajax({
			url:"Signup.do",
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
					link_pwd[0].innerHTML="Sign up success! Redirecting......";
					setTimeout(function(){
						location.href="login.jsp";
					},1000);
				}else if(msg=="3"){
					alert_warning[0].style.display="block";
					link_pwd[0].innerHTML="unknown error!";
				}
			},
			error:function(er){
				alert(er);
			}
		});
	});
});
