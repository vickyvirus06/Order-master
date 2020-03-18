package com.paypay.oa.order.application.repository.orderitem;

import com.paypay.oa.order.domain.entity.OrderItem;

public interface PersistOrderItemRepo {
    /**
     * save order item
     *
     * @param orderItem
     * @return OrderItem
     */
    OrderItem add(OrderItem orderItem);
}
