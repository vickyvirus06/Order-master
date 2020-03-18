package com.paypay.oa.order.application.service.order;

import com.paypay.oa.order.controller.request.order.SetPaymentDetailRequest;
import com.paypay.oa.order.domain.entity.Order;

public interface OrderPaymentService {

    /**
     * To update the Order entity with payment detail
     * @param order
     * @return Order
     */
    Order update(Order order, SetPaymentDetailRequest setPaymentDetailRequest);

}
