package com.paypay.oa.order.application.service.order.impl;

import com.paypay.oa.order.application.repository.order.GetOrderRepo;
import com.paypay.oa.order.application.service.order.GetOrderService;
import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.enums.OrderStatus;
import com.paypay.oa.order.exception.OAOrderManagementException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetOrderServiceImpl implements GetOrderService {
    private final GetOrderRepo getOrderRepo;

    @Override
    public Order getOrderDetails(Long orderId, Long consumerId, Long merchantId, Long storeId) throws OAOrderManagementException {
        return getOrderRepo.findByOrderIdAndStoreIdAndMerchantIdAndConsumerId(orderId, storeId, merchantId, consumerId);
    }

    @Override
    public List<Order> getOrderList(Long merchantId, Long storeId, OrderStatus orderStatus) throws OAOrderManagementException {
        return getOrderRepo.findByStoreIdAndMerchantIdAndOrderStatus(storeId, merchantId, orderStatus);
    }
}
