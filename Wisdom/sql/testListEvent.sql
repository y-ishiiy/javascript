/* 質問一覧イベント発生時のテストデータ */

/* usersテーブル */
INSERT INTO users (id, login_id, user_name, password, parents)
VALUES (1, 'login1', 'テストユーザ1', 'pass1234', false),
        (2, 'login2', 'テストユーザ2', 'word5678', true),
        (3, 'login3', 'テストユーザ3', 'abcd4321', false);

        /* questionsテーブル */
INSERT INTO questions (id, title, content, created_at, create_user_id)
VALUES (123, 'title', '本文です。', '2000-06-01', 1),
        (12345, 'テスト', '質問しました', '1994-01-31', 2),
        (123456, 'タイトル', 'コンテンツは、本文です。','2019-06-24', 3);

