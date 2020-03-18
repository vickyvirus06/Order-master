package com.paypay.oa.order.application.repository.order;

import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.OrderPickupCode;
import com.paypay.oa.order.domain.entity.enums.OrderStatus;

import java.util.List;

public interface GetOrderRepo {
    /**
     *
     * @param orderId
     * @param storeId
     * @param merchantId
     * @return Order
     */
    Order findByOrderIdAndStoreIdAndMerchantId(Long orderId, Long storeId, Long merchantId);

    /**
     * @param storeId
     * @param merchantId
     * @param orderStatus
     * @return List
     */
    List<Order> findByStoreIdAndMerchantIdAndOrderStatus(Long storeId, Long merchantId, OrderStatus orderStatus);

    /**
     * To fetch order pickup code entity based on given store id
     * @param storeId
     * @return OrderPickupCode
     */
    OrderPickupCode fetchOrderPickupCodeIdentifiedByStore(Long storeId);

    /**
     *
     * @param orderId
     * @param storeId
     * @param merchantId
     * @param consumerId
     * @return Order
     */
    Order findByOrderIdAndStoreIdAndMerchantIdAndConsumerId(Long orderId, Long storeId, Long merchantId, Long consumerId);
}
