-- Step 1: Create the `Rating` table with necessary columns and relationships.
CREATE TABLE Rating (
                        rating_id INT AUTO_INCREMENT PRIMARY KEY,
                        stars INT CHECK (stars >= 1 AND stars <= 5), -- Ensuring rating is between 1 and 5
                        comment VARCHAR(255), -- Adjust length as needed for comments
                        user_id INT,
                        product_id INT,

    -- Foreign key references
                        FOREIGN KEY (user_id) REFERENCES Person(id) ON DELETE CASCADE, -- Assuming User is a subclass of Person
                        FOREIGN KEY (product_id) REFERENCES Product(product_id) ON DELETE CASCADE
);

-- Step 2: Create indexes on user_id and product_id for performance optimization.

CREATE INDEX idx_user_id ON Rating(user_id);
CREATE INDEX idx_product_id ON Rating(product_id);
