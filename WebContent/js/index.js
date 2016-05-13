/**
 * home js
 */

$(document).ready(function(){
	
		 $(".flexslider").flexslider({
			    animation: "slide"
		 });
		var jsondata;
		$.ajax({
			url:"/News.do",
			type:"POST",
			contentType: "application/json",
			dataType:"json",
			success:function(msg){
				jsondata = msg;
				$.each(msg.newsdata,function(i,item){
					var btndom = "<button class='list-group-item'>"+item.title+"<span class='nid' style='display:none'>"+item.nid+"</span>"+"</button>";
					$(".list-group").append(btndom);
				});
			}, 
			error:function(er){
				console.log(er);
			}
		});
 		$(document).on('click', '.list-group-item', function(e) {
 			var thisBtn = $(this);
			$(this).attr({"disabled":"disabled"});
			
//			$.each($("button"),function(i,item){
//				
//				if(i != thisBtn[0].children[0].firstChild.data){
//					console.log(thisBtn[0].children[0].firstChild.data);
////					$("button")[0].removeAttr("disabled");
//				}
//			});
			var spannid = $(this)[0].children[0].firstChild.data
				$.each(jsondata.newsdata,function(i,item){
					if(item.nid == spannid){
						$("#newscontent")[0].innerHTML="" 
						var content = "<h1>"+item.title+"</h1><br > <p>"+item.content+"</p>";
						$("#newscontent").append(content);
					}
				});
		});
});
