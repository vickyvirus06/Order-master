package com.paypay.oa.order.dataaccess.rds.orderstatustrails;

import com.paypay.oa.order.application.repository.orderstatustrails.PersistOrderStatusTrailsRepo;
import com.paypay.oa.order.dataaccess.rds.OrderStatusTrailsRepository;
import com.paypay.oa.order.domain.entity.OrderStatusTrails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersistOrderStatusTrailsRepositoryImpl implements PersistOrderStatusTrailsRepo {
    private final OrderStatusTrailsRepository orderStatusTrailsRepository;

    @Override
    public OrderStatusTrails add(OrderStatusTrails orderStatusTrails) {
        return orderStatusTrailsRepository.saveAndFlush(orderStatusTrails);
    }
}
