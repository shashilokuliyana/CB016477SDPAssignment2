package com.sdp.logistics.repository;

import com.sdp.logistics.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByCustomerCustomerIdOrderByCreatedAtDesc(Long customerId);

    List<Notification> findByShipmentShipmentIdOrderByCreatedAtDesc(Long shipmentId);
}