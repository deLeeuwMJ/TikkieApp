CREATE TABLE IF NOT EXISTS TRANSACTIONS (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_code VARCHAR(10),
    transaction_description VARCHAR(140)
);

CREATE TABLE IF NOT EXISTS PAYMENTS (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_id INT,
    payment_sender_name VARCHAR(70),
    payment_creation_date VARCHAR(32),
    payment_amount DOUBLE,
    FOREIGN KEY (transaction_id) REFERENCES TRANSACTIONS(transaction_id)
);

CREATE TABLE IF NOT EXISTS REQUESTS (
    request_id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_id INT,
    request_sender_name VARCHAR(70),
    request_receiver_name VARCHAR(70),
    request_creation_date VARCHAR(32),
    request_amount DOUBLE,
    FOREIGN KEY (transaction_id) REFERENCES TRANSACTIONS(transaction_id)
);