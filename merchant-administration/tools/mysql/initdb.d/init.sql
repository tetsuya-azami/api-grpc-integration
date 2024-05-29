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