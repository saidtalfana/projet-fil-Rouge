CREATE TABLE Enterprise (
                            enterprise_id INT AUTO_INCREMENT PRIMARY KEY,
                            enterprise_name VARCHAR(255),
                            enterprise_description TEXT,
                            enterprise_logo VARCHAR(255),
                            activity VARCHAR(255),
                            phone_number VARCHAR(255),
                            email VARCHAR(255),
                            provider_id INT,
                            CONSTRAINT fk_provider
                                FOREIGN KEY (provider_id)
                                    REFERENCES Provider(provider_id)
                                    ON DELETE SET NULL
);

-- Optional: You can also create an index on provider_id if you need faster lookup.
CREATE INDEX idx_provider_id ON Enterprise(provider_id);
