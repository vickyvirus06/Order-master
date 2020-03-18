package com.paypay.oa.order.application.service.order;

import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.enums.OrderStatus;
import com.paypay.oa.order.exception.OAOrderManagementException;

import java.util.List;

public interface GetOrderService {
    /**
     * @param orderId
     * @param consumerId
     * @param merchantId
     * @param storeId
     * @return Order
     * @throws OAOrderManagementException
     */

    Order getOrderDetails(Long orderId, Long consumerId, Long merchantId, Long storeId) throws OAOrderManagementException;

    /**
     * @param merchantId
     * @param storeId
     * @param orderStatus
     * @return List
     * @throws OAOrderManagementException
     */

    List<Order> getOrderList(Long merchantId, Long storeId, OrderStatus orderStatus) throws OAOrderManagementException;
}
