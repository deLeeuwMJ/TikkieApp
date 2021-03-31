DROP TABLE IF EXISTS transactions;

CREATE TABLE IF NOT EXISTS transactions (
  id                     VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
  code                   VARCHAR      NOT NULL,
  sname                  VARCHAR      NOT NULL,
  cdate                  VARCHAR      NOT NULL,
  amount                 NUMERIC(3,1) NOT NULL
);

INSERT INTO transactions(code, sname, cdate, amount)
VALUES ('258wes', 'Miquel de Leeuw', '2021-01-03', '50.0');

INSERT INTO transactions(code, sname, cdate, amount)
VALUES ('adf89', 'Justen de Leeuw', '2021-02-03', '20.0');