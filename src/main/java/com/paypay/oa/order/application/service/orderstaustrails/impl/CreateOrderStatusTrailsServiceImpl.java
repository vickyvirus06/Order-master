package com.paypay.oa.order.application.service.orderstaustrails.impl;

import com.paypay.oa.order.application.repository.orderstatustrails.PersistOrderStatusTrailsRepo;
import com.paypay.oa.order.application.service.orderstaustrails.CreateOrderStatusTrailsService;
import com.paypay.oa.order.domain.entity.OrderStatusTrails;
import com.paypay.oa.order.domain.entity.enums.OrderStatus;
import com.paypay.oa.order.domain.entity.enums.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateOrderStatusTrailsServiceImpl implements CreateOrderStatusTrailsService {
    private final PersistOrderStatusTrailsRepo persistOrderStatusTrailsRepo;

    public OrderStatusTrails add(Long orderId, OrderStatus orderStatus, Type type) {
        return persistOrderStatusTrailsRepo.add(prepareOrderStatusTrailEntity(orderId, orderStatus, type));
    }

    /**
     *
     * @param orderId
     * @param orderStatus
     * @param type
     * @return
     */
    private OrderStatusTrails prepareOrderStatusTrailEntity(Long orderId, OrderStatus orderStatus, Type type) {
        OrderStatusTrails orderStatusTrails = new OrderStatusTrails();
        orderStatusTrails.setOrderId(orderId);
        orderStatusTrails.setOrderStatus(orderStatus);
        orderStatusTrails.setType(type);
        return orderStatusTrails;
    }
}
