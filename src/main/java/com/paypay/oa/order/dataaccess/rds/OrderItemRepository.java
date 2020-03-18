package com.paypay.oa.order.dataaccess.rds;

import com.paypay.oa.order.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    /**
     * @param orderId
     * @return List
     */
    List<OrderItem> findByOrderId(Long orderId);
}
