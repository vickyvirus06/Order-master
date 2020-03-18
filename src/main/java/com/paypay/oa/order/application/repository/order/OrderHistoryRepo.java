package com.paypay.oa.order.application.repository.order;
import com.paypay.oa.order.domain.entity.Order;
import java.util.List;

public interface OrderHistoryRepo {
	/**
	 * 
	 * 
	 * @param consumerId
	 * @return
	 */
    	List<Order> getOrderHistory(Long consumerId);
}
