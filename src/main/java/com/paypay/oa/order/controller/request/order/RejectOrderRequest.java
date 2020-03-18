package com.paypay.oa.order.controller.request.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class RejectOrderRequest {
    @NotBlank(message = "Please provide reject reason")
    private String rejectReason;
}
