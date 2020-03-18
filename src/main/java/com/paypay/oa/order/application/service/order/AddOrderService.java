package com.paypay.oa.order.application.service.order;

import com.paypay.oa.order.controller.request.order.AddOrderDetailsRequest;
import com.paypay.oa.order.domain.entity.Order;

public interface AddOrderService {
    /**
     * @param addOrderDetailsRequest
     * @return Order
     */
    Order createOrder(AddOrderDetailsRequest addOrderDetailsRequest, Long consumerId, Long merchantId, Long storeId);

}
