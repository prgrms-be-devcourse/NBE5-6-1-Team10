CREATE TABLE `order` (
                         order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT,
                         email VARCHAR(255) NOT NULL,
                         name VARCHAR(255),
                         zip_code VARCHAR(255),
                         address VARCHAR(255),
                         total_price INT,
                         order_time DATETIME,
                         order_status VARCHAR(255)
);