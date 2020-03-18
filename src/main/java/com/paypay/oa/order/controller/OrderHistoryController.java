package com.paypay.oa.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.paypay.oa.order.application.OrderApplication;
import com.paypay.oa.order.controller.response.GenericServerResponse;
import com.paypay.oa.order.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api("OrderHistory")
public class OrderHistoryController {
	private final OrderApplication orderApplication;

    @GetMapping(value = "/v1/orderHistory/consumer/{consumerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get order history details for a consumer.", notes = "Get order history details for a consumer.")
    public ResponseEntity<GenericServerResponse> getOrderDetails(@PathVariable Long consumerId) {
    	return ResponseUtil.getSuccessResponse(orderApplication.getOrderHistory(consumerId));

    }
    
    

}
