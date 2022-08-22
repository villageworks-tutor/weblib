/** コードマスタのデータ登録 */
-- 権限マスタのコードの登録
INSERT INTO priviledge (code, name) VALUES (0, 'システム管理者');
INSERT INTO priviledge (code, name) VALUES (1, '一般利用者');

-- 分類マスタのコードの登録
INSERT INTO category (code, name) VALUES (0, '総記');
INSERT INTO category (code, name) VALUES (1, '哲学');
INSERT INTO category (code, name) VALUES (2, '歴史');
INSERT INTO category (code, name) VALUES (3, '社会科学');
INSERT INTO category (code, name) VALUES (4, '自然科学');
INSERT INTO category (code, name) VALUES (5, '技術');
INSERT INTO category (code, name) VALUES (6, '産業');
INSERT INTO category (code, name) VALUES (7, '芸術');
INSERT INTO category (code, name) VALUES (8, '言語');
INSERT INTO category (code, name) VALUES (9, '文学');

-- 出版社マスタのコードの登録
INSERT INTO publisher (code, name) VALUES (10, '新潮社');
INSERT INTO publisher (code, name) VALUES (930795, 'SBクリエイティブ');
INSERT INTO publisher (code, name) VALUES (89052, 'SBクリエイティブ');
INSERT INTO publisher (code, name) VALUES (7973, 'SBクリエイティブ');
INSERT INTO publisher (code, name) VALUES (8156, 'SBクリエイティブ');
INSERT INTO publisher (code, name) VALUES (915673, '翔泳社');
INSERT INTO publisher (code, name) VALUES (88135, '翔泳社');
INSERT INTO publisher (code, name) VALUES (7981, '翔泳社');
INSERT INTO publisher (code, name) VALUES (320, '共立出版');
INSERT INTO publisher (code, name) VALUES (900900, 'オライリー・ジャパン');
INSERT INTO publisher (code, name) VALUES (87311, 'オライリー・ジャパン');
INSERT INTO publisher (code, name) VALUES (8144, 'オライリー・ジャパン');
INSERT INTO publisher (code, name) VALUES (8222, '日経BP');
INSERT INTO publisher (code, name) VALUES (296, '日経BP');
INSERT INTO publisher (code, name) VALUES (87408, '技術評論社');
INSERT INTO publisher (code, name) VALUES (7741, '技術評論社');
INSERT INTO publisher (code, name) VALUES (297, '技術評論社');
INSERT INTO publisher (code, name) VALUES (274, 'オーム社');
INSERT INTO publisher (code, name) VALUES (621, '丸善出版');

-- 利用者マスタテーブルのデータ登録
/**
INSERT INTO member (card, name, zipcode, address, phone, email, birthday, priviledge) VALUES ('12056692', '梅田 俊章', '232-0016', '神奈川県横浜市南区宮元町2-16-18', '080-4293-2703', 'mizuho2311@mhwuymgwsr.rp.rql', '1985-07-05',  0);
INSERT INTO member (card, name, zipcode, address, phone, email, birthday, priviledge) VALUES ('12050662', '清田 健蔵', '277-0851', '千葉県柏市向原町1-17-13', '080-3440-9925', 'yuzuki086@udtmsizh.nbl.jrml', '2001-02-01',  1);
INSERT INTO member (card, name, zipcode, address, phone, email, birthday, priviledge) VALUES ('12058021', '浜口 秋雄', '259-0201', '神奈川県足柄下郡真鶴町真鶴3-20-8', '080-4751-9498', 'fujio_tsuchiya@yfqkvmrmfr.geq.bbl', '1987-11-15',  1);
INSERT INTO member (card, name, zipcode, address, phone, email, birthday, priviledge) VALUES ('12057327', '古橋 正美', '121-0807', '東京都足立区伊興本町1-16伊興本町アパート406', '090-4613-0336', 'yoshiko_shimada@ztvzw.frig.fhbl', '1970-10-08',  0);
INSERT INTO member (card, name, zipcode, address, phone, email, birthday, priviledge) VALUES ('12052425', '吉澤 正徳', '241-0005', '神奈川県横浜市旭区白根4-6白根フォレスト216', '080-1284-7148', 'chuuzou30475@kwjs.qxl', '1980-01-27',  1);
INSERT INTO member (card, name, zipcode, address, phone, email, birthday, priviledge) VALUES ('12056107', '橘 良彦', '231-0862', '神奈川県横浜市中区山手町2-5-9山手町タワー418', '080-6431-9202', 'momokatezuka@wjhpjivinw.hgl', '1997-04-02',  1);
INSERT INTO member (card, name, zipcode, address, phone, email, birthday, priviledge) VALUES ('12058439', '浅田 薫', '350-0236', '埼玉県坂戸市花影町4-2-6', '090-1133-3877', 'ikosugi@xsrehtx.uis.ayl', '1978-04-07',  1);
*/

