package com.paypay.oa.order.controller.response;

import lombok.Value;

@Value
public class ErrorResource {
    private String status;
    private String statusCode;
    private String statusMessage;
}
