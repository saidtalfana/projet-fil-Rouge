CREATE TABLE CustomerOrder (
                               order_id INT AUTO_INCREMENT PRIMARY KEY,
                               order_date DATE,
                               order_time TIME,
                               name VARCHAR(255),
                               address VARCHAR(255),
                               email VARCHAR(255),
                               phone_number VARCHAR(255),
                               customer_request TEXT,
                               service_id INT,
                               user_id INT,
                               CONSTRAINT fk_product
                                   FOREIGN KEY (service_id)
                                       REFERENCES Product(product_id)
                                       ON DELETE SET NULL,
                               CONSTRAINT fk_user
                                   FOREIGN KEY (user_id)
                                       REFERENCES User(user_id)
                                       ON DELETE SET NULL
);

-- Optional: Indexes for service_id and user_id for faster lookups
CREATE INDEX idx_service_id ON CustomerOrder(service_id);
CREATE INDEX idx_user_id ON CustomerOrder(user_id);
