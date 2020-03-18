package com.paypay.oa.order.application.repository.orderitem;

import com.paypay.oa.order.domain.entity.OrderItem;

import java.util.List;

public interface GetOrderItemRepo {
    /**
     * @param orderId
     * @return List
     */
    List<OrderItem> getOrderItems(Long orderId);
}
