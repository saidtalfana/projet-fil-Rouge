CREATE TABLE Product (
                         product_id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255),
                         description TEXT,
                         price DOUBLE,
                         category VARCHAR(50),
                         product_status VARCHAR(50),
                         image LONGBLOB,
                         enterprise_id INT,
                         CONSTRAINT fk_enterprise
                             FOREIGN KEY (enterprise_id)
                                 REFERENCES Enterprise(enterprise_id)
                                 ON DELETE SET NULL
);

-- Optional: Index for enterprise_id for faster lookup
CREATE INDEX idx_enterprise_id ON Product(enterprise_id);
