USE logistics_shipment_db;

SELECT * FROM delivery_reschedules;
SELECT shipment_id, tracking_id, status, expected_delivery_date, last_updated
FROM shipments
WHERE shipment_id = 1;