ALTER TABLE PROPERTIES ADD COLUMN currency character varying NOT NULL DEFAULT 'DOLLAR';
UPDATE PROPERTIES SET currency = 'DOLLAR';

CREATE TABLE ads
(
  url character varying,
  weight integer,
  id serial NOT NULL,
  CONSTRAINT id PRIMARY KEY (id)
);

INSERT INTO ads (url, weight) VALUES ('https://dl.dropbox.com/u/1283975/paw/ad1.png', 10);
INSERT INTO ads (url, weight) VALUES ('https://dl.dropbox.com/u/1283975/paw/ad2.png', 20);
INSERT INTO ads (url, weight) VALUES ('https://dl.dropbox.com/u/1283975/paw/ad3.png', 10);
INSERT INTO ads (url, weight) VALUES ('https://dl.dropbox.com/u/1283975/paw/ad4.png', 30);
INSERT INTO ads (url, weight) VALUES ('https://dl.dropbox.com/u/1283975/paw/ad5.png', 10);
INSERT INTO ads (url, weight) VALUES ('https://dl.dropbox.com/u/1283975/paw/ad6.png', 40);
INSERT INTO ads (url, weight) VALUES ('https://dl.dropbox.com/u/1283975/paw/ad7.png', 10);
INSERT INTO ads (url, weight) VALUES ('https://dl.dropbox.com/u/1283975/paw/ad8.png', 100);

ALTER TABLE PROPERTIES ADD COLUMN  sold boolean NOT NULL DEFAULT FALSE;