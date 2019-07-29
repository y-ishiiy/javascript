/* 作成日：20190620
 * 作成者：落合竜也
 * ユーザ情報を
 * */

package model;

public class User {

    //ユーザID
    private int userid;
    //ログインID
    private String loginId;
    //ユーザ名
    private String username = "no_name";
    //パスワード
    private Password password;
    //保護者
    private boolean parents = false;

    //==========コンストラクタ==========
    public User(int userid, String loginId, String username, String password, boolean parents) {
        this.userid = userid;
        this.loginId = loginId;
        this.username = username;
        this.password = new Password(password);
        this.parents = parents;
    }

    //==========getter==========
    /**パスワードのゲッターは設定しない*/

    //==========getter==========
    //パスワードのゲッターは設定しない
    public int getUserid() {
        return (userid);
    }

    public String getLoginId() {
        return (loginId);
    }

    public String getUsername() {
        return (username);
    }

    public boolean getParents() {
        return (parents);
    }

    //==========setter==========
    //ログインID設定
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    //アカウント名設定
    public void setUsername(String username) {
        this.username = username;
    }

    //入力したパスワードが一致するかを確認するメソッド
    @SuppressWarnings("unlikely-arg-type")
    public boolean matchPassword(String pass) {
        return (this.password.equals(pass));
    }

    //パスワードが正しいかを確認するメソッド
    public boolean checkPassword(String password) {
        return (this.password.checkPassword(password));
    }

    //パスワードの変更を行うメソッド
    public String changePassword(String password, String newPassword1, String newPassword2) {
        //パスワードの設定が行えたかの確認のため状態変数を返す。
        return (this.password.changePassword(password, newPassword1, newPassword2));
    }

}
