var genre = ['.wa','.tyu','.yo'];

$(function(){
	//メインメニュー開閉処理
	$('.submenu h3').on('click', function(){
		$(this).next().toggleClass('hidden');
	});
	//メニュー追加処理
	$('#kaku').on('click', function(){

		var cookgenre = parseInt($('#form [name=select]').val());
		var cookname = $('#form [name=word]').val();

		let li = $('<li>').text(cookname);
		$('ul' + genre[cookgenre]).append(li);

		//初回追加時の削除ボタン表示
		if (typeof cookname === "string" && cookname !== "") {
			$('.hidden1').css('display','block');
		}
	});

	//削除のジャンルプルダウン切り替え時の削除メニュー一覧表示
	$('#delpull').change(function() {
		var cookNo = parseInt($('#formdel [name=selectgenre]').val());
		$('#delsel option').remove();
		$('.hidden2').css('display','none');

		$.each($(genre[cookNo]+' li'), function (i, elem) {
			var menutext= $(elem).text();
			option = $('<option>')
			.val(i)
			.text(menutext)
			.prop('selected', 'true');
			$('#delsel').append(option);
		});
		if(!isNaN(cookNo)){
			$('.hidden2').css('display','block');
		}
	});
	//削除処理
	$('#kesu').on('click', function(){
		var cookNo = parseInt($('#formdel [name=selectgenre]').val());
		var menuNo = parseInt($('#formdel [name=menu]').val());

		$(genre[cookNo]+' li').eq(menuNo).remove();
		$('#delpull').trigger('change');

	});

});
