USE logistics_shipment_db;

SELECT notification_id, shipment_id, customer_id, message, notification_type, created_at
FROM notifications
ORDER BY created_at DESC;