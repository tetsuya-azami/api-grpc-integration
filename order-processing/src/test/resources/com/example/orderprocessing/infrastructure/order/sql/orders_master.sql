-- 外部キー制約のためのマスタデータ登録
-- 商品マスタ
INSERT INTO items (item_id, chain_id, shop_id, name, price, description, created_at, updated_at) VALUES
(1, 1, 1, 'テスト商品1', 150, 'テスト商品1説明', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
(2, 1, 1, 'テスト商品2', 200, 'テスト商品2説明', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
(3, 1, 1, 'テスト商品3', 300, 'テスト商品3説明', '2000-01-01 00:00:00', '2000-01-01 00:00:00');

INSERT INTO attributes (name, value, created_at, updated_at) VALUES
('芯硬度', '2B', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
('芯硬度', 'HB', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
('芯硬度', 'B', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
('販売単位', '一箱(12本入り)', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
('販売単位', '1ダース(12本入り)', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
('販売単位', '1セット(3ダース: 12本入りx3)', '2000-01-01 00:00:00', '2000-01-01 00:00:00');

INSERT INTO item_attributes (item_id, attribute_id, created_at, updated_at) VALUES
(1, 1, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
(1, 2, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
(1, 3, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
(1, 4, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
(1, 5, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
(1, 6, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
(2, 1, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
(2, 2, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
(2, 3, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
(2, 4, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
(2, 5, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
(2, 6, '2000-01-01 00:00:00', '2000-01-01 00:00:00');