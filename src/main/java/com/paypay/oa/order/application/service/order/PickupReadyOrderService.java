package com.paypay.oa.order.application.service.order;

import com.paypay.oa.order.domain.entity.Order;

public interface PickupReadyOrderService {

    /**
     *
     * @param order
     */
    Order update(Order order);
}
