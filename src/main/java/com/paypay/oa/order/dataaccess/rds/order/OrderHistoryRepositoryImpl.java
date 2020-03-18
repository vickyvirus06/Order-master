package com.paypay.oa.order.dataaccess.rds.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypay.oa.order.application.repository.order.OrderHistoryRepo;
import com.paypay.oa.order.dataaccess.rds.OrderRepository;
import com.paypay.oa.order.domain.entity.Order;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Service
@Data
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderHistoryRepositoryImpl implements OrderHistoryRepo {
	private final OrderRepository orderRepo;
	
	public List<Order> getOrderHistory(Long consumerId) {

   	return orderRepo.findByConsumerId(consumerId);

}
}