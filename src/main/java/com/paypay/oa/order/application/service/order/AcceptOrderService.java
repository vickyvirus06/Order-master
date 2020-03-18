package com.paypay.oa.order.application.service.order;

import com.paypay.oa.order.domain.entity.Order;

public interface AcceptOrderService {

    /**
     *
     * @param order
     * @return Order
     */
    Order update(Order order);

}
