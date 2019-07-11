
var images = ['index.html','index-j.html', 'index-c.html', 'index-p.html'];
//プルダウンで値を取得し配列からURLを呼び出し値と照合
document.getElementById('form').select.onchange = function() {
	var urlNo = parseInt(document.getElementById('form').select.value);
//URLを変える
	location.href = images[urlNo];
}

