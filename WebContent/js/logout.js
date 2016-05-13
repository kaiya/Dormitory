/**
 * 用户登出js
 */

$(document).ready(function(){
	$(logout_link).click(function(){
		$.ajax({
			url:"/Dashboard/Logout.do",
			type:"GET",
			success:function(){
				location.href="/Dormitory/index.jsp";
			},
			error:function(er){
				console.log(er);
			}
		});
	});
});