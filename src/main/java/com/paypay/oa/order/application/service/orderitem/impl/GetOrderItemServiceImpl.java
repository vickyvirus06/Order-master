package com.paypay.oa.order.application.service.orderitem.impl;

import com.paypay.oa.order.application.repository.orderitem.GetOrderItemRepo;
import com.paypay.oa.order.application.service.orderitem.GetOrderItemService;
import com.paypay.oa.order.domain.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetOrderItemServiceImpl implements GetOrderItemService {
    private final GetOrderItemRepo getOrderItemRepo;

    @Override
    public List<OrderItem> getOrderItems(Long orderId) {
        return getOrderItemRepo.getOrderItems(orderId);
    }
}
