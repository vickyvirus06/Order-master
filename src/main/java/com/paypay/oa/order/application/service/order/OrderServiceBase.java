package com.paypay.oa.order.application.service.order;

import com.paypay.oa.order.controller.response.ResultInfoDto;
import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.OrderPickupCode;

public interface OrderServiceBase {
    /**
     * To verify if the given object is null
     * @param object
     * @param resultInfoDto
     * @param <T>
     */
    <T> void isObjectNull(T object, ResultInfoDto resultInfoDto);

    /**
     * To fetch Order entity based on the given parameters
     * @param orderId
     * @param storeId
     * @param merchantId
     * @return Order
     */
    Order fetchOrderEntity(Long orderId, Long storeId, Long merchantId);

    /**
     * To fetch Order entity based on the given parameters
     * @param orderId
     * @param storeId
     * @param merchantId
     * @param consumerId
     * @return Order
     */
    Order fetchOrderEntity(Long orderId, Long storeId, Long merchantId, Long consumerId);

    /**
     * To fetch OrderPickupCode entity based on store id
     * @param storeId
     * @return OrderPickupCode
     */
    OrderPickupCode fetchOrderPickupCodeEntity(Long storeId);

    /**
     * Create pickup code entity specific to given store
     * @param orderPickupCode
     * @return OrderPickupCode
     */
    OrderPickupCode createOrderPickupCode(OrderPickupCode orderPickupCode);

    /**
     * To update order pickup code entity specific to given store
     * @param orderPickupCode
     * @return OrderPickupCode
     */
    OrderPickupCode updateOrderPickCode(OrderPickupCode orderPickupCode);
}
