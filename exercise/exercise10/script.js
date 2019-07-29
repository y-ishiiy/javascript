$(document).ready(function(){
	//jsonや追加のobjectをまとめる変数
	var lecturedata;
	//idを昇順に揃える変数
	var idorder = 3;
	//id名を取得する変数
	var idname;
	//「新規登録」を押すことでのモーダルの表示処理
	$('#click-me').on('click', function(){
		$('#modal-content').toggleClass('hidden');
	});

	//「変更」を押すことでのモーダルの表示処理
	$('ul').on('click', '.checkedit', function(){
		idname = $(this).parent().attr('id');
		$('#modal-content').toggleClass('hidden');

	});

	//閉じるボタンでのモーダルの閉じる処理
	$('#modal-close').on('click', function(){
		$('#modal-content').toggleClass('hidden');
	});

	//ファイルの読み込み
	$.ajax({url: 'data.json', dataType: 'json'})
	.done(function(data){
		lecturedata=data;
		$(data).each(function(i,p){
			var listcourse = $('<li class="seminar">').attr('id',i);
			listcourse.append($('<h2>').text(p.name));
			listcourse.append($('<p>').text("空席状況を確認").addClass('check'));
			listcourse.append($('<p>').text("編集").addClass('checkedit'));
			listcourse.append($('<p>').text("削除").addClass('checkdel delete'));
			$('ul.list').append(listcourse);

			if(this.crowded === 'yes') {
				var idName = '#' + this.id;
				$(idName).find('.check').addClass('crowded');
			}
		});
	})
	.fail(function(){
		window.alert('読み込みエラー');
	});
	//書き込み、追加、の処理
	$('#to-add').on('click', function(){

		var search = $('#form [name=word]').val();

		if(typeof search === "string" && search !== ""){
			var addobject = {id:idorder,name:search,crowded:"no"};
			//dataにobjectの追加
			lecturedata.push(addobject);
			//追加講義の表示処理
			var listcourse = $('<li class="seminar">').attr('id',idorder);
			listcourse.append($('<h2>').text(search));
			listcourse.append($('<p>').text("空席状況を確認").addClass('check'));
			listcourse.append($('<p>').text("編集").addClass('checkedit'));
			listcourse.append($('<p>').text("削除").addClass('checkdel'));
			$('ul.list').append(listcourse);
			++idorder;

			$('#modal-content').toggleClass('hidden');
		}
	});

	//編集処理
	$('#to-edit').on('click', function(){
		var search = $('#form [name=word]').val();
		if(typeof search === "string" && search !== ""){
			$('#'+idname+' h2').text(search);
			//object名前編集
			lecturedata[idname].name = search;
			$('#modal-content').toggleClass('hidden');
		}

	});

	//削除処理
	$('ul').on('click', '.checkdel', function(){
		idname = $(this).parent().attr('id');
		$('li'+'#'+idname).remove();
	});

	//クリックされたら空き席状況を表示
	$('ul').on('click','.check', function(){
		if($(this).hasClass('crowded')) {
			$(this).text('残席わずか').addClass('red');
		} else {
			$(this).text('お席あります').addClass('green');
		}
	});
	//ダウンロード処理
	$('#download').on('click',function(){
		//ダウンロードデータの作成
		var text = JSON.stringify(lecturedata);
		var blob = new Blob([text], {type: "application/json"});
		//ダウンロードリンクの作成
		var a = document.createElement("a");
		a.href = URL.createObjectURL(blob);
		a.target = '_blank';
		a.download = 'data.json';
		a.click();
	});

});