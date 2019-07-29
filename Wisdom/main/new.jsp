<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="tool.Constant"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="css/new_layout.css" rel="stylesheet">
		<title>質問投稿</title>
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


		<!------------------------------  質問投稿  -------------------------------------->
		<div id ="contain">
			<h1>しつもんする</h1>

			<%
				    User user = (User)userObject;
			%>

			<form action=<%=Constant.SERVLET_POST %> method="post">

				<table class ="question_send">
					<tr>
						<th>タイトル</th>
						<td><input type="text" name=<%=Constant.TAG_TITLE %>></td>
					</tr>
					<tr>
						<th>本文</th>
						<td><textarea name=<%=Constant.TAG_CONTENT %>></textarea></td>
					</tr>
					<tr>
						<th></th>
						<td colspan="2">
						<input type="hidden" name=<%=Constant.TAG_USER %> value=<%=user.getUserid() %>>
						<input type="hidden" name=<%=Constant.TAG_POST %> value=<%=Constant.POST_QUESTION %>>
						<input class="button" type="submit" value="しつもんする"></td>
					</tr>
				</table>
			</form>
		</div>

		<footer class="footer">
		<div id="ichirann">
			<a class="link" href=<%=Constant.SERVLET_DETAIL %>>いちらんへ</a>
		</div>
		</footer>
	</body>
</html>
