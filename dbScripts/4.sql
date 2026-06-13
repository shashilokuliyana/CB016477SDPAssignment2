USE logistics_shipment_db;

CREATE TABLE IF NOT EXISTS delivery_instructions (
    instruction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    shipment_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    instruction_text VARCHAR(500) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NULL,

    CONSTRAINT fk_instruction_shipment
        FOREIGN KEY (shipment_id)
        REFERENCES shipments(shipment_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_instruction_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(customer_id)
        ON DELETE CASCADE
);

SELECT * FROM delivery_instructions;