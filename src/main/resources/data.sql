-- 사용자 테이블
CREATE TABLE user (
                      user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      zip_code VARCHAR(20),
                      role VARCHAR(20) NOT NULL
);
-- 상품 테이블
CREATE TABLE item (
                      item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      item_name VARCHAR(255) NOT NULL,
                      image_url VARCHAR(255),
                      description TEXT,
                      price INT NOT NULL,
                      stock_count INT NOT NULL
);
-- 주문 테이블
CREATE TABLE orders (
                        order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT,
                        email VARCHAR(255) NOT NULL,
                        zip_code VARCHAR(20),
                        address VARCHAR(255),
                        order_status VARCHAR(20) NOT NULL,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        order_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        name VARCHAR(255),
                        total_price INT DEFAULT 0,
                        FOREIGN KEY (user_id) REFERENCES user(user_id)
);
-- 주문 상세 테이블
CREATE TABLE order_item (
                            order_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            order_id BIGINT NOT NULL,
                            item_id BIGINT NOT NULL,
                            item_name VARCHAR(255) NOT NULL,
                            order_cnt INT NOT NULL,
                            price INT NOT NULL,
                            total_price INT DEFAULT 0,

                            FOREIGN KEY (order_id) REFERENCES orders(order_id),
                            FOREIGN KEY (item_id) REFERENCES item(item_id)
);