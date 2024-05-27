CREATE TABLE orders(
        order_id varchar(250) PRIMARY KEY,
        chain_id bigint NOT NULL,
        shop_id bigint NOT NULL,
        user_id bigint NOT NULL,
        payment_method varchar(100) NOT NULL,
        delivery_address_id bigint NOT NULL,
        delivery_type varchar(100) NOT NULL CHECK (delivery_type IN ('immediate', 'specified')),
        delivery_charge decimal NOT NULL,
        non_taxed_total_price decimal NOT NULL,
        tax decimal NOT NULL,
        taxed_total_price decimal NOT NULL,
        time datetime NOT NULL,
        black_level integer NOT NULL,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL
);

CREATE TABLE order_items(
        order_id varchar(250) NOT NULL,
        item_id bigint NOT NULL,
        quantity integer NOT NULL,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL,
        PRIMARY KEY (order_id, item_id),
        FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE TABLE order_item_attributes(
        order_id varchar(250) NOT NULL,
        item_id bigint NOT NULL,
        attribute_id bigint NOT NULL,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL,
        PRIMARY KEY (order_id, item_id, attribute_id),
        FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE TABLE payment_methods(
        name varchar(250) PRIMARY KEY,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL
);