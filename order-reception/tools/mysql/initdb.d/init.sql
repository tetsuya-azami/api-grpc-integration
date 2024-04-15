CREATE TABLE chains(
       chain_id bigint PRIMARY KEY AUTO_INCREMENT,
       name varchar(250) NOT NULL,
       created_at datetime NOT NULL,
       updated_at datetime NOT NULL
);

CREATE TABLE shops(
       shop_id bigint PRIMARY KEY AUTO_INCREMENT,
       chain_id bigint NOT NULL,
       name varchar(250) NOT NULL,
       created_at datetime NOT NULL,
       updated_at datetime NOT NULL,
       FOREIGN KEY (chain_id) REFERENCES chains(chain_id)
);

CREATE TABLE items(
        item_id bigint PRIMARY KEY AUTO_INCREMENT,
        chain_id bigint NOT NULL,
        shop_id bigint NOT NULL,
        name varchar(250) NOT NULL,
        price decimal NOT NULL,
        description varchar(500) NOT NULL,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL,
        FOREIGN KEY (chain_id) REFERENCES chains(chain_id),
        FOREIGN KEY (shop_id) REFERENCES shops(shop_id)
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

CREATE TABLE ranks(
        rank_id bigint PRIMARY KEY AUTO_INCREMENT,
        name varchar(250) NOT NULL,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL
);

CREATE TABLE users(
        user_id bigint PRIMARY KEY AUTO_INCREMENT,
        first_name varchar(250) NOT NULL,
        last_name varchar(250) NOT NULL,
        phone_number varchar(250) NOT NULL UNIQUE,
        email varchar(250) NOT NULL UNIQUE,
        password varchar(250) NOT NULL,
        birthday date NOT NULL,
        rank_id bigint NOT NULL,
        black_level integer NOT NULL DEFAULT 0,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL,
        FOREIGN KEY (rank_id) REFERENCES ranks(rank_id)
);

CREATE TABLE addresses(
        address_id bigint PRIMARY KEY AUTO_INCREMENT,
        user_id bigint NOT NULL,
        postcode varchar(250) NOT NULL,
        prefecture varchar(250) NOT NULL,
        city varchar(250) NOT NULL,
        street_address varchar(250) NOT NULL,
        building varchar(250) NOT NULL,
        created_at datetime NOT NULL,
        updated_at datetime NOT NULL,
        FOREIGN KEY (user_id) REFERENCES users(user_id)
);