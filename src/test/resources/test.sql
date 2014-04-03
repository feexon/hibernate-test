DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS recommend;
DROP TABLE IF EXISTS picture;


CREATE TABLE category (
  id   INTEGER PRIMARY KEY,
  name VARCHAR

);

CREATE TABLE recommend (
  id         INTEGER PRIMARY KEY,
  name       VARCHAR,
  categoryId INTEGER
);

CREATE TABLE picture (
  id          INTEGER PRIMARY KEY AUTOINCREMENT,
  image       VARCHAR,
  recommendId INTEGER
);

INSERT INTO category VALUES (1, '恋天鹅系列');
INSERT INTO category VALUES (2, '卓尔翡翠');

INSERT INTO recommend VALUES (3, '奢华梦想季01', 1);
INSERT INTO recommend VALUES (4, '奢华梦想季02', 2);
INSERT INTO picture(image,recommendId) VALUES ('1.jpg', 3);
INSERT INTO picture(image,recommendId) VALUES ('2.jpg', 3);


