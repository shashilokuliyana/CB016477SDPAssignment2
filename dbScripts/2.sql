USE logistics_shipment_db;

UPDATE customers
SET password_hash = 'ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f'
WHERE email = 'customer@example.com';

SELECT customer_id, name, email, password_hash, phone FROM customers;

USE logistics_shipment_db;

CREATE TABLE IF NOT EXISTS delivery_reschedules (
    reschedule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    shipment_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    old_delivery_date DATE NOT NULL,
    new_delivery_date DATE NOT NULL,
    reason VARCHAR(255),
    requested_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_reschedule_shipment
        FOREIGN KEY (shipment_id)
        REFERENCES shipments(shipment_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_reschedule_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(customer_id)
        ON DELETE CASCADE
);

SELECT * FROM delivery_reschedules;