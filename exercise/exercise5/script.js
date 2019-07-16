
var images = {'':'',
	Java:'0から始めるには打って付けのプログラミング言語',
	C:'少し分かりずらいプログラミング言語、ゲーム制作ならよく使う',
	Python:'コードがシンプルでわかりやすいプログラミング言語'};

//プルダウンで値を取得しobjectからテキストを呼び出し出力
document.getElementById('form').select.onchange = function() {
	var genre = document.getElementById('form').select.value;

	document.getElementById('content').textContent = images[genre];

}

