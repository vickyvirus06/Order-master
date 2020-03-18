package com.paypay.oa.order.dataaccess.rds.order;

import com.paypay.oa.order.application.repository.order.GetOrderRepo;
import com.paypay.oa.order.dataaccess.rds.OrderPickupCodeRepository;
import com.paypay.oa.order.dataaccess.rds.OrderRepository;
import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.OrderPickupCode;
import com.paypay.oa.order.domain.entity.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetOrderRepositoryImpl implements GetOrderRepo {
    private final OrderRepository orderRepository;
    private final OrderPickupCodeRepository orderPickupCodeRepository;

    @Override
    public Order findByOrderIdAndStoreIdAndMerchantId(Long orderId, Long storeId, Long merchantId) {
        return orderRepository.findByOrderIdAndStoreIdAndMerchantId(orderId, storeId, merchantId);
    }

    @Override
    public Order findByOrderIdAndStoreIdAndMerchantIdAndConsumerId(Long orderId, Long storeId, Long merchantId, Long consumerId) {
        return orderRepository.findByOrderIdAndStoreIdAndMerchantIdAndConsumerId(orderId, storeId, merchantId, consumerId);
    }

    @Override
    public List<Order> findByStoreIdAndMerchantIdAndOrderStatus(Long storeId, Long merchantId, OrderStatus orderStatus) {
        return orderRepository.findByStoreIdAndMerchantIdAndOrderStatus(storeId, merchantId, orderStatus);
    }

    @Override
    public OrderPickupCode fetchOrderPickupCodeIdentifiedByStore(Long storeId) {
        return orderPickupCodeRepository.findByStoreId(storeId);
    }
}
