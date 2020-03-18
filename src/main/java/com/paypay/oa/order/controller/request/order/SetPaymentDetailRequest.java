package com.paypay.oa.order.controller.request.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SetPaymentDetailRequest {
    @NotBlank(message = "Payment status must not be null")
    private String paymentStatus;
    private Long paymentId;
    @NotNull(message = "Payment reference number cannot be null")
    private String paymentRefNumber;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime paymentDate;
}
