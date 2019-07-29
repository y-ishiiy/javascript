<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Collections"%>
<%@ page import="controller.DetailServlet"%>
<%@ page import="dto.Answer"%>
<%@ page import="dto.Question"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.User"%>
<%@ page import="tool.Constant"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="css/mypage_layout.css" rel="stylesheet">
		<title>マイページ</title>
		<meta charset="utf-8">
		<% Object userObject = session.getAttribute(Constant.TAG_USER); %>
	</head>
	<body>
	<div id="container">
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

		<article>

		<div id ="contain">

			<div id="yuzaname">

				<% User user = (User) userObject; %>

				<!-- ログインしているユーザ名を表示 -->
				<a class="yuza"><%=user.getUsername()%>さん</a>
			</div>

			<!-- 質問するボタン表示（クリックで質問投稿画面へ） -->
			<button class="questionbutton"  type="button" onclick="location.href='<%=Constant.SERVLET_POST%>'">しつもんする</button>

		<div class ="qanda">
		<div class="myquestion">
			<!-- 自分の質問のタイトルのみをを一覧表示 -->
			<h1>自分のしつもん</h1>

			<%
				//日付表示設定
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

				//質問一覧が入っているArrayListを取得する。
			    @SuppressWarnings("unchecked")
			    ArrayList<Question> questionlist = (ArrayList<Question>) request.getAttribute(Constant.TAG_QUESTION_LIST);

				if (questionlist.size() != 0){
			%>
			<table>
				<tr>
					<th>タイトル</th>
					<th>しつもんした日</th>
				</tr>
				<%

				    for (Question question : questionlist) {
				%>
				<tr>
					<!-- タイトルクリックでその質問の詳細へ -->
					<td>
						<input type="hidden" name=<%=Constant.PARA_WHERE%> value=<%=Constant.JSP_DETAIL%>>
						<a href="<%=Constant.SERVLET_DETAIL %>?<%=Constant.PARA_WHERE%>=<%=Constant.NAME_DETAIL_JSP%>&<%=Constant.TAG_SEND_ID%>=<%=question.getId()%>"> <%=question.getTitle()%></a>
					</td>

					<!-- 質問投稿日表示 -->
					<td><%=sdf.format(question.getCreateTime())%></td>

				</tr>
				<%
				    }
				}else{
				%>

				<p>なし</p>

				<%
				}
				%>
			</table>
		</div>

		<div class="myanswer">
			<!-- 自分が回答した質問のタイトルのみを一覧表示 -->
			<h1>自分が答えたしつもん</h1>

			<%
				//質問一覧が入っているArrayListを取得する。
			    @SuppressWarnings("unchecked")
			    ArrayList<Question> answerlist = (ArrayList<Question>) request.getAttribute(Constant.TAG_ANSWER_LIST);

				if (answerlist.size() != 0){
			%>
			<table>
				<tr>
					<th>タイトル</th>
					<th>答えた日</th>
				</tr>
				<%
				    for (Question answer : answerlist) {
				%>
				<tr>
					<!-- タイトルクリックでその質問の詳細へ -->
					<td>
						<input type="hidden" name=<%=Constant.PARA_WHERE%> value=<%=Constant.JSP_DETAIL%>>
						<a href="<%=Constant.SERVLET_DETAIL %>?<%=Constant.PARA_WHERE%>=<%=Constant.NAME_DETAIL_JSP%>&<%=Constant.TAG_SEND_ID%>=<%=answer.getId()%>"> <%=answer.getTitle()%></a>
					</td>

					<!-- 質問投稿日表示 -->
					<td><%=sdf.format(answer.getCreateTime())%></td>

				</tr>
				<%
				    }
				}else{
				%>

				<p>なし</p>

				<%
				}
				%>
			</table>
		</div>
		</div>

		</div>

		</article>

		<footer>
		<div id="ichirann">
			<a class="link" href=<%=Constant.SERVLET_DETAIL %>>いちらんへ</a>
		</div>
		</footer>
		</div>
	</body>
</html>
