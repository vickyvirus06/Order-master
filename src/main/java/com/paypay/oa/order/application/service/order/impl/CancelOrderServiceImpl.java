package com.paypay.oa.order.application.service.order.impl;

import com.paypay.oa.order.application.repository.order.PersistOrderRepo;
import com.paypay.oa.order.application.service.order.CancelOrderService;
import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CancelOrderServiceImpl implements CancelOrderService {
    private final PersistOrderRepo persistOrderRepo;

    @Override
    @Transactional
    public Order update(Order order) {
        // update order
        order.setOrderStatus(OrderStatus.CANCELLED);
        return persistOrderRepo.update(order);
    }
}
