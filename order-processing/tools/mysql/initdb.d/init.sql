CREATE TABLE orders(
        order_id integer PRIMARY KEY AUTO_INCREMENT,
        chain_id integer NOT NULL,
        shop_id integer NOT NULL,
        user_id integer NOT NULL,
        payment_method varchar(100) NOT NULL,
        delivery_address_id integer NOT NULL,
        delivery_type varchar(100) NOT NULL CHECK (delivery_type IN ('immediate', 'specified')),
        delivery_method_id integer NOT NULL,
        delivery_charge decimal NOT NULL,
        non_taxed_total_price decimal NOT NULL,
        tax decimal NOT NULL,
        taxed_total_price decimal NOT NULL,
        time datetime NOT NULL,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL
);