CREATE TABLE public.products (
	id bigserial NOT NULL,
	"name" varchar(255) NOT NULL,
	price float8 NOT NULL,
	CONSTRAINT products_pkey PRIMARY KEY (id)
);

CREATE TABLE public.orders (
	id bigserial NOT NULL,
	created_at timestamp NOT NULL,
	email varchar(255) NULL,
	total_price float8 NOT NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id)
);

CREATE TABLE public.order_products (
	order_id int8 NOT NULL,
	product_id int8 NOT NULL
);
ALTER TABLE public.order_products ADD CONSTRAINT fkawxpt1ns1sr7al76nvjkv21of FOREIGN KEY (order_id) REFERENCES orders(id);
ALTER TABLE public.order_products ADD CONSTRAINT fkdxjduvg7991r4qja26fsckxv8 FOREIGN KEY (product_id) REFERENCES products(id);

INSERT INTO public.products
(id, "name", price)
VALUES(1000, 'name1', 10.0);
INSERT INTO public.products
(id, "name", price)
VALUES(2000, 'name2', 20.0);
INSERT INTO public.products
(id, "name", price)
VALUES(3000, 'name3', 30.0);
INSERT INTO public.products
(id, "name", price)
VALUES(4000, 'name4', 40.0);

INSERT INTO public.orders
(id, created_at, email, total_price)
VALUES(1000, '2021-07-05 15:08:45.342', 'test1@test.com', 30.0);
INSERT INTO public.orders
(id, created_at, email, total_price)
VALUES(2000, '2021-07-05 15:09:06.181', 'test2@test.com', 20.0);

INSERT INTO public.order_products
(order_id, product_id)
VALUES(1000, 1000);
INSERT INTO public.order_products
(order_id, product_id)
VALUES(1000, 2000);
INSERT INTO public.order_products
(order_id, product_id)
VALUES(2000, 2000);
