var search;
$(document).ready(function(){

	//「ここ」を押すことでのモーダルの表示処理
	$('#click_me').on('click', function(){
		$('.hidden').css('display','block');
	});
	//閉じるボタンでのモーダルの閉じる処理
	$('#modal-close').on('click', function(){
		modal_close();
	});
	//書き込み、入力値取得、の処理
	$('#kaku').on('click', function(){


		search = $('#form [name=word]').val();

		if(typeof search === "string" && search !== ""){
			$('#henkou').text(search);
			modal_close();
		}
		return false;

	});

	//モーダルを閉じる処理のメソッド化
	var modal_close =function(){
		$('.hidden').css('display','none');
	}
});


