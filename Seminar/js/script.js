//jsonや追加のobjectをまとめる変数
var lectureData;

//Read機能（JSONでの受け取り）メソッド
var read = function() {
	// Ajax通信実行
	$.ajax({
		type: "GET", // リクエストメソッド
		url: '/Seminar/seminars' // URL
	})
	.done(function(data, textStatus, jqXHR){
		lectureData=data;
		$(data).each(function(i,p){
			//DBからとってきた値の表示処理
			var listCourse = $('<li class="seminar">').attr('id',i+1);
			listCourse.append($('<h2>').text(p.name));
			listCourse.append($('<p>').text("空席状況を確認").addClass('check'));
			listCourse.append($('<p>').text("編集").addClass('checkedit'));
			listCourse.append($('<p>').text("削除").addClass('checkdel'));
			$('ul.list').append(listCourse);
			//空き席状況の判定処理
			if(this.crowded === 'yes') {
				var idName = '#' + this.id;
				$(idName).find('.check').addClass('crowded');
			}
		});
	})
	.fail(function(jqXHR, textStatus, errorThrown){
		window.alert(textStatus);
	})
};


$(document).ready(function(){

	//view上でのid名を取得する変数
	var idName;
	//初回のread機能の実装
    read();

	//「新規登録」を押すことでのモーダルの表示処理
	$('#click-me').on('click', function(){
		modalActive();
	});

	//「変更」を押すことでのモーダルの表示処理
	$('ul').on('click', '.checkedit', function(){
		//変更さきをidで特定
		idName = $(this).parent().attr('id');
		modalActive();

	});

	//閉じるボタンでのモーダルの閉じる処理
	$('#modal-close').on('click', function(){
		modalActive();
	});

	//書き込み、追加、の処理
	$('#to-add').on('click', function(){
		//入力した講義名取得
	    var search = $('#form [name=word]').val();

		if(typeof search === "string" && search !== ""){
			// 各フィールドから値を取得してJSONデータを作成
		    var data = {
		    	id: null,
		        name: search,
		        crowded: "no"
		    };

			// Ajax通信実行
		    $.ajax({
		        type:"post",                // method = "POST"
		        url:"/Seminar/seminars",        // POST送信先のURL
		        data:{ data: JSON.stringify(data),
		        	method:"create"},// JSONデータ本体
		        dataType: "json"           // レスポンスをJSONとしてパースする
		    })
		    .done(function(data, textStatus, jqXHR){
		    	//追加講義の表示処理
				var listCourse = $('<li class="seminar">').attr('id',data[0].id);
				listCourse.append($('<h2>').text(search));
				listCourse.append($('<p>').text("空席状況を確認").addClass('check'));
				listCourse.append($('<p>').text("編集").addClass('checkedit'));
				listCourse.append($('<p>').text("削除").addClass('checkdel'));
				$('ul.list').append(listCourse);
				var addObject = {id:data[0].id,name:search,crowded:"no"};
				//dataにobjectの追加
				lectureData.push(addObject);
		    })
		    .fail(function(jqXHR, textStatus, errorThrown){
		    	window.alert(textStatus);
		    })
			modalActive();
		}
	});


	//編集処理(update機能)
	$('#to-edit').on('click', function(){
		var search = $('#form [name=word]').val();

		if(typeof search === "string" && search !== ""){
			//jsonデータ作成
			var data = {
					id: idName,
					name: search
			};
			// 通信実行
		    $.ajax({
		        type:"post",                // method = "POST"
		        url:"/Seminar/seminars",        // POST送信先のURL
		        data:{ data: JSON.stringify(data),
		        	method:"update"},// JSONデータ本体
		    })

			$('#'+idName+' h2').text(search);
			//object名前編集
			lectureData[idName].name = search;
			modalActive();
		}

	});

	//削除処理(delete機能)
	$('ul').on('click', '.checkdel', function(){
		//削除さきをidで特定
		idName = $(this).parent().attr('id');
		//jsonデータ作成
		var data = {
				id: idName
		};
		// 通信実行
	    $.ajax({
	        type:"post",                // method = "POST"
	        url:"/Seminar/seminars",        // POST送信先のURL
	        data:{ data: JSON.stringify(data),
	        	   method:"delete"}
	    })

	    //view上での削除処理
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