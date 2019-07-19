$(function(){
	//メインメニュー開閉処理
	$('.submenu h3').on('click', function(){
		$(this).next().toggleClass('hidden');
	});
	//メニュー追加処理
	$('#menuadd').on('click', function(){

		var cookgenre = $('#form [name=select]').val();
		var cookname = $('#form [name=word]').val();

		let li = $('<li>').text(cookname);
		$('ul' + cookgenre).append(li);
	});

	//削除のジャンルプルダウン切り替え時の削除メニュー一覧表示
	$('#delpullgenre').change(function() {
		var cookgenre = $('#formdel [name=selectgenre]').val();
		$('#delpullmenu option').remove();
		$('.hidden1').css('display','none');

		$.each($(cookgenre+' li'), function (i, elem) {
			var menutext= $(elem).text();
			option = $('<option>')
			.val(i)
			.text(menutext)
			.prop('selected', 'true');
			$('#delpullmenu').append(option);
		});
		//ジャンル未選択時のプル非表示
		if(typeof cookgenre === "string" && cookgenre !== ""){
			$('.hidden1').css('display','block');
		}
	});
	//削除処理
	$('#delete').on('click', function(){
		var cookgenre = $('#formdel [name=selectgenre]').val();
		var menuNo = parseInt($('#formdel [name=menu]').val());

		$(cookgenre +' li').eq(menuNo).remove();
		$('#delpullgenre').trigger('change');

	});

});
