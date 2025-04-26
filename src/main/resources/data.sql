-- 커피 상품 데이터 예시
INSERT INTO item (item_name, description, price, sold_out)
VALUES ('아메리카노', '진한 에스프레소에 뜨거운 물을 추가한 커피', 4000, FALSE),
       ('카페라떼', '우유와 에스프레소가 조화로운 부드러운 커피', 4500, FALSE),
       ('카라멜 마키아토', '카라멜 시럽이 들어간 달콤한 커피', 5000, FALSE),
       ('카푸치노', '부드러운 우유 거품과 함께 즐기는 커피', 4700, FALSE),
       ('에스프레소', '강렬하고 진한 커피', 3000, FALSE);



INSERT INTO orders
(order_id, user_id, email, name, address, zip_code, order_status, order_time)
VALUES (100, 1, 'user1@example.com', '홍길동', '서울시 강남구 압구정로 50', '12345', 'ORDERED',
        '2025-04-25 10:00:00'),
       (101, 1, 'user1@example.com', '홍길동', '부산시 해운대구 센텀로 30', '12345', 'DELIVERED',
        '2025-04-20 15:30:00'),
       (102, NULL, 'guest1@example.com', '이영희', '대전시 서구 둔산대로 20', '54321', 'ORDERED',
        '2025-04-26 09:15:00'),
       (103, NULL, 'guest2@example.com', '박민수', '광주시 북구 무진대로 10', '98765', 'CANCELLED',
        '2025-04-24 13:45:00'),
       (104, 2, 'user2@example.com', '팥쥐', '경기도 수원시', '56789', 'DELIVERED', NOW()), INSERT
INTO order_item
    (order_item_id, order_id, item_name, quantity, unit_price)
VALUES
    (1, 100, '아메리카노', 1, 4000), (2, 100, '카페라떼', 2, 5000),
                                (3, 101, '카라멜 마키아토', 1, 5500),
                                (4, 102, '카푸치노', 1, 4500),
                                (5, 104, '에스프레소', 3, 3000);