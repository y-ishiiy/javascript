$(document).ready(function(){
	//jsonや追加のobjectをまとめる変数
	var lectureData;
	//idを昇順に揃える変数
	var idOrder = 3;
	//id名を取得する変数
	var idName;
	//「新規登録」を押すことでのモーダルの表示処理
	$('#click-me').on('click', function(){
		modalActive();
	});

	//「変更」を押すことでのモーダルの表示処理
	$('ul').on('click', '.checkedit', function(){
		idName = $(this).parent().attr('id');
		modalActive();

	});

	//閉じるボタンでのモーダルの閉じる処理
	$('#modal-close').on('click', function(){
		modalActive();
	});

	//ファイルの読み込み
	$.ajax({url: 'data.json', dataType: 'json'})
	.done(function(data){
		lectureData=data;
		$(data).each(function(i,p){
			var listCourse = $('<li class="seminar">').attr('id',i);
			listCourse.append($('<h2>').text(p.name));
			listCourse.append($('<p>').text("空席状況を確認").addClass('check'));
			listCourse.append($('<p>').text("編集").addClass('checkedit'));
			listCourse.append($('<p>').text("削除").addClass('checkdel'));
			$('ul.list').append(listCourse);

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
			var addObject = {id:idOrder,name:search,crowded:"no"};
			//dataにobjectの追加
			lectureData.push(addObject);
			//追加講義の表示処理
			var listCourse = $('<li class="seminar">').attr('id',idOrder);
			listCourse.append($('<h2>').text(search));
			listCourse.append($('<p>').text("空席状況を確認").addClass('check'));
			listCourse.append($('<p>').text("編集").addClass('checkedit'));
			listCourse.append($('<p>').text("削除").addClass('checkdel'));
			$('ul.list').append(listCourse);
			++idOrder;

			modalActive();
		}
	});

	//編集処理
	$('#to-edit').on('click', function(){
		var search = $('#form [name=word]').val();
		if(typeof search === "string" && search !== ""){
			$('#'+idName+' h2').text(search);
			//object名前編集
			lectureData[idName].name = search;
			modalActive();
		}

	});

	//削除処理
	$('ul').on('click', '.checkdel', function(){
		idName = $(this).parent().attr('id');
		$('li'+'#'+idName).remove();
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
		var text = JSON.stringify(lectureData);
		var blob = new Blob([text], {type: "application/json"});
		//ダウンロードリンクの作成
		var a = document.createElement("a");
		a.href = URL.createObjectURL(blob);
		a.target = '_blank';
		a.download = 'data.json';
		a.click();
	});

	//モーダルの開閉処理のメソッド化
	var modalActive =function(){
		$('#modal-content').toggleClass('hidden');
	}

});