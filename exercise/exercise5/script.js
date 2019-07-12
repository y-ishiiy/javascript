
var images = ['','0から始めるには打って付けのプログラミング言語',
	'少し分かりずらいプログラミング言語、ゲーム制作ならよく使う',
	'コードがシンプルでわかりやすいプログラミング言語'];

//プルダウンで値を取得し配列からテキストを呼び出し出力
document.getElementById('form').select.onchange = function() {
	var urlNo = parseInt(document.getElementById('form').select.value);

	document.getElementById('content').textContent = images[urlNo];

}

