-- 커피 상품 데이터 예시
INSERT INTO item (item_id, item_name, description, price, stock_count)
VALUES
    (1, '커피머신', '최신형 커피머신', 250000, 10),
    (2, '텀블러', '스테인리스 텀블러', 30000, 50),
    (3, '원두 세트', '콜롬비아, 에티오피아 원두 세트', 45000, 100);

INSERT INTO orders (order_id, user_id, email, name, zip_code, address, order_status, order_time, total_price)
VALUES
    (1, 1, 'user1@example.com', '홍길동', '12345', '서울시 강남구', 'ORDERED', NOW(), 280000),
    (2, NULL, 'guest1@example.com', '김철수', '67890', '서울시 서초구', 'ORDERED', NOW(), 90000);

INSERT INTO order_item (order_item_id, order_id, item_id, item_name, order_cnt, price)
VALUES
    (1, 1, 1, '커피머신', 1, 250000),
    (2, 1, 2, '텀블러', 1, 30000),
    (3, 2, 3, '원두 세트', 2, 45000);