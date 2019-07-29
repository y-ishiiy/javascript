/* 作成日：20190620
 * 作成者：落合竜也
 * パスワードの変更、確認を行うクラス。パスワードだけ独立して作成したかった。
 * */

package model;

import tool.Constant;
import tool.InputChecker;

public class Password {

    /*==========インスタンスフィールド==========*/
    private String password = "";

    /*==========コンストラクタ==========*/
    protected Password(String password) {
        changePassword("", password, password);
    }

    protected Password(String password1, String password2) {
        changePassword("", password1, password2);
    }

    /*==========インスタンスメソッド==========*/
    //パスワードの設定を行うセッター
    protected String changePassword(String password, String newPassword1, String newPassword2) {

        //入力チェック
        //既存パスワードと同じパスワードかを確認
        if (!checkPassword(password)) {
            return (Constant.ERROR_MATCH);
        }
        //新しいパスワードの入力を確認
        if (!newPassword1.equals(newPassword2)) {
            return (Constant.ERROR_FITTING);
        }
        //文字列の長さが8文字以上16文字以下であるかを確認
        if (!inputLengthCheck(newPassword1)) {
            return (Constant.ERROR_LENGTH);
        }
        //パスワードを構成する文字列が正しい表記かを確認
        if (!(inputValueCheck(newPassword1))) {
            return (Constant.ERROR_SIMPLE);
        }

        this.password = newPassword1;
        return (Constant.ERROR_NO);
    }

    //入力されたパスワードが今のパスワードと等しい場合trueを返す
    protected boolean checkPassword(String password) {
        return (this.password.equals(password));
    }

    /*==========クラスメソッド==========*/

    //パスワードの長さが範囲内であるならtrueを返す
    public static boolean inputLengthCheck(String str) {
        return ((Constant.LIMIT_PASSWORD_LOW <= str.length()) && (str.length() <= Constant.LIMIT_PASSWORD));
    }

    //入力が指定通りであるならtrueを返す
    public static boolean inputValueCheck(String str) {
        //半角英字が含まれているならtrue
        boolean flag1 = InputChecker.smallAlphCont(str) || InputChecker.largeAlphCont(str);
        //半角数字が含まれているならtrue
        boolean flag2 = InputChecker.numCont(str);
        //半角英数字のみで構成されているならtrue
        boolean flag3 = InputChecker.alphNumCheck(str);

        return (flag1 && flag2 && flag3);
    }

}
