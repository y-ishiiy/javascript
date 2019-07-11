//モーダルを表示させる処理
$(document).ready(function(){
	$('#click_me').on('click', function(){
		$('.hidden').css('display','block');
	});
});
//モーダルを閉じる処理
$(document).ready(function(){
	$('#modal-close').on('click', function(){
		$('.hidden').css('display','none');
	});
});

var search;
//入力値の取得、判定による書き換え、閉じる処理
$(document).ready(function(){
	$('#form').on('submit', function(){
		search = document.getElementById('form').word.value;

		if(typeof search === "string" && search !== ""){
			$('#henkou').text(search);
			$('.hidden').css('display','none');
		}
		return false;


	});
});
