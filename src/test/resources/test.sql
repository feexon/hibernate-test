DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS t_order;

CREATE TABLE t_user (
  id NOT NULL PRIMARY KEY,
  name   VARCHAR,
  cardNo VARCHAR
);

CREATE TABLE t_order (
  id NOT NULL PRIMARY KEY,
  name        VARCHAR,
  payerCardNo VARCHAR
);

INSERT INTO t_user VALUES (1, 'admin', 'F-001');
INSERT INTO t_user VALUES (2, '张三', 'F-002');
INSERT INTO t_order VALUES (1, '金饰', 'F-002');
