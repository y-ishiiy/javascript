CREATE DATABASE seminar;

¥c seminar

CREATE TABLE seminars (
    id Serial PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    crowded VARCHAR(10) NOT NULL
);

INSERT INTO seminars (name,crowded)
	VALUES ('JavaScript勉強会','yes'),
            ('セキュリティ対策講座','no'),
            ('UI/UXハッカソン','no');