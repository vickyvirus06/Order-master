package com.paypay.oa.order.dataaccess.rds.orderitem;

import com.paypay.oa.order.application.repository.orderitem.GetOrderItemRepo;
import com.paypay.oa.order.dataaccess.rds.OrderItemRepository;
import com.paypay.oa.order.domain.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetOrderItemRepositoryImpl implements GetOrderItemRepo {
    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getOrderItems(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}
