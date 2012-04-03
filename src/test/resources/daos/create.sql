-- SQLITE SCHEMA

CREATE TABLE contact_request
(
  id INTEGER PRIMARY KEY,
  username character varying NOT NULL,
  email character varying NOT NULL,
  phone character varying NOT NULL,
  "comment" character varying
);

CREATE TABLE property
(
  id INTEGER PRIMARY KEY,
  "type" character varying NOT NULL,
  "transaction" character varying NOT NULL,
  address character varying NOT NULL,
  neighborhood character varying NOT NULL,
  price integer NOT NULL,
  rooms integer NOT NULL,
  csqm integer NOT NULL,
  usqm integer NOT NULL DEFAULT 0,
  age integer NOT NULL,
  has_cable boolean NOT NULL,
  has_phone boolean NOT NULL,
  has_swimmingpool boolean NOT NULL,
  has_salon boolean NOT NULL,
  has_paddle boolean NOT NULL,
  has_quincho boolean NOT NULL,
  description character varying
);

CREATE TABLE users
(
  id INTEGER PRIMARY KEY,
  firstname character varying NOT NULL,
  lastname character varying NOT NULL,
  email character varying NOT NULL,
  phone character varying NOT NULL,
  username character varying,
  "password" character varying NOT NULL,
  CONSTRAINT users_username_key UNIQUE (username)
);

CREATE TABLE photos
(
  id INTEGER PRIMARY KEY,
  data bytea NOT NULL,
  type character varying NOT NULL,
  property_id integer references property(id)
);
