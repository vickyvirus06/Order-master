package com.paypay.oa.order.controller.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrderPickupCodeResponse {
    private Long orderId;
    private String pickupCode;

    public static GetOrderPickupCodeResponse buildOrderPickupCodeResponse(Long orderId, String pickupCode) {
        return GetOrderPickupCodeResponse.builder()
                .orderId(orderId)
                .pickupCode(pickupCode)
                .build();
    }
}
