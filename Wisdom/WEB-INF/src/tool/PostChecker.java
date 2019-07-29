/* 作成日：20190625
 * 作成者：落合竜也
 * JSPから受け取った文章が正しくないフォーマットだった場合にエラー文を返すクラス
 * */

package tool;

public class PostChecker {

    public String contentCheck(String content) {
        //戻り値用のエラー文変数
        String errorStr = "";

        if (content.length() > Constant.LIMIT_CONTENT) {
            //本文が長い場合のエラー
            errorStr = Constant.ERROR_CONTENT_LENGTH;
        } else if (InputChecker.emptyStringCheck(content)) {
            //本文が空白の場合のエラー
            errorStr = Constant.ERROR_CONTENT_EMPTY;
        }
        return (errorStr);
    }

    public String titleCheck(String title) {
        //戻り値用のエラー文変数
        String errorStr = "";

        if (title.length() > Constant.LIMIT_TITLE) {
            //タイトルが長い場合のエラー
            errorStr = Constant.ERROR_TITLE_LENGTH;
        } else if (InputChecker.emptyStringCheck(title)) {
            //タイトルが空白の場合のエラー
            errorStr = Constant.ERROR_TITLE_EMPTY;
        }
        return (errorStr);
    }
}
