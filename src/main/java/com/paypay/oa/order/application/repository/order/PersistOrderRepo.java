package com.paypay.oa.order.application.repository.order;

import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.OrderPickupCode;

public interface PersistOrderRepo {
    /**
     * save order master
     *
     * @param order
     * @return Order
     */
    Order add(Order order);

    /**
     * update order master
     *
     * @param order
     * @return Order
     */
    Order update(Order order);

    /**
     * save order pickup code entity
     * @param orderPickupCode
     * @return OrderPickupCode
     */
    OrderPickupCode add(OrderPickupCode orderPickupCode);

    /**
     * update order pickup code entity
     * @param orderPickupCode
     * @return OrderPickupCode
     */
    OrderPickupCode update(OrderPickupCode orderPickupCode);
}
