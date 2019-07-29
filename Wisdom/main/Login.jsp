<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="tool.Constant"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
	<head>
		<title>ログイン</title>
		<link href="css/Login_layout.css" rel="stylesheet" type="text/css">
		<meta charset="utf-8">
		<% Object userObject = session.getAttribute(Constant.TAG_USER); %>
	</head>
	<body>

		<!-- ヘッダー -->
		<header>
			<a href=<%=Constant.SERVLET_DETAIL %>><img id="logo" alt="logo" src="tenohira.png"></a>

			<!-- 検索BOX表示 -->
			<div id="searchbox">
			<form method="get" action=<%=Constant.SERVLET_DETAIL%> class="search_container">
  				<input type="text" size="25" name=<%= Constant.TAG_SEARCH_WORD %> placeholder="　キーワードけんさく">
  				<input type="submit" value="けんさく">
			</form>
			</div>

			<div id="yuzainf">

				<!-- ログインしてるならユーザー名表示、そうでない場合ゲスト表示 -->
				<%
				    if (userObject == null) {
				%>

				<!-- ゲストの場合 -->
				<a class="yuza">ゲストさん</a>

				<!-- ログインボタン表示 -->
				<form action=<%=Constant.SERVLET_LOGIN%> method="get">
					<input type="hidden" name="action" value="login">
					<input class="logbotton" type="submit" value="ログイン">
				</form>

				<%
				    } else {
				        User user = (User)userObject;
				%>

				<!-- ログイン済みの場合 -->
				<!-- ログインしているユーザ名を表示 -->
				<a class="link" href=<%=Constant.SERVLET_MYPAGE%>><%=user.getUsername()%>さん</a>

				<!-- ログアウトボタン表示 -->
				<form action=<%=Constant.SERVLET_LOGIN%> method="get">
					<input type="hidden" name="action" value="logout">
					<input class="logbotton" type="submit" value="ログアウト">
				</form>

				<%
				    }
				%>
			</div>
		</header>

		<div id ="contain">
			<h1>ログイン</h1>

			<!-- エラーがあるときメッセージを表示 -->
			<% if (request.getAttribute(Constant.TAG_ERROR) != null) { %>
				<p><%= request.getAttribute(Constant.TAG_ERROR) %></p>
			<% } %>

			<form action=<%= Constant.SERVLET_LOGIN %> method="post" class="input_form">

				<table>
					<tr>
						<th>ユーザID</th>
						<td><input class ="textfild" type="text" name="userID" size="40"></td>
					</tr>
					<tr>
						<th>パスワード</th>

						<!-- "placeholder" テキストボックスに初期表示させる文字 -->
						<td><input class ="textfild" type="password" name="password"
							placeholder="8文字以上16文字以内" size="40"></td>
					</tr>
					<tr id ="loginbutton">
						<th></th>
						<td><input type="submit" id ="login-button" value="ログイン"></td>
					</tr>
				</table>

			</form>

		</div>
		<footer>
		<div id="ichirann">
			<a class="link" href=<%=Constant.SERVLET_DETAIL %>>いちらんへ</a>
		</div>
		</footer>

	</body>
</html>
