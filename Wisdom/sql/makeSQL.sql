CREATE DATABASE tenohiradb;

\c tenohiradb;

/* ユーザテーブル */
CREATE TABLE users (
id SERIAL PRIMARY KEY,
login_id VARCHAR(20) NOT NULL UNIQUE,
user_name VARCHAR(20) NOT NULL UNIQUE,
password VARCHAR(16) NOT NULL,
parents BOOLEAN NOT NULL
);

/* 親子テーブル */
CREATE TABLE parent_and_child (
id SERIAL PRIMARY KEY,
parent_id INTEGER REFERENCES users (id),
child_id INTEGER REFERENCES users (id)
);

/* 質問テーブル */
CREATE TABLE questions (
id SERIAL PRIMARY KEY,
title VARCHAR(20) NOT NULL,
content VARCHAR(1000) NOT NULL,
created_at TIMESTAMP NOT NULL,
create_user_id INTEGER REFERENCES users (id)
);

/* 回答テーブル */
CREATE TABLE answers (
id SERIAL PRIMARY KEY,
content VARCHAR(1000) NOT NULL,
created_at TIMESTAMP NOT NULL,
question_id INTEGER REFERENCES questions (id),
create_user_id INTEGER REFERENCES users (id)
);

/* 追記テーブル */
CREATE TABLE append_questions (
id SERIAL PRIMARY KEY,
content VARCHAR(1000) NOT NULL,
created_at TIMESTAMP NOT NULL,
question_id INTEGER REFERENCES questions (id)
);

/* ユーザ作成 */
INSERT INTO users (id, login_id, user_name, password, parents, create_date) VALUES (1,'test01','テスト太郎','password','false','2019-06-24');
