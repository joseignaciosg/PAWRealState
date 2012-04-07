CREATE TABLE contact_requests
(
  id serial NOT NULL,
  username character varying NOT NULL,
  email character varying NOT NULL,
  phone character varying NOT NULL,
  "comment" character varying,
  CONSTRAINT contact_request_pkey PRIMARY KEY (id)
);

CREATE TABLE properties
(
  id serial NOT NULL,
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
  description character varying,
  visible boolean NOT NULL,
  CONSTRAINT property_pkey PRIMARY KEY (id),
  CONSTRAINT property_transaction_check CHECK (transaction::text = ANY (ARRAY['SELL'::character varying::text, 'RENT'::character varying::text])),
  CONSTRAINT property_type_check CHECK (type::text = ANY (ARRAY['APARTMENT'::character varying::text, 'HOUSE'::character varying::text]))
);

CREATE TABLE users
(
  id serial NOT NULL,
  firstname character varying NOT NULL,
  lastname character varying NOT NULL,
  email character varying NOT NULL,
  phone character varying NOT NULL,
  username character varying,
  "password" character varying NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id),
  CONSTRAINT users_username_key UNIQUE (username)
);

CREATE TABLE photos
(
  id serial NOT NULL,
  data bytea NOT NULL,
  type character varying NOT NULL,
  property_id integer references properties(id),
  CONSTRAINT photos_pkey PRIMARY KEY (id)
);
