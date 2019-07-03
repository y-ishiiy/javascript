var time = parseInt(window.prompt('時刻を入力してください。（時のみ/24時間制）'));
var timeAllocation = ['未明','明け方','朝','昼前','昼過ぎ','夕方','夜の初め頃','夜遅く'];

//if文で入力値を時間で振り分け
if(time >= 0 && time <= 2){
	//入力値と判定された配列文の出力
	document.getElementById('hour').textContent = time +'時は' +timeAllocation[0]+ 'です。';

}else if(time >= 3 && time <= 5){
	document.getElementById('hour').textContent = time +'時は' +timeAllocation[1]+ 'です。';

}else if(time >= 6 && time <= 8){
	document.getElementById('hour').textContent = time +'時は' +timeAllocation[2]+ 'です。';

}else if(time >= 9 && time <= 11){
	document.getElementById('hour').textContent = time +'時は' +timeAllocation[3]+ 'です。';

}else if(time >= 12 && time <= 14){
	document.getElementById('hour').textContent = time +'時は' +timeAllocation[4]+ 'です。';

}else if(time >= 15 && time <= 17){
	document.getElementById('hour').textContent = time +'時は' +timeAllocation[5]+ 'です。';

}else if(time >= 18 && time <= 20){
	document.getElementById('hour').textContent = time +'時は' +timeAllocation[6]+ 'です。';

}else if(time >= 21 && time <= 24){
	document.getElementById('hour').textContent = time +'時は' +timeAllocation[7]+ 'です。';
//時間以外のすべてはエラーとする
}else{
	document.getElementById('hour').textContent = 'エラー';

}
