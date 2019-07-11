//}); $(document).ready(function()はファイルに一つで事足りる複数は必要ない

var search;
$(document).ready(function(){

	//「ここ」を押すことでのモーダルの表示処理
	$('#click_me').on('click', function(){
		$('.hidden').css('display','block');
	});
	//閉じるボタンでのモーダルの閉じる処理
	$('#modal-close').on('click', function(){
		$('.hidden').css('display','none');
	});
	//書き込み、入力値取得、の処理
	$('#kaku').on('click', function(){

		//search = document.getElementById('form').word.value;
		search = $('#form [name=word]').val();

		if(typeof search === "string" && search !== ""){
			$('#henkou').text(search);
			$('.hidden').css('display','none');
		}
		return false;


	});
});
