<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="tool.Constant"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.Answer"%>
<%@ page import="dto.Question"%>
<%@ page import="dto.Append"%>
<%@ page import="model.User"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Collections"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="css/question_detail_layout.css" rel="stylesheet">
		<title>質問詳細</title>
		<meta charset="utf-8">

		<!-- 表示に必要な変数の受け取り -->
		<%
			//閲覧ユーザの特定
			Object userObject = session.getAttribute(Constant.TAG_USER);
			//詳細表示する質問の取得
			Object questionObject = request.getAttribute(Constant.TAG_QUESTION);
			//追記一覧を取得
		    Object appendObject = request.getAttribute(Constant.TAG_APPEND_LIST);
		  	//回答一覧ｗ取得
		    Object answerObject = request.getAttribute(Constant.TAG_ANSWER_LIST);
		%>

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

		<!-- タイトル表示 -->
		<%
	    Question question = null;

		//日付表示設定
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		//詳細表示する質問があれば追記文の質問の表示	if文：①
	    if (questionObject != null) {
	        question = (Question) questionObject;
		%>

		<h2 class ="addanswer">しつもん</h2>
		<div class ="heading">
			<!-- (タイトル ユーザ名 投稿日) 表示 -->
			<table>
			<div id ="gettitle">
				<h2 id="questiontitle"><%=question.getTitle()%></h2>
			</div>
			<div id="userinf">
				<a id="username"><%=question.getUserName()%></a>
				<a id="createtime"><%=sdf.format(question.getCreateTime())%></a>
			</div>
			</table>

			<!-- 本文表示 -->
			<div id="question">
				<a id="content" ><%=question.getQuestion()%></a>
			</div>

			</div>

			<%
			//listが存在している場合、追記を表示	if文：②
		    if (appendObject != null) {

		        @SuppressWarnings("unchecked")
		        ArrayList<Append> appendList = (ArrayList<Append>) appendObject;

			%>

				<!-- 追記文出力 -->
				<table>

				<%
				if (appendList.size() > 0){
				%>
				<h2 class ="addanswer">書き足したもの</h2>

				<%
				}

				for(Append append : appendList) {

				%>
					<tr>
						<!-- 追記内容 -->
						<td><%=append.getContent()%></td>
						<!-- 質問投稿日表示 -->
						<td><%=sdf.format(append.getCreateTime())%></td>
					</tr>


				<!-- for文の終了 -->
				<% } %>
				</table>
			<!-- if文②の終了 -->
			<% } %>

			<%
		    //listが存在している場合、回答を表示	if文：④
		    if (answerObject != null) {
		        @SuppressWarnings("unchecked")
		        ArrayList<Answer> answers = (ArrayList<Answer>) answerObject;
			%>

			<!-- 回答一覧を表示する。 -->
			<table border="1">

				<%
				if (answers.size() > 0){
				%>
				<h2 class ="addanswer">答え</h2>

				<%
				}

		    	//回答一覧を全件表示
		        for (Answer answer : answers) {
				%>
					<tr>

						<!-- 回答内容 -->
						<td><%=answer.getAnswer()%></td>
						<!-- 回答投稿日表示 -->
						<td><%=sdf.format(answer.getCreateTime())%></td>
						<!-- 回答ユーザ表示 -->
						<td><%=answer.getUserName()%></td>

						<%
						    User user = (User)userObject;

						    //自分の質問の時だけ回答を消せる。
						    if(user.getUserid() == question.getUserId()){

						%>

						<td><form action=<%=Constant.SERVLET_DELETE %> method="get">
								<input type="hidden" value=<%=question.getId()%> name=<%=Constant.TAG_QUESTION_ID%>>
								<input type="hidden" value=<%=answer.getAnswerId()%> name=<%=Constant.TAG_ANSWER_ID%>>
								<input type="submit" value="さくじょ">
							</form>
						</td>
					</tr>
				<!-- for文の終了 -->
				<% } }%>
				<%
				//listがnullの場合、回答が存在しないので"なし"と表示。	if文：④のelse
		    } else {
			%>

			<p>なし</p>
			<!-- if文④の終了 -->
			<% } %>
			<!-- if文①のelse : 詳細表示させたい質問がない場合 -->
			<%}else {
			    //質問一覧にリダイレクト
				response.sendRedirect(Constant.SERVLET_DETAIL);
			}
			%>
			</table>

			<!-- 回答フォームと追記フォームのどちらを出すか判別 -->
			<%
			User user = null;
			//受け取ったユーザ情報がnullでなければキャスト
			if(userObject != null){
			    user = (User) userObject;
			}

		   	//ゲストまたは、質問者と異なるユーザで閲覧している場合。	if文：③
		    if (userObject == null || !(user.getUsername().equals(question.getUserName()))) {
			%>
				<!-- 回答フォーム -->
				<form action=<%=Constant.SERVLET_POST%> method="post">
					<textarea name=<%=Constant.TAG_CONTENT%>></textarea>
					<!-- どの質問に回答したのかを示す質問IDの送信 -->
					<input type="hidden" name=<%=Constant.TAG_QUESTION_ID%> value=<%=question.getId()%>>
					<!-- 回答投稿したユーザIDの送信 -->
					<input type="hidden" name=<%=Constant.TAG_USER%> value=<%=user.getUserid() %>>
					<!-- 回答投稿であることを示すフラグ送信 -->
					<input type="hidden" name=<%=Constant.TAG_POST%> value=<%=Constant.POST_ANSWER%>>
					<p ><input class ="sendbutton" type="submit" value="答える"></p>
				</form>

			<%
		    //質問者と同一ユーザで閲覧している場合	if文：③のelse
		    } else {
			%>
				<!-- 追記フォーム -->
				<form action=<%=Constant.SERVLET_POST%> method="post">
					<textarea name=<%=Constant.TAG_CONTENT%>></textarea>
					<!-- どの質問に追記したのかを示す質問IDの送信 -->
					<input type="hidden" name=<%=Constant.TAG_QUESTION_ID%> value=<%=question.getId()%>>
					<!-- 追記投稿であることを示すフラグ送信 -->
					<input type="hidden" name=<%=Constant.TAG_POST %> value=<%=Constant.POST_APPEND %>>
					<p ><input class ="sendbutton" type="submit" value="書き足す"></p>
				</form>
			<!-- if文③の終了 -->
			<% } %>



		</div>
		</article>

		<!-- フッター -->
		<footer>
		<div id="ichirann">
			<a class="link" href=<%=Constant.SERVLET_DETAIL %>>いちらんへ</a>
		</div>
		</footer>

	</body>
</html>