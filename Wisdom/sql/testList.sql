/* 質問一覧画面初期表示のテストデータ */

/* usersテーブル */
INSERT INTO users (id, login_id, user_name, password, parents)
VALUES (1, 'login1', 'テストユーザ1', 'pass1234', false),
        (2, 'login2', 'テストユーザ2', 'word5678', true),
        (3, 'login3', 'テストユーザ3', 'abcd4321', false);

/* questionsテーブル */
INSERT INTO questions (id, title, content, created_at, create_user_id)
VALUES (123, 'クエスチョンテーブル１番目のタイトルです',
         'クエスチョンテーブル１番目のデータです１クエスチョンテーブル１番目のデータです１１',
         '2000-06-01', 1),
        (12345, 'クエスチョンテーブル２番目のタイトルです',
         'クエスチョンテーブル２番目のデータです１クエスチョンテーブル２番目のデータです１',
         '1994-01-31', 2),
        (123456, 'クエスチョンテーブル３番目のタイトルです',
         'クエスチョンテーブル３番目のデータです１クエスチョンテーブル３番目のデータです１
          クエスチョンテーブル３番目のデータです１クエスチョンテーブル３番目のデータです１
          クエスチョンテーブル３番目のデータです１', '2019-06-24', 3);


