package com.paypay.oa.order.dataaccess.rds.orderitem;

import com.paypay.oa.order.application.repository.orderitem.PersistOrderItemRepo;
import com.paypay.oa.order.dataaccess.rds.OrderItemRepository;
import com.paypay.oa.order.domain.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersistOrderItemRepositoryImpl implements PersistOrderItemRepo {
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItem add(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
