-- Flyway baseline migration: create core tables required by JPA entities

--drop table if exists orders;
--drop table if exists cart_items;
--drop table if exists carts;
--drop table if exists products;
--drop table if exists users;

CREATE TABLE if not exists users (
  id varchar(36) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  token varchar(255),
  expiration varchar(255),
  PRIMARY KEY (id)
);

--CREATE UNIQUE INDEX uk_users_email ON users(email);

CREATE TABLE if not exists products (
  id varchar(36) NOT NULL,
  title varchar(255) NOT NULL,
  description text,
  price double precision NOT NULL,
  quantity integer NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE if not exists carts (
  id varchar(36) NOT NULL,
  user_id varchar(36) NOT NULL UNIQUE,
  PRIMARY KEY (id),
  CONSTRAINT fk_carts_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE if not exists cart_items (
  id varchar(36) NOT NULL,
  cart_id varchar(36) NOT NULL,
  product_id varchar(36) NOT NULL,
  quantity integer NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_cart_items_cart FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
  CONSTRAINT fk_cart_items_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE RESTRICT
);

CREATE TABLE if not exists orders (
  id varchar(36) NOT NULL,
  user_id varchar(36) NOT NULL,
  product_id varchar(36) NOT NULL,
  quantity integer NOT NULL,
  total_price double precision NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT fk_orders_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE RESTRICT
);

--CREATE INDEX idx_cart_items_cart_id ON cart_items(cart_id);
--CREATE INDEX idx_cart_items_product_id ON cart_items(product_id);

-- Optionally create orders table later; this migration covers entities currently referenced by the failing validation

