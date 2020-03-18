package com.paypay.oa.order.application.service.orderstaustrails;

import com.paypay.oa.order.domain.entity.OrderStatusTrails;
import com.paypay.oa.order.domain.entity.enums.OrderStatus;
import com.paypay.oa.order.domain.entity.enums.Type;

public interface CreateOrderStatusTrailsService {
    /**
     *
     * @param orderId
     * @param orderStatus
     * @param type
     * @return OrderStatusTrails
     */
    OrderStatusTrails add(Long orderId, OrderStatus orderStatus, Type type);
}
