//boxを押したときの色が変わる処理
$(function(){
	$('.kata').on('click',function(){
		var idname = $(this).attr("id");
		//bodyの背景変更
		$("#all").css("background-color",idname);
	});
})
