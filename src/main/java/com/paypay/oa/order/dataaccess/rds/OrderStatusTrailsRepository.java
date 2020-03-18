package com.paypay.oa.order.dataaccess.rds;

import com.paypay.oa.order.domain.entity.OrderStatusTrails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusTrailsRepository extends JpaRepository<OrderStatusTrails, Long> {
}
