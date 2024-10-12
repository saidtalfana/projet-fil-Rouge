-- Step 1: Create the `Person` table with single-table inheritance strategy.
-- The `person_type` column is used for discrimination between different person types (e.g., PROVIDER, USER).

CREATE TABLE Person (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255),
                        username VARCHAR(255),
                        email VARCHAR(255),
                        password VARCHAR(255),
                        roles VARCHAR(50),
                        person_type VARCHAR(50) NOT NULL  -- This will store the discriminator value for inheritance
);

-- Step 2: Modify the `Enterprise` table to include a foreign key for the `Provider`.
-- This establishes the relationship between `Provider` (a subclass of Person) and `Enterprise`.

ALTER TABLE Enterprise
    ADD COLUMN provider_id INT,
ADD CONSTRAINT fk_provider
    FOREIGN KEY (provider_id)
    REFERENCES Person(id)
    ON DELETE SET NULL;

-- Step 3: Create an index on `provider_id` to optimize queries involving providers and enterprises.

CREATE INDEX idx_provider_id ON Enterprise(provider_id);
