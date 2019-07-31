CREATE DATABASE seminar;

¥c seminar

CREATE TABLE seminars (
    id INTEGER PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    crowded VARCHAR(10) NOT NULL
);