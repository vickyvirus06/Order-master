package com.paypay.oa.order.application.service.order;

import com.paypay.oa.order.controller.response.order.OrderHistoryDetailsRepsonse;
import com.paypay.oa.order.controller.response.order.OrderHistoryDetailsRepsonse.GetOrderHistoryDetailsResponse;
import com.paypay.oa.order.domain.entity.Order;

import java.util.List;

public interface OrderHistoryService {

    GetOrderHistoryDetailsResponse getOrderHistory(Long consumerId);
}
