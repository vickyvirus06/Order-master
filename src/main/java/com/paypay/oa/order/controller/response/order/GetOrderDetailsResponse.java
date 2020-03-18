package com.paypay.oa.order.controller.response.order;

import com.fasterxml.jackson.databind.JsonNode;
import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.OrderItem;
import com.paypay.oa.order.util.JsonConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class GetOrderDetailsResponse {
    private Long orderId;
    private Long merchantId;
    private Long consumerId;
    private Long storeId;
    private Integer totalPrice;
    private String currency;
    private String orderStatus;
    private Integer totalItem;
    private Integer prepareTime;
    private String consumerRemarks;
    private List<GetOrderItemResponse> orderItems;

    @Data
    @Builder
    public static class GetOrderItemResponse {
        private Long orderItemId;
        private Long itemId;
        private Long itemStoreMapId;
        private String name;
        private Integer price;
        private Integer quantity;
        private JsonNode imageUrl;
        private JsonNode itemOptions;
    }

    public static GetOrderDetailsResponse buildGetOrderResponse(Order order, List<OrderItem> orderItems) {
        List<GetOrderItemResponse> getOrderItemResponse = orderItems.stream()
                .map(orderItem ->
                        GetOrderItemResponse.builder().orderItemId(orderItem.getOrderItemId())
                                .itemId(orderItem.getItemId())
                                .itemStoreMapId(orderItem.getItemStoreMapId())
                                .imageUrl(JsonConverter.convertStringToJsonNode(orderItem.getImageUrl()))
                                .itemId(orderItem.getItemId())
                                .name(orderItem.getName()).price(orderItem.getPrice())
                                .quantity(orderItem.getQuantity())
                                .itemOptions(Optional.ofNullable(orderItem.getOptions()).map(JsonConverter::convertStringToJsonNode).orElse(null))
                                .build())
                .collect(Collectors.toList());


        return GetOrderDetailsResponse.builder().orderId(order.getOrderId())
                .consumerId(order.getConsumerId())
                .storeId(order.getStoreId()).merchantId(order.getMerchantId())
                .totalPrice(order.getTotalPrice())
                .currency(order.getCurrency())
                .orderStatus(order.getOrderStatus().name())
                .totalItem(order.getTotalItem())
                .prepareTime(order.getPrepareTime())
                .consumerRemarks(order.getConsumerRemarks())
                .orderItems(getOrderItemResponse).build();

    }
}


