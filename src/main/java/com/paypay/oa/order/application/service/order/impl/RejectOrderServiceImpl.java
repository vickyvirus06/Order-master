package com.paypay.oa.order.application.service.order.impl;

import com.paypay.oa.order.application.repository.order.PersistOrderRepo;
import com.paypay.oa.order.application.service.order.RejectOrderService;
import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RejectOrderServiceImpl implements RejectOrderService {
    private final PersistOrderRepo persistOrderRepo;

    @Override
    public Order update(Order order, String rejectReason) {
        // update order
        order.setOrderStatus(OrderStatus.REJECTED);
        order.setRejectionReason(rejectReason);
        return persistOrderRepo.update(order);
    }
}
