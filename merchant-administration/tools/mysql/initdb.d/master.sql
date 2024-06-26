-- -- 外部キー制約のためのマスタデータ登録
-- -- チェーンマスタ
-- INSERT INTO chains (chain_id, name, created_at, updated_at) VALUES
-- (1, 'テストチェーン1', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
-- -- ショップマスタ
-- INSERT INTO shops (shop_id, chain_id, name, created_at, updated_at) VALUES
-- (1, 1, 'テストショップ1', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
--
-- -- 商品マスタ
-- INSERT INTO items (item_id, chain_id, shop_id, name, price, description, created_at, updated_at) VALUES
-- (1, 1, 1, '消しゴム', 150, '消しゴムです。', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
-- (2, 1, 1, 'トンボ鉛筆 鉛筆 8900', 472, 'トンボ鉛筆 鉛筆 8900です。', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
-- (3, 1, 1, 'トンボ鉛筆 消しゴム付き鉛筆2558', 619, 'トンボ鉛筆 消しゴム付き鉛筆2558です。', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
--
-- INSERT INTO attributes (name, value, created_at, updated_at) VALUES
-- ('サイズ', 'S', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
-- ('販売単位', '1ダース(12本入り)', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
-- ('芯強度', 'HB', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
-- ('商品タイプ', '先削りなし(削り器必要)', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
--
-- INSERT INTO item_attributes (item_id, attribute_id, created_at, updated_at) VALUES
-- (1, 1, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
-- (2, 2, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
-- (2, 3, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
-- (3, 2, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
-- (3, 3, '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
-- (3, 4, '2000-01-01 00:00:00', '2000-01-01 00:00:00');