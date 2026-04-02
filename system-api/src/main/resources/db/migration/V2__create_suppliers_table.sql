CREATE TABLE suppliers (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(150) NOT NULL,
                           contact_email VARCHAR(150),
                           created_at TIMESTAMP DEFAULT NOW()
);