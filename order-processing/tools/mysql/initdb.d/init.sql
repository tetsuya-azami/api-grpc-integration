CREATE TABLE orders(
        order_id bigint PRIMARY KEY AUTO_INCREMENT,
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
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL
);

CREATE TABLE items(
        item_id bigint PRIMARY KEY AUTO_INCREMENT,
        chain_id bigint NOT NULL,
        shop_id bigint NOT NULL,
        name varchar(250) NOT NULL,
        price decimal NOT NULL,
        description varchar(500) NOT NULL,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL
);

CREATE TABLE order_items(
        order_id bigint NOT NULL,
        item_id bigint NOT NULL,
        quantity integer NOT NULL,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL,
        PRIMARY KEY (order_id, item_id),
        FOREIGN KEY (order_id) REFERENCES orders(order_id),
        FOREIGN KEY (item_id) REFERENCES items(item_id)
);

CREATE TABLE attributes(
        attribute_id bigint PRIMARY KEY AUTO_INCREMENT,
        name varchar(250) NOT NULL,
        value varchar(250) NOT NULL,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL
);

CREATE TABLE item_attributes(
        item_id bigint NOT NULL,
        attribute_id bigint NOT NULL,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL,
        PRIMARY KEY (item_id, attribute_id),
        FOREIGN KEY (item_id) REFERENCES items(item_id),
        FOREIGN KEY (attribute_id) REFERENCES attributes(attribute_id)
);

CREATE TABLE order_item_attributes(
        order_id bigint NOT NULL,
        item_id bigint NOT NULL,
        attribute_id bigint NOT NULL,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL,
        PRIMARY KEY (order_id, item_id, attribute_id),
        FOREIGN KEY (order_id) REFERENCES orders(order_id),
        FOREIGN KEY (item_id) REFERENCES items(item_id),
        FOREIGN KEY (attribute_id) REFERENCES attributes(attribute_id)
);

CREATE TABLE payment_methods(
        name varchar(250) PRIMARY KEY,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL
);

-- TODO: Add Chain, Shop, User, Address, and other tables
-- TODO: Add connstraints and indexes