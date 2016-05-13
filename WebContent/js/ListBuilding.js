/**
 * ajax查询building
 */

$(document).ready(function(){
	$("#ListBuilding").click(function(){
		$.ajax({
			url:"ListBuilding.do",
			type:"GET",
			success:function(msg){
				$('#table').bootstrapTable({
					data: msg.data
				});
			},
			error:function(err){
				alert(err);
			}
		});
	});
});