-- -- 外部キー制約のためのマスタデータ登録
-- INSERT INTO ranks (name, created_at, updated_at) VALUES
-- ('regular', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
-- ('bronze', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
-- ('silver', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
-- ('gold', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
--
-- INSERT INTO users (user_id, first_name, last_name, phone_number, email, password, birthday, rank_id, created_at, updated_at) VALUES
-- (1, '太郎', '山田', '090-1234-5678', 'test@example.com', 'password', '2000-01-01', 1, '2000-01-01 00:00:00', '2000-01-01 00:00:00');
--
-- INSERT INTO addresses (address_id, user_id, postcode, prefecture, city, street_address, building, created_at, updated_at) VALUES
-- (1, 1, '100-0001', '東京都', '千代田区', '千代田1-1-1', '千代田ビル1F', '2000-01-01 00:00:00', '2000-01-01 00:00:00');