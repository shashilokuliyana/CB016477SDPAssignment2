CREATE DATABASE IF NOT EXISTS logistics_shipment_db;
USE logistics_shipment_db;

CREATE USER IF NOT EXISTS 'logistics_app'@'localhost' IDENTIFIED BY 'Logistics@123';
GRANT ALL PRIVILEGES ON logistics_shipment_db.* TO 'logistics_app'@'localhost';
FLUSH PRIVILEGES;

DROP TABLE IF EXISTS notifications;
DROP TABLE IF EXISTS shipments;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE shipments (
    shipment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tracking_id VARCHAR(50) NOT NULL UNIQUE,
    customer_id BIGINT NOT NULL,
    origin VARCHAR(150) NOT NULL,
    destination VARCHAR(150) NOT NULL,
    status VARCHAR(50) NOT NULL,
    expected_delivery_date DATE NOT NULL,
    driver_name VARCHAR(100),
    special_instructions VARCHAR(500),
    last_updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_shipments_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(customer_id)
        ON DELETE CASCADE
);

CREATE TABLE notifications (
    notification_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    shipment_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    message VARCHAR(255) NOT NULL,
    notification_type VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_notifications_shipment
        FOREIGN KEY (shipment_id)
        REFERENCES shipments(shipment_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_notifications_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(customer_id)
        ON DELETE CASCADE
);

INSERT INTO customers (name, email, password_hash, phone)
VALUES
('Demo Customer', 'customer@example.com', '$2y$10$k01wKadMIPyyhZyRml5pruxWypwPGLXPOEYUUkQHf7/LoaAv7Ymha', '0211234567');

INSERT INTO shipments (
    tracking_id,
    customer_id,
    origin,
    destination,
    status,
    expected_delivery_date,
    driver_name,
    special_instructions,
    last_updated
)
VALUES
('TRK1001', 1, 'Auckland Distribution Centre', '12 Queen Street, Auckland', 'OUT_FOR_DELIVERY', '2026-06-15', 'David Perera', 'Leave at front door if no one is home.', NOW()),
('TRK1002', 1, 'Hamilton Warehouse', '25 Lake Road, Auckland', 'IN_TRANSIT', '2026-06-17', 'Sarah Wilson', NULL, NOW()),
('TRK1003', 1, 'Wellington Depot', '8 Victoria Street, Auckland', 'DELAYED', '2026-06-18', 'Michael Lee', 'Call customer before delivery.', NOW());

INSERT INTO notifications (shipment_id, customer_id, message, notification_type, created_at)
VALUES
(1, 1, 'Your shipment TRK1001 is out for delivery.', 'STATUS', NOW()),
(2, 1, 'Your shipment TRK1002 is currently in transit.', 'STATUS', NOW()),
(3, 1, 'Your shipment TRK1003 has been delayed due to operational reasons.', 'DELAY', NOW());

SELECT * FROM customers;
SELECT * FROM shipments;
SELECT * FROM notifications;