var category = window.prompt('マイクさんの何が知りたいですか？（名前,年齢,身長,体重,趣味）');
var profile = {名前: 'Mick JS',年齢: 28, 身長: 185, 体重: 65, 趣味: "釣り、ツーリング、家庭菜園"};
//if文なしブラケット記述法
document.getElementById('hour').textContent = 'マイクさんの' + category + 'は' + profile[category] + 'です。';

