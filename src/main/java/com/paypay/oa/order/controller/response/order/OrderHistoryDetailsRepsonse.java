package com.paypay.oa.order.controller.response.order;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.OrderItem;
import com.paypay.oa.order.util.JsonConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderHistoryDetailsRepsonse {

	private Long orderId;
    private Long merchantId;
    private Long consumerId;
    private Long storeId;
    private int totalPrice;
    private String currency;
    private String status;
    private  List<GetOrderHistoryItemResponse> orderItems;

    @Data
    @Builder
    public static class GetOrderHistoryItemResponse {
        private Long orderItemId;
        private Long itemId;
        private String name;
        private int price;
        private int quantity;
        private JsonNode imageUrl;
        private JsonNode itemOptions;
    }
    
    @Data
    @Builder
    public static class GetOrderHistoryDetailsResponse
    {
    	private List<OrderHistoryDetailsRepsonse> orders;
    	private List<Long> stores; 
    }

    public static OrderHistoryDetailsRepsonse buildGetOrderHistoryResponse(Order order, List<OrderItem> orderItems) {
        List<GetOrderHistoryItemResponse> getOrderHistoryItemResponse = orderItems.stream()
                .map(orderItem ->
                        GetOrderHistoryItemResponse.builder().orderItemId(orderItem.getOrderItemId())
                                .imageUrl(JsonConverter.convertStringToJsonNode(orderItem.getImageUrl()))
                        		.itemId(orderItem.getItemId())
                                .name(orderItem.getName()).price(orderItem.getPrice())
                                .quantity(orderItem.getQuantity())
                                .itemOptions(Optional.ofNullable(orderItem.getOptions()).map(JsonConverter::convertStringToJsonNode).orElse(null))
                                .build())
                .collect(Collectors.toList());


        return OrderHistoryDetailsRepsonse.builder().orderId(order.getOrderId()).consumerId(order.getConsumerId())
                .storeId(order.getStoreId()).merchantId(order.getMerchantId())
                .totalPrice(order.getTotalPrice())
                .status(order.getOrderStatus().toString())
                .currency(order.getCurrency()).orderItems(getOrderHistoryItemResponse).build();

    }
    
    public static GetOrderHistoryDetailsResponse buildGetOrderHistoryDetailsResponse(List<OrderHistoryDetailsRepsonse> orderHistoryDetailsRepsonses)
    {
    	List<Long> getStores = orderHistoryDetailsRepsonses.stream()
                .map(order ->
                        order.getStoreId())
                .collect(Collectors.toList());
    	
    	return GetOrderHistoryDetailsResponse.builder().orders(orderHistoryDetailsRepsonses).stores(getStores).build();

    }
    
}
