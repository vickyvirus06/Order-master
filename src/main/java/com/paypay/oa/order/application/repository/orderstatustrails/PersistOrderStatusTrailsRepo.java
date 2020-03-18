package com.paypay.oa.order.application.repository.orderstatustrails;

import com.paypay.oa.order.domain.entity.OrderStatusTrails;

public interface PersistOrderStatusTrailsRepo {
    /**
     * save order status trails
     *
     * @param orderStatusTrails
     * @return Type
     */
    OrderStatusTrails add(OrderStatusTrails orderStatusTrails);
}
