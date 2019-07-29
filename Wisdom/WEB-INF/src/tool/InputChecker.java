/* 作成日：20190620
 * 作成者：落合竜也
 * 文字列に対してどんな文字構成かを確認するクラス
 * */

package tool;

public class InputChecker {

    //入力値が正規表現と一致しているならtrue
    public static boolean matchCheck(String checkedString, String matchString) {
        return (checkedString.matches(matchString));
    }

    //入力値が正規表現を含んでいるならtrue
    public static boolean matchContainCheck(String checkedString, String matchString) {
        return (checkedString.contains(matchString));
    }

    //入力値が空文字またはnullならtrue
    public static boolean emptyStringCheck(String checkedString) {
        if (checkedString.equals(null)) {
            return (true);
        } else {
            return (checkedString.equals(""));
        }
    }

    //入力値が自然数ならtrue
    public static boolean positiveIntCheck(String checkedNum) {
        return (checkedNum.matches("^[0-9]{1,}$"));
    }

    //入力値が負の整数ならtrue
    public static boolean negativeIntCheck(String checkedNum) {
        return (checkedNum.matches("^-[0-9]{1,}$"));
    }

    //入力値が正の有理数ならtrue
    public static boolean positiveDobCheck(String checkedNum) {
        //正の整数ならtrueを持つフラグ
        boolean naturalFlag = positiveIntCheck(checkedNum);
        //小数点を含む正の数ならtrueを持つフラグ
        boolean dotFlag = checkedNum.matches("^[0-9]{1,}\\.[0-9]{1,}$");
        return (naturalFlag || dotFlag);
    }

    //入力値が負の有理数ならtrue
    public static boolean negativeDobCheck(String checkedNum) {
        boolean flag1 = negativeIntCheck(checkedNum);
        boolean flag2 = checkedNum.matches("^-[0-9]{1,}\\.[0-9]{1,}$");
        return (flag1 || flag2);
    }

    //入力値に半角数字が含まれているならtrue
    public static boolean numCont(String checkedString) {
        return (checkedString.matches(".*[0-9].*"));
    }

    //入力値が全て小文字のアルファベットならtrue
    public static boolean smallAlphCheck(String checkedString) {
        return (checkedString.matches("^[a-z]{1,}$"));
    }

    //入力値に小文字のアルファベットが含まれているならtrue
    public static boolean smallAlphCont(String checkedString) {
        return (checkedString.matches(".*[a-z].*"));
    }

    //入力値が全て大文字のアルファベットならtrue
    public static boolean largeAlphCheck(String checkedString) {
        return (checkedString.matches("^[A-Z]{1,}$"));
    }

    //入力値に大文字のアルファベットが含まれているならtrue
    public static boolean largeAlphCont(String checkedString) {
        return (checkedString.matches(".*[A-Z].*"));
    }

    //入力値が英数字のみの場合ならtrue
    public static boolean alphNumCheck(String checkedString) {
        return (checkedString.matches("^[a-zA-Z0-9]*$"));
    }

    /*特殊文字の置き換え*/
    //<の処理
    public static String htmlTagCheck(String checkedString) {
        checkedString = checkedString.replace("&", "&amp;");
        checkedString = checkedString.replace("<", "&lt;");
        checkedString = checkedString.replace(">", "&gt;");
        return (checkedString);
    }
}
