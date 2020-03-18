package com.paypay.oa.order.application.service.orderitem;

import com.paypay.oa.order.domain.entity.OrderItem;

import java.util.List;

public interface GetOrderItemService {
    /**
     *
     * @param orderId
     * @return List
     */
    List<OrderItem> getOrderItems(Long orderId);
}
