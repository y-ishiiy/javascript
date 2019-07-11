//boxを押したときの色が変わる処理、以下５通り分
$(function(){
	$('.kata').on('click',function(){
		$("body").attr('id','red');
	});
})

$(function(){
	$('.kata2').on('click',function(){
		$("body").attr('id','bule');
	});
})

$(function(){
	$('.kata3').on('click',function(){
		$("body").attr('id','pink');
	});
})

$(function(){
	$('.kata4').on('click',function(){
		$("body").attr('id','green');
	});
})

$(function(){
	$('.kata5').on('click',function(){
		$("body").attr('id','white');
	});
})