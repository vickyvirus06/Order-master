package com.paypay.oa.order.application.service.order.impl;

import com.paypay.oa.order.application.repository.order.PersistOrderRepo;
import com.paypay.oa.order.application.service.order.AddOrderService;
import com.paypay.oa.order.constants.Values;
import com.paypay.oa.order.controller.request.order.AddOrderDetailsRequest;
import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.enums.OrderStatus;
import com.paypay.oa.order.domain.entity.enums.PaymentStatus;
import com.paypay.oa.order.util.IDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddOrderServiceImpl implements AddOrderService {
    private final PersistOrderRepo persistOrderRepo;

    @Override
    public Order createOrder(AddOrderDetailsRequest addOrderDetailsRequest, Long consumerId, Long merchantId, Long storeId) {
        // add order
        return persistOrderRepo.add(setOrderEntity(addOrderDetailsRequest, consumerId, merchantId, storeId));
    }

    private Order setOrderEntity(AddOrderDetailsRequest addOrderDetailsRequest, Long consumerId, Long merchantId, Long storeId) {
        return Order.builder()
                .orderId(IDGenerator.generateId())
                .consumerId(consumerId)
                .storeId(storeId)
                .merchantId(merchantId)
                .storePickupAddressId(addOrderDetailsRequest.getStorePickupAddressId())
                .totalPrice(addOrderDetailsRequest.getTotalPrice() != null ? addOrderDetailsRequest.getTotalPrice() : Values.ZERO)
                .currency(addOrderDetailsRequest.getCurrency())
                .totalItem(addOrderDetailsRequest.getTotalItem())
                .orderStatus(OrderStatus.NEW)
                .orderDate(LocalDateTime.now())
                .paymentStatus(PaymentStatus.UNPAID)
                .consumerLatitude(addOrderDetailsRequest.getConsumerLatitude())
                .consumerLongitude(addOrderDetailsRequest.getConsumerLongitude())
                .consumerRemarks(addOrderDetailsRequest.getConsumerRemarks())
                .prepareTime(addOrderDetailsRequest.getPrepareTime())
                .build();
    }
}
