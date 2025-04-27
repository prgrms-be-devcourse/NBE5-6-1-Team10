CREATE TABLE user (
                      user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      email VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      name VARCHAR(100) NOT NULL,
                      zip_code VARCHAR(20),
                      address VARCHAR(255),
                      role VARCHAR(50) NOT NULL
);
CREATE TABLE item (
                      item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      item_name VARCHAR(255) NOT NULL,
                      description TEXT,
                      price INT NOT NULL,
                      image_url VARCHAR(500) ,
                      stock_cnt INT DEFAULT 100
);
CREATE TABLE `order` (
                         order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT,
                         email VARCHAR(255),
                         name VARCHAR(255),
                         zip_code VARCHAR(20),
                         address VARCHAR(255),
                         total_price INT,
                         order_time DATETIME,
                         order_status VARCHAR(50),
                         CONSTRAINT fk_order_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE SET NULL
);
CREATE TABLE order_item (
                            order_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            order_id BIGINT NOT NULL,
                            item_id BIGINT NOT NULL,
                            order_cnt INT NOT NULL,
                            price INT NOT NULL,
                            CONSTRAINT fk_orderitem_order FOREIGN KEY (order_id) REFERENCES `order`(order_id) ON DELETE CASCADE,
                            CONSTRAINT fk_orderitem_item FOREIGN KEY (item_id) REFERENCES item(item_id) ON DELETE CASCADE
);