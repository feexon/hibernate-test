DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS address;

CREATE TABLE user (
  id   INTEGER PRIMARY KEY,
  name VARCHAR
);

CREATE TABLE address (
  id      INTEGER PRIMARY KEY,
  country VARCHAR,
  userId  INTEGER
);

INSERT INTO user VALUES (1, 'Joe');
INSERT INTO user VALUES (2, 'Kate');

INSERT INTO address VALUES (1, 'china', 1);
INSERT INTO address VALUES (2, 'canada', 2);