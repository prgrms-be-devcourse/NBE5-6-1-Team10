CREATE TABLE IF NOT EXISTS item
(

    item_id     BIGINT PRIMARY KEY AUTO_INCREMENT,
    item_name   VARCHAR(255) NOT NULL,
    description TEXT,
    price       INT          NOT NULL,
    image_url   VARCHAR(500),
    stock       INT          NOT NULL

);
