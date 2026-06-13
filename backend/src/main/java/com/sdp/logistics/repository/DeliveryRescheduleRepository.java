package com.sdp.logistics.repository;

import com.sdp.logistics.entity.DeliveryReschedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRescheduleRepository extends JpaRepository<DeliveryReschedule, Long> {

    List<DeliveryReschedule> findByCustomerCustomerId(Long customerId);

    List<DeliveryReschedule> findByShipmentShipmentId(Long shipmentId);
}