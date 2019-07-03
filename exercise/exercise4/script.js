var category = window.prompt('マイクさんの何が知りたいですか？（名前,年齢,身長,体重,趣味）');
var profile = {名前: 'Mick JS',年齢: 28, 身長: 185, 体重: 65, 趣味: "釣り、ツーリング、家庭菜園"};

//入力値がプロパティ名とあっているか判定
if(category === '名前'){
	//判定されたオブジェクトを出力
	document.getElementById('hour').textContent = 'マイクさんの名前は' +profile.名前+ 'です。';

}else if(category === '年齢'){
	document.getElementById('hour').textContent = 'マイクさんの年齢は' +profile.年齢+ 'です。';

}else if(category === '身長'){
	document.getElementById('hour').textContent = 'マイクさんの身長は' +profile.身長+ 'です。';

}else if(category === '体重'){
	document.getElementById('hour').textContent = 'マイクさんの体重は' +profile.体重+ 'です。';

}else if(category === '趣味'){
	document.getElementById('hour').textContent = 'マイクさんの趣味は' +profile.趣味+ 'です。';
//入力値がプロパティ名とちがう場合はエラーとなる。
}else{
	document.getElementById('hour').textContent = 'エラー';

}
