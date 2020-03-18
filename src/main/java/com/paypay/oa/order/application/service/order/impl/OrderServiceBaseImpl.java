package com.paypay.oa.order.application.service.order.impl;

import com.paypay.oa.order.application.repository.order.GetOrderRepo;
import com.paypay.oa.order.application.repository.order.PersistOrderRepo;
import com.paypay.oa.order.application.service.order.OrderServiceBase;
import com.paypay.oa.order.controller.response.ResultInfoDto;
import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.OrderPickupCode;
import com.paypay.oa.order.exception.OAOrderManagementException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceBaseImpl implements OrderServiceBase {
    private final GetOrderRepo getOrderRepo;
    private final PersistOrderRepo persistOrderRepo;

    @Override
    public <T> void isObjectNull(T object, ResultInfoDto resultInfoDto) {
        if (Objects.isNull(object)) throw new OAOrderManagementException(resultInfoDto);
    }

    @Override
    public Order fetchOrderEntity(Long orderId, Long storeId, Long merchantId) {
        return getOrderRepo.findByOrderIdAndStoreIdAndMerchantId(orderId, storeId, merchantId);
    }

    @Override
    public Order fetchOrderEntity(Long orderId, Long storeId, Long merchantId, Long consumerId) {
        return getOrderRepo.findByOrderIdAndStoreIdAndMerchantIdAndConsumerId(orderId, storeId, merchantId, consumerId);
    }

    @Override
    public OrderPickupCode fetchOrderPickupCodeEntity(Long storeId) {
        return getOrderRepo.fetchOrderPickupCodeIdentifiedByStore(storeId);
    }

    @Override
    public OrderPickupCode createOrderPickupCode(OrderPickupCode orderPickupCode) {
        return persistOrderRepo.add(orderPickupCode);
    }

    @Override
    public OrderPickupCode updateOrderPickCode(OrderPickupCode orderPickupCode) {
        return persistOrderRepo.update(orderPickupCode);
    }
}
