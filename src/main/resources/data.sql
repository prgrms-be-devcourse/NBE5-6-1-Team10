CREATE TABLE IF NOT EXISTS item (
                                    item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    item_name VARCHAR(100) NOT NULL,
                                    description TEXT,
                                    price INT NOT NULL,
                                    sold_out BOOLEAN DEFAULT FALSE
);
