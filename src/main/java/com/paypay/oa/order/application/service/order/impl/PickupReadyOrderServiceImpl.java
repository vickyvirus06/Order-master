package com.paypay.oa.order.application.service.order.impl;

import com.paypay.oa.order.application.repository.order.PersistOrderRepo;
import com.paypay.oa.order.application.service.order.OrderServiceBase;
import com.paypay.oa.order.application.service.order.PickupReadyOrderService;
import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.enums.OrderStatus;
import com.paypay.oa.order.util.PickupCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PickupReadyOrderServiceImpl implements PickupReadyOrderService {
    private final PersistOrderRepo persistOrderRepo;
    private final OrderServiceBase orderServiceBase;

    @Override
    @Transactional
    public Order update(Order order) {
        // update order
        order.setOrderStatus(OrderStatus.READYFORPICKUP);
        order.setPickupCode(PickupCodeGenerator.generatePickupCode(order.getStoreId(), orderServiceBase));
        return persistOrderRepo.update(order);
    }
}
