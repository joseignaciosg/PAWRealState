-- Creamos la tabla de los servicios para moverlos dentro de la data

CREATE TABLE services (
  property_id integer not null,
  element character varying not null,
  primary key(property_id, element)
);

 -- has_cable boolean NOT NULL,
 --  has_phone boolean NOT NULL,
 --  has_swimmingpool boolean NOT NULL,
 --  has_salon boolean NOT NULL,
 --  has_paddle boolean NOT NULL,
 --  has_quincho boolean NOT NULL,

-- CABLE, PHONE, SWIMMING, SALON, PADDLE, QUINCHO
INSERT INTO services (SELECT id, text 'CABLE' as "element" FROM PROPERTIES P  WHERE P.has_cable = true);
INSERT INTO services (SELECT id, text 'PHONE' as "element" FROM PROPERTIES P  WHERE P.has_phone = true);
INSERT INTO services (SELECT id, text 'SWIMMING' as "element" FROM PROPERTIES P  WHERE P.has_swimmingpool = true);
INSERT INTO services (SELECT id, text 'SALON' as "element" FROM PROPERTIES P  WHERE P.has_salon = true);
INSERT INTO services (SELECT id, text 'PADDLE' as "element" FROM PROPERTIES P  WHERE P.has_paddle = true);
INSERT INTO services (SELECT id, text 'QUINCHO' as "element" FROM PROPERTIES P  WHERE P.has_quincho = true);

ALTER TABLE PROPERTIES DROP COLUMN has_cable;
ALTER TABLE PROPERTIES DROP COLUMN has_phone;
ALTER TABLE PROPERTIES DROP COLUMN has_swimmingpool;
ALTER TABLE PROPERTIES DROP COLUMN has_salon;
ALTER TABLE PROPERTIES DROP COLUMN has_paddle;
ALTER TABLE PROPERTIES DROP COLUMN has_quincho;
ALTER TABLE PROPERTIES ADD COLUMN  visitcount integer NOT NULL DEFAULT 0;

CREATE TABLE rooms (
  id SERIAL not null,
  size integer not null,
  property_id integer not null,
  type character varying not null,
  primary key(id)
);
