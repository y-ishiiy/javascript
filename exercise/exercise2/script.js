var sum = parseInt(window.prompt('整数を入力してね。'));

for(var i = 1; sum > 0; i++) {
	//7倍数を出力させるための処理
	add = 7 * i;
	//毎回、入力値から-7する
	sum = sum - 7;
    //7より少ない場合のforをまわらせない処理
	if(sum < 7){
    	sum = -1;
    }
	console.log(add);
}