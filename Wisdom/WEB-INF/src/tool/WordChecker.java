/*
 * 作成日：2019-06-21
 * 作成者：湯本涼香
 * 更新日：
 * 更新者：
 * 概要：禁止ワードチェッククラス
 */

package tool;

public class WordChecker {

    //入力値が禁止ワードを含んでいるときtrue
    public static boolean matchNgWord(String checkedString, String matchString) {
        return (checkedString.contains(matchString));
    }

    //入力値の長さ
    public static boolean strLengthErr(String inputStr, int min, int max) {
        boolean flg = true;
        if (inputStr.length() < min) {
            flg = false;
        } else if (inputStr.length() > max) {
            flg = false;
        }
        return flg;
    }

}
