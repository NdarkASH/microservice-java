-- Buat tipe enum untuk payment_method
CREATE TYPE payment_method AS ENUM (
    'DANA',
    'GOPAY',
    'OVO',
    'BITCOIN',
    'MASTERCARD',
    'PAYPAL'
);

-- Tabel untuk customer_order (Order)
CREATE TABLE customer_order (
                                id SERIAL PRIMARY KEY,
                                reference VARCHAR(255),
                                total_amount NUMERIC(19,2),
                                payment_method payment_method,
                                customer_id VARCHAR(255),
                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabel untuk customer_line (OrderLine)
CREATE TABLE customer_line (
                               id SERIAL PRIMARY KEY,
                               order_id INTEGER,
                               product_id INTEGER,
                               quantity DOUBLE PRECISION,
                               CONSTRAINT fk_order
                                   FOREIGN KEY(order_id)
                                       REFERENCES customer_order(id)
                                       ON DELETE CASCADE
);
