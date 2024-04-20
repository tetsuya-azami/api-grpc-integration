 -- 外部キー制約のためのマスタデータ登録
 -- チェーンマスタ
 INSERT INTO chains (chain_id, name, created_at, updated_at) VALUES
 (1, 'テストチェーン1', '2000-01-01 00:00:00', '2000-01-01 00:00:00');
 -- ショップマスタ
 INSERT INTO shops (shop_id, chain_id, name, created_at, updated_at) VALUES
 (1, 1, 'テストショップ1', '2000-01-01 00:00:00', '2000-01-01 00:00:00');

 -- 商品マスタ
 INSERT INTO items (item_id, chain_id, shop_id, name, price, description, created_at, updated_at) VALUES
 (1, 1, 1, 'テスト商品1', 100, 'テスト商品1説明。', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
 (2, 1, 1, 'テスト商品2', 200, 'テスト商品2説明。', '2000-01-01 00:00:00', '2000-01-01 00:00:00'),
 (3, 1, 1, 'テスト商品3', 300, 'テスト商品3説明。', '2000-01-01 00:00:00', '2000-01-01 00:00:00');