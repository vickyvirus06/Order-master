package com.paypay.oa.order.application.service.orderitem;

import com.paypay.oa.order.controller.request.order.AddOrderDetailsRequest;

public interface AddOrderItemService {
    /**
     *
     * @param addOrderDetailsRequest
     * @param orderId
     */
    void addOrderItems(AddOrderDetailsRequest addOrderDetailsRequest, Long orderId);
}
