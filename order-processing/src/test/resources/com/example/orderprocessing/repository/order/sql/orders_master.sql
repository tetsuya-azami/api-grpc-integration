-- 外部キー制約のためのマスタデータ登録
-- 注文マスタ
INSERT INTO orders (order_id, chain_id, shop_id, user_id, payment_method, delivery_address_id, delivery_type, delivery_charge, non_taxed_total_price, tax, taxed_total_price, time, created_at, updated_at)
VALUES(1, 1, 1, 1, 'cash', 1, 'immediate', 350, '1000', '100', 1595, '2000-01-01 00:00:00', '2000-01-01 00:00:00', '2000-01-01 00:00:00');

-- 商品マスタ
INSERT INTO items (item_id, chain_id, shop_id, name, price, description, created_at, updated_at) VALUES
(1, 1, 1, 'テスト商品', 150, 'テスト商品説明', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
(2, 1, 1, 'テスト商品2', 200, 'テスト商品説明2', '2000-01-01 00:00:00', '2000-01-01 00:00:00');

