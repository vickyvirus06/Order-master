package com.paypay.oa.order.controller.response.order;

import com.paypay.oa.order.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderListResponse {
    private List<OrderDTO> orderList;

    @Data
    @Builder
    public static class OrderDTO {
        private Long orderId;
        private int totalPrice;
        private String currency;
        private int totalItem;
        private int prepareTime;
        private Long consumerId;
        private String pickupCode;
        private String orderRefNumber;
        private String consumerRemarks;
    }

    public static GetOrderListResponse buildGetOrderListResponse(List<Order> orderList) {
        List<OrderDTO> orderDTOList = orderList.stream().map(order -> OrderDTO.builder()
                .orderId(order.getOrderId())
                .totalPrice(order.getTotalPrice())
                .currency(order.getCurrency())
                .totalItem(order.getTotalItem())
                .prepareTime(order.getPrepareTime())
                .consumerId(order.getConsumerId())
                .pickupCode(order.getPickupCode())
                .orderRefNumber(order.getOrderRefNumber())
                .consumerRemarks(order.getConsumerRemarks())
                .build()).collect(Collectors.toList());

        return GetOrderListResponse.builder().orderList(orderDTOList).build();

    }
}
