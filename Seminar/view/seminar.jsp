<!doctype html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<meta http-equiv="x-ua-compatible" content="IE=edge">
		<title>空き席状況8月</title>
    	<link href="../css/style.css" rel="stylesheet" type="text/css">
		<link href="../css/main.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<header>
			<div class="header-contents">
				<h1>空き席状況をチェック</h1>
				<h2>Ajaxとデータの活用</h2>
			</div><!-- /.header-contents -->
		</header>
		<div class="main-wrapper">
		<section>
			<div id="modal-content" class="hidden">
				<p>講座名を入力してください。</p>
				<form action="#" id="form">
					<p>
					講座名：<input maxlength='20' type="text" name="word">
					</p>
					<input type="button" value="書き込む" id='to-add'>
					<input type="button" value="変更" id='to-edit'>
				</form>
				<button type="button" name="aaa" value="閉じる" id="modal-close">
				閉じる</button>
			</div>
			<button id="click-me">新規登録</button>
			<button id="download">ダウンロード</button>
			<ul class="list">
			</ul>
		</section>
		</div><!-- /.main-wrapper -->
		<footer>JavaScript Samples</footer>
		<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
		<script src ="../js/script.js"></script>
	</body>
</html>
