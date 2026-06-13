USE logistics_shipment_db;

SELECT * FROM delivery_instructions;

SELECT shipment_id, tracking_id, special_instructions, last_updated
FROM shipments
WHERE shipment_id = 1;