//boxを押したときの色が変わる処理、以下５通り分
$(function(){
	$('.kata').on('click',function(){
		$("body").attr('id','red');
	});

	$('.kata2').on('click',function(){
		$("body").attr('id','bule');
	});

	$('.kata3').on('click',function(){
		$("body").attr('id','pink');
	});

	$('.kata4').on('click',function(){
		$("body").attr('id','green');
	});

	$('.kata5').on('click',function(){
		$("body").attr('id','white');
	});
})
