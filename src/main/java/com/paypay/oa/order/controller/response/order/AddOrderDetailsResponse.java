package com.paypay.oa.order.controller.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddOrderDetailsResponse {
    private Long orderId;
    private Integer totalPrice;

    public static AddOrderDetailsResponse buildAddOrderDetailsResponse(Long orderId, int totalPrice) {
        return AddOrderDetailsResponse.builder()
                .orderId(orderId)
                .totalPrice(totalPrice)
                .build();
    }
}
