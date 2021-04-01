CREATE TABLE TRANSACTIONS (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_code VARCHAR(10),
    transaction_description VARCHAR(140)
);

INSERT INTO TRANSACTIONS(transaction_id, transaction_code, transaction_description)
VALUES (1, '258wes', 'Fiets'),(2, 'qwer12', 'Eten');

CREATE TABLE PAYMENTS (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_id INT,
    payment_sender_name VARCHAR(70),
    payment_creation_date VARCHAR(32),
    payment_amount DOUBLE,
    FOREIGN KEY (transaction_id) REFERENCES TRANSACTIONS(transaction_id)
);

INSERT INTO PAYMENTS(payment_id, transaction_id, payment_sender_name, payment_creation_date, payment_amount)
VALUES (1, 1, 'Miquel de Leeuw', '03-02-2021', 50.00),(2, 2, 'Justen de Leeuw', '03-02-2021', 30.00),(3, 1, 'Wim de Leeuw', '03-02-2021', 10.00);

CREATE TABLE REQUESTS (
    request_id INT AUTO_INCREMENT PRIMARY KEY,
    transaction_id INT,
    request_sender_name VARCHAR(70),
    request_receiver_name VARCHAR(70),
    request_creation_date VARCHAR(32),
    request_amount DOUBLE,
    FOREIGN KEY (transaction_id) REFERENCES TRANSACTIONS(transaction_id)
);

INSERT INTO REQUESTS(request_id, transaction_id, request_sender_name, request_receiver_name, request_creation_date, request_amount)
VALUES (1, 1, 'Miquel de Leeuw', 'Justen de Leeuw', '03-02-2021', 20.00);