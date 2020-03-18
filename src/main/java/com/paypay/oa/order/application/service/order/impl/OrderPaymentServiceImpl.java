package com.paypay.oa.order.application.service.order.impl;

import com.paypay.oa.order.application.repository.order.PersistOrderRepo;
import com.paypay.oa.order.application.service.order.OrderPaymentService;
import com.paypay.oa.order.constants.OrderManagementCode;
import com.paypay.oa.order.controller.request.order.SetPaymentDetailRequest;
import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.enums.OrderStatus;
import com.paypay.oa.order.domain.entity.enums.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderPaymentServiceImpl implements OrderPaymentService {
    private final PersistOrderRepo persistOrderRepo;

    @Override
    public Order update(Order order, SetPaymentDetailRequest setPaymentDetailRequest) {
        // update order
        if (setPaymentDetailRequest.getPaymentStatus().equalsIgnoreCase(OrderManagementCode.ORDER_PAYMENT_SUCCESS)) {
            order.setPaymentStatus(PaymentStatus.PAID);
            order.setPaymentId(setPaymentDetailRequest.getPaymentId());
            order.setPaymentRefNumber(setPaymentDetailRequest.getPaymentRefNumber());
            order.setPaymentDate(setPaymentDetailRequest.getPaymentDate());
            order.setOrderStatus(OrderStatus.CONFIRMED);
        } else {
            order.setPaymentStatus(PaymentStatus.FAILEDPAYMENT);
        }
        return persistOrderRepo.update(order);
    }

}
