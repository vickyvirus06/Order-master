package com.paypay.oa.order.dataaccess.rds.order;

import com.paypay.oa.order.application.repository.order.PersistOrderRepo;
import com.paypay.oa.order.dataaccess.rds.OrderPickupCodeRepository;
import com.paypay.oa.order.dataaccess.rds.OrderRepository;
import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.OrderPickupCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersistOrderRepositoryImpl implements PersistOrderRepo {
    private final OrderRepository orderRepository;
    private final OrderPickupCodeRepository orderPickupCodeRepository;

    @Override
    public Order add(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public OrderPickupCode add(OrderPickupCode orderPickupCode) {
        return orderPickupCodeRepository.save(orderPickupCode);
    }

    @Override
    public OrderPickupCode update(OrderPickupCode orderPickupCode) {
        return orderPickupCodeRepository.saveAndFlush(orderPickupCode);
    }
}
