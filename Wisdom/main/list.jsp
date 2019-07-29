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
		<link href="css/list_layout.css" rel="stylesheet">
		<title>質問一覧</title>
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

		<article>

		<div id ="contain">
			<!-- サイトの説明表示 -->
			<p id="siteinf">
				小学生のための質問サイト！<br>
				みんなで分からないことは、質問し合おう！
			</p>

			<!-- 質問するボタン表示 -->
			<button class="questionbutton"  type="button" onclick="location.href='<%=Constant.SERVLET_POST%>'">しつもんする</button>

		<div id ="allquestion">
			<%
				//質問一覧のlist取得。
			    @SuppressWarnings("unchecked")
			    ArrayList<Question> questionlist =
			    (ArrayList<Question>) request.getAttribute(Constant.TAG_QUESTION_LIST);

				if (questionlist.size() != 0){
			%>
			<h1>いちらん　（<%=questionlist.size()%>こ）</h1>

			<table>
				<tr>
					<th class="title"  >タイトル</th>
					<th class="main"  >本文</th>
					<th class="createday" >しつもんした日</th>
					<th>ユーザ</th>
				</tr>
				<%
				    //日付表示設定
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");


				    for (Question question : questionlist) {

				        String str = question.getQuestion();
				%>
				<tr id ="post_question">
					<td>
						<input type="hidden" name=<%=Constant.PARA_WHERE%> value=<%=Constant.JSP_DETAIL%>>
						<a href="<%=Constant.SERVLET_DETAIL %>?<%=Constant.PARA_WHERE%>=<%=Constant.NAME_DETAIL_JSP%>&<%=Constant.TAG_SEND_ID%>=<%=question.getId()%>"> <%=question.getTitle()%></a>
					</td>

					<!-- 本文が40字以上だと切り取る。 -->
					<td>
					<%if (str.length() >= 40) {%>
					<%=str.substring(0, 40) %>
					<%}else{ %>
					<%=str %>
					<%} %>
					</td>
					<td><%=sdf.format(question.getCreateTime())%></td>
					<td><%=question.getUserName()%></td>

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
		</article>

		<footer>
		<div id="ichirann">
			<a class="link" href=<%=Constant.SERVLET_DETAIL %>>いちらんへ</a>
		</div>
		</footer>

	</body>
</html>
