/* 作成日：20190624
 * 作成者：落合竜也
 * 追記データを保持するクラス
 * */

package dto;

import java.sql.Timestamp;

public class Append {

    //追記データの区別ID
    private int appendId;
    //追記内容本文
    private String content;
    //どの質問に対する追記か
    private int questionId;
    //投稿日時
    private Timestamp createTime;

    //==========コンストラクタ==========
    //新規投稿用のコンストラクタ
    public Append(String content, int questionId) {
        //DAOからユーザー名を取得
        this.appendId = 0;
        this.content = content;
        this.questionId = questionId;
    }

    //データベースから取得用のコンストラクタ
    public Append(int appendId, String content, int questionId, Timestamp createTime) {
        //DAOからユーザー名を取得
        this.appendId = appendId;
        this.content = content;
        this.questionId = questionId;
        this.createTime = createTime;
    }

    //==========getter==========
    public int getAppendId() {
        return (appendId);
    }

    public String getContent() {
        return (content);
    }

    public int getQuestionId() {
        return (questionId);
    }

    public Timestamp getCreateTime() {
        return (createTime);
    }
}
