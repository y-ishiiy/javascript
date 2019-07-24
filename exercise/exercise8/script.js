$(function(){
	//メインメニュー開閉処理
	$('.submenu h3').on('click', function(){
		$(this).next().toggleClass('hidden');
	});
	//メニュー追加処理
	$('#menuadd').on('click', function(){

		var cookgenre = $('#form [name=select]').val();
		var cookname = $('#form [name=word]').val();

		var li = $('<li>').text(cookname);
		$('ul' + cookgenre).append(li);
	});

	//削除のジャンルプルダウン切り替え時の削除メニュー一覧表示
	$('#delpullgenre').change(function() {
		var cookgenre = $('#formdel [name=selectgenre]').val();
		$('#delpullmenu option').remove();

		if(cookgenre !== ""){
			$.each($(cookgenre+' li'), function (i, elem) {
				option = $('<option>')
				.val(i)
				.text($(elem).text())
				$('#delpullmenu').append(option);
			});
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
