/* 作成日：20190627
 * 作成者：落合竜也
 * 定数クラス
 * */

package tool;

public class Constant {

    /*==========JSP名==========*/
    public static final String NAME_LOGIN_JSP = "LoginJsp"; //ログインJSPへのパス
    public static final String NAME_MYPAGE_JSP = "mypageJsp"; //マイページJSPへのパス
    public static final String NAME_DETAIL_JSP = "questionDetailJsp"; //質問詳細JSPへのパス
    public static final String NAME_POST_JSP = "newJsp"; //質問投稿JSPへのパス
    public static final String NAME_LIST_JSP = "listJsp"; //質問一覧JSPへのパス

    /*==========JSPパス==========*/
    /*パスはWisdom直下にいることを前提にした相対パスで示す*/
    public static final String JSP_LOGIN = "/main/Login.jsp"; //ログインJSPへのパス
    public static final String JSP_MYPAGE = "/main/mypage.jsp"; //マイページJSPへのパス
    public static final String JSP_DETAIL = "/main/question_detail.jsp"; //質問詳細JSPへのパス
    public static final String JSP_POST = "/main/new.jsp"; //質問投稿JSPへのパス
    public static final String JSP_LIST = "/main/list.jsp"; //質問一覧JSPへのパス

    /*==========Servletパス==========*/
    /*パスはWisdom直下にいることを前提にした相対パスで示す*/
    public static final String SERVLET_LOGIN = "./LoginController"; //ログインサーブレットへのパス
    public static final String SERVLET_MYPAGE = "./MypageController"; //マイページサーブレットへのパス
    public static final String SERVLET_DETAIL = "./DetailController"; //詳細画面サーブレットへのパス
    public static final String SERVLET_POST = "./PostController"; //文章投稿サーブレットへのパス
    public static final String SERVLET_DELETE = "./DeleteController"; //削除サーブレットへのパス

    /*==========Attributeタグ==========*/
    public static final String TAG_USER = "tagUser";//ユーザをセットするときのキー
    public static final String TAG_ANSWER = "tagAnswer";//回答をセットするときのキー
    public static final String TAG_ANSWER_ID = "tagAnswerId";//回答IDをセットするときのキー
    public static final String TAG_ANSWER_LIST = "tagAnswerList";//回答リストをセットするときのキー
    public static final String TAG_QUESTION = "tagQuestion";//質問をセットするときのキー
    public static final String TAG_QUESTION_ID = "tagQuestionId";//質問IDをセットするときのキー
    public static final String TAG_QUESTION_LIST = "tagQuestionList";//質問リストをセットするときのキー
    public static final String TAG_APPEND = "tagAppend";//追記をセットするときのキー
    public static final String TAG_APPEND_ID = "tagAppendId";//追記IDをセットするときのキー
    public static final String TAG_APPEND_LIST = "tagAppendList";//追記リストをセットするときのキー
    public static final String TAG_ERROR = "tagError";//エラー文をセット・ゲットするためのキー
    public static final String TAG_SEARCH_WORD = "tagSearchWord";//検索文をセット・ゲットするためのキー
    public static final String TAG_SEND_ID = "tagSendId";//検索文をセット・ゲットするためのキー
    //質問・回答・追記の投稿用パラメータ
    public static final String TAG_POST = "tagPost";//何を投稿したのかをセットするときのキー
    public static final String POST_ANSWER = "postAnswer";//何を投稿したのかに対する値
    public static final String POST_QUESTION = "postQuestion";//何を投稿したのかに対する値
    public static final String POST_APPEND = "postAppend";//何を投稿したのかに対する値
    public static final String TAG_TITLE = "tagTitle";//投稿タイトルをセットするときのキー
    public static final String TAG_CONTENT = "tagContent";//投稿本文をセットするときのキー
    public static final String TAG_POST_USER = "tagUser";//投稿者をセットするときのキー
    /*==========Attributeタグ==========*/
    public static final String FLAG_BEFORE = "before";//どこから来たのか
    public static final String FLAG_AFTER = "after";//どこに行くのか

    /*==========Parameter==========*/
    public static final String PARA_WHERE = "paramaterWhere";//どの画面遷移を要求しているのかを取得するキー

    /*==========Column==========*/
    //ユーザテーブル
    public static final String COL_USER_ID = "id";//ユーザーテーブルのカラム「ID」
    public static final String COL_USER_LOGIN = "login_id";//ユーザーテーブルのカラム「ログインID」
    public static final String COL_USER_NAME = "user_name";//ユーザーテーブルのカラム「ユーザー名」
    public static final String COL_USER_PASS = "passwprd";//ユーザーテーブルのカラム「パスワード」
    public static final String COL_USER_STATUS = "parents";//ユーザーテーブルのカラム「親であるかどうか」
    //親子テーブル
    public static final String COL_PAC_ID = "id";//親子テーブルのカラム名「ID」
    public static final String COL_PAC_PARENT_ID = "parent_id";//親子テーブルのカラム名「親ID」
    public static final String COL_PAC_CHILD_ID = "child_id";//親子テーブルのカラム名「子ID」
    //質問テーブル
    public static final String COL_QUE_ID = "id";//質問テーブルのカラム「ID」
    public static final String COL_QUE_TITLE = "title";//質問テーブルのカラム「タイトル」
    public static final String COL_QUE_CONTENT = "content";//質問テーブルのカラム「本文」
    public static final String COL_QUE_CREATE_AT = "created_at";//質問テーブルのカラム「投稿日」
    public static final String COL_QUE_USER = "create_user_id";//質問テーブルのカラム「投稿者」
    //回答テーブル
    public static final String COL_ANS_ID = "id";//回答テーブルのカラム「ID」
    public static final String COL_ANS_CONTENT = "content";//回答テーブルのカラム「本文」
    public static final String COL_ANS_CREATE_AT = "created_at";//回答テーブルのカラム「投稿日」
    public static final String COL_ANS_QUE_ID = "question_id";//回答テーブルのカラム「質問ID」
    public static final String COL_ANS_USER = "create_user_id";//回答テーブルのカラム「投稿者」
    //追記テーブル
    public static final String COL_APP_ID = "id";//追記テーブルのカラム「ID」
    public static final String COL_APP_QUE_ID = "question_id";//追記テーブルのカラム「質問ID」
    public static final String COL_APP_CONTENT = "content";//追記テーブルのカラム「本文」
    public static final String COL_APP_CREATE_AT = "created_at";//追記テーブルのカラム「投稿日」

    /*==========定数==========*/
    /*Passwordクラスのエラーメッセージ*/
    public static final String ERROR_NO = "パスワードは正しく設定されました。";
    public static final String ERROR_ALREADY = "そのユーザ名は既に使用されています。";
    public static final String ERROR_LENGTH = "パスワードは8～16文字で設定してください。";
    public static final String ERROR_SIMPLE = "パスワードは英数字混在に設定してください。";
    public static final String ERROR_MATCH = "変更後のパスワードが一致しません";
    public static final String ERROR_FITTING = "アカウント名またはパスワードが間違っています。";

    /*質問と回答の投稿時のエラー文*/
    public static final String ERROR_POST_NULL = "投稿内容が正しくありません。";
    public static final String ERROR_TITLE_LENGTH = "タイトルが長すぎます。";
    public static final String ERROR_CONTENT_LENGTH = "本文が長すぎます。";
    public static final String ERROR_TITLE_EMPTY = "タイトルが空欄です。";
    public static final String ERROR_CONTENT_EMPTY = "本文が空欄です。";

    /*文字数*/
    public static final int LIMIT_CONTENT = 1000;//本文の文字数制限
    public static final int LIMIT_TITLE = 20;//タイトルの文字数制限
    public static final int LIMIT_USERID = 20;//ユーザーIDの文字数上限
    public static final int LIMIT_USERID_LOW = 1;//ユーザーIDの文字数下限
    public static final int LIMIT_PASSWORD = 16;//ユーザーIDの文字数制限
    public static final int LIMIT_PASSWORD_LOW = 8;//ユーザーIDの文字数制限


}
