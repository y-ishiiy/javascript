/*
 * 作成日：2019-06-21
 * 作成者：湯本涼香
 * 更新日：
 * 更新者：
 * 概要：ログイン画面からの入力内容を確認するクラス
 */

package tool;

public class LoginChecker {

    //入力文字が入力値の範囲内であるかチェックするメソッド
    public static boolean strLengthErr(String inputStr, int min, int max) {
        boolean errFlg = false;
        if (inputStr.length() < min) {
            errFlg = true;
        } else if (inputStr.length() > max) {
            errFlg = true;
        }
        return errFlg;
    }

    //ログインID、パスワードの長さが規定値内であるかチェックするメソッド
    public static boolean loginStrLength(String loginId, String pass) {
        boolean errFlg = false;
        if (strLengthErr(loginId, Constant.LIMIT_USERID_LOW, Constant.LIMIT_USERID)) {
            errFlg = true;
        } else if (strLengthErr(pass, Constant.LIMIT_PASSWORD_LOW, Constant.LIMIT_PASSWORD)) {
            errFlg = true;
        }
        return errFlg;
    }

    //ログインIDが半角英数字のみであればtrue
    public static boolean inputIdStrCheck(String loginId) {
        return (loginId.matches("^[a-zA-Z0-9]*$"));
    }

}