-- 認証テーブルのデータ登録
/**
INSERT INTO auth (card, password) VALUES ('12056692', '7ccb68e74ce41f7db7943a342890bbd43712cae0cf690705e706bd0eb56779c2');
INSERT INTO auth (card, password) VALUES ('12050662', '7e8f1f5171586a5b86bd654a858e89050f8c38460c9b16529c55c7dc1f99a4e2');
INSERT INTO auth (card, password) VALUES ('12058021', '1ad7acd335f782b4b331e465b0f00797dcf36a80785df3dbff806a1286255d9c');
INSERT INTO auth (card, password) VALUES ('12057327', '7e02576ebea9ff2fc9089afc4439910d3c202e859911ede551990904f3b53682');
INSERT INTO auth (card, password) VALUES ('12052425', 'ed316da9eb5fdf699f5979dcd6df0f907d01968cb21007fea6587248b990f350');
INSERT INTO auth (card, password) VALUES ('12056107', '9f11b239554e5f7aa45bc586007070c1b76a82ce3f157f91b9692a973366c631');
INSERT INTO auth (card, password) VALUES ('12058439', '30d42ed0a461c661761ab6b87338e9b89486a24ceb7b5c486170daf7cc169d07');
*/

-- 図書目録のデータ登録
/**
INSERT INTO book_catalogue (isbn, category_cd, title, author, publisher_cd, published_at) VALUES ('978-4621303252', 5, 'Effective Java 第3版', 'ジョシュア・ブロック@柴田 芳樹', 621, '2018-10-30');
INSERT INTO book_catalogue (isbn, category_cd, title, author, publisher_cd, published_at) VALUES ('978-4873115658', 5, 'リーダブルコード', 'ダスティン・ボズウェル@トレバー・フォシェ', 87311, '2012-06-23');
INSERT INTO book_catalogue (isbn, category_cd, title, author, publisher_cd, published_at) VALUES ('978-4798157603', 5, 'テスト駆動Python', '安井 力', 7981, '2018-08-29');
INSERT INTO book_catalogue (isbn, category_cd, title, author, publisher_cd, published_at) VALUES ('978-4815615406', 4, '数学ガールの物理ノート/波の重ね合わせ', '結城 浩', 8156, '2022-09-17');
INSERT INTO book_catalogue (isbn, category_cd, title, author, publisher_cd, published_at) VALUES ('978-4101012513', 9, '数学する人生', '岡 潔', 10, '2019-04-01');
INSERT INTO book_catalogue (isbn, category_cd, title, author, publisher_cd, published_at) VALUES ('4320031423', 4, '詳解物理応用数学演習', '後藤 憲一@山本 邦夫@神吉 健', 320, '1979-05-15');
INSERT INTO book_catalogue (isbn, category_cd, title, author, publisher_cd, published_at) VALUES ('4296000187', 5, 'オブジェクト指向でなぜつくるのか 第３版', '平澤 章', 296, '2021-04-15');
INSERT INTO book_catalogue (isbn, category_cd, title, author, publisher_cd, published_at) VALUES ('4274224546', 5, 'リファクタリング(第2版)', 'マーチン・ファウラー', 274, '2019-12-01');
INSERT INTO book_catalogue (isbn, category_cd, title, author, publisher_cd, published_at) VALUES ('978-4873117782', 5, '退屈なことはPythonにやらせよう', 'アル・スウェイガート', 87311, '2017-06-03');
INSERT INTO book_catalogue (isbn, category_cd, title, author, publisher_cd, published_at) VALUES ('978-4774183619', 5, 'オブジェクト指向設計実践ガイド', 'サンディ・メッツ', 7741, '2016-09-02');
*/