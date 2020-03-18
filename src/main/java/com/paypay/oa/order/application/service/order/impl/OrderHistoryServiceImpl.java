package com.paypay.oa.order.application.service.order.impl;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.paypay.oa.order.application.repository.order.GetOrderRepo;
import com.paypay.oa.order.application.repository.order.OrderHistoryRepo;
import com.paypay.oa.order.application.repository.orderitem.GetOrderItemRepo;
import com.paypay.oa.order.application.service.order.OrderHistoryService;
import com.paypay.oa.order.controller.response.order.OrderHistoryDetailsRepsonse;
import com.paypay.oa.order.controller.response.order.OrderHistoryDetailsRepsonse.GetOrderHistoryDetailsResponse;
import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.OrderItem;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderHistoryServiceImpl implements OrderHistoryService {
	private  final OrderHistoryRepo orderHistoryRepo;
	private  final GetOrderItemRepo getOrderItemRepo;
    
    @Override
    public GetOrderHistoryDetailsResponse getOrderHistory(Long consumerId) {

    	List<Order> orders =  orderHistoryRepo.getOrderHistory(consumerId);
    	List<OrderHistoryDetailsRepsonse> orderHistoryDetailsRepsonses = new ArrayList<>();
    	orders.forEach(order -> {
    		
    		List<OrderItem> orderItems = getOrderItemRepo.getOrderItems(order.getId());
    		orderHistoryDetailsRepsonses.add(OrderHistoryDetailsRepsonse.buildGetOrderHistoryResponse(order,orderItems));
    	});
    		//return orderHistoryDetailsRepsonses;
    		
    		return OrderHistoryDetailsRepsonse.buildGetOrderHistoryDetailsResponse(orderHistoryDetailsRepsonses);
    	
    }
}
