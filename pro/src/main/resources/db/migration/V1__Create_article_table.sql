CREATE TABLE Article (
                         article_id INT AUTO_INCREMENT PRIMARY KEY,
                         article_title VARCHAR(1000),
                         article_content TEXT,
                         article_author VARCHAR(1000),
                         article_date DATE,
                         article_type VARCHAR(1000),
                         article_image LONGBLOB
);
