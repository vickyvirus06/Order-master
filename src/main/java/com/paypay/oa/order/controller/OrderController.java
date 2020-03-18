package com.paypay.oa.order.controller;

import com.paypay.oa.order.application.OrderApplication;
import com.paypay.oa.order.config.validator.Status;
import com.paypay.oa.order.controller.request.order.AddOrderDetailsRequest;
import com.paypay.oa.order.controller.request.order.RejectOrderRequest;
import com.paypay.oa.order.controller.request.order.SetPaymentDetailRequest;
import com.paypay.oa.order.controller.response.GenericServerResponse;
import com.paypay.oa.order.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "Order")
public class OrderController {
    private final OrderApplication orderApplication;

    /**
     * Add order and its items
     *
     * @param addOrderDetailsRequest
     * @param consumerId
     * @return ResponseEntity<GenericServerResponse>
     */
    @PostMapping(
            value = "/v1/order/consumer/{consumerId}/merchant/{merchantId}/store/{storeId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Add an order and its items into order.",
            notes = "Add an order and its items into the order based on consumer id.")
    public ResponseEntity<GenericServerResponse> addOrder(
            @RequestBody @Valid AddOrderDetailsRequest addOrderDetailsRequest,
            @PathVariable("consumerId") Long consumerId, @PathVariable("merchantId") Long merchantId, @PathVariable("storeId") Long storeId) {
        return ResponseUtil.getSuccessResponse(orderApplication.addOrder(addOrderDetailsRequest, consumerId, merchantId, storeId));
    }

    /**
     * Fetch order details
     *
     * @param consumerId
     * @param merchantId
     * @param storeId
     * @return ResponseEntity<GenericServerResponse>
     */
    @GetMapping(value = "/v1/order/{orderId}/consumer/{consumerId}/merchant/{merchantId}/store/{storeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get order details for a consumer.", notes = "Get order details for a consumer.")
    public ResponseEntity<GenericServerResponse> getOrderDetails(
            @PathVariable Long orderId, @PathVariable Long consumerId, @PathVariable Long merchantId, @PathVariable Long storeId) {
        return ResponseUtil.getSuccessResponse(orderApplication.getOrderDetails(orderId, consumerId, merchantId, storeId));
    }

    /**
     * Cancel and order before it get accepted
     *
     * @param orderId
     * @param consumerId
     * @param merchantId
     * @param storeId
     * @return ResponseEntity<GenericServerResponse>
     */
    @PatchMapping(
            value = "/v1/order/{orderId}/consumer/{consumerId}/merchant/{merchantId}/store/{storeId}/cancel",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cancel an order before getting accepted by store.", notes = "Cancel an order before getting accepted by store.")
    public ResponseEntity<GenericServerResponse> cancelOrder(
            @PathVariable("orderId") Long orderId,
            @PathVariable("consumerId") Long consumerId,
            @PathVariable("merchantId") Long merchantId,
            @PathVariable("storeId") Long storeId) {
        orderApplication.cancelOrder(orderId, consumerId, merchantId, storeId);
        return ResponseUtil.getSuccessResponse(null);
    }

    /**
     * Update payment detail to the specified Order
     *
     * @param orderId
     * @param consumerId
     * @param merchantId
     * @param storeId
     * @return ResponseEntity<GenericServerResponse>
     */
    @PatchMapping(
            value = "/v1/order/{orderId}/consumer/{consumerId}/merchant/{merchantId}/store/{storeId}/payment",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cancel an order before getting accepted by store.", notes = "Cancel an order before getting accepted by store.")
    public ResponseEntity<GenericServerResponse> orderPayment(
            @RequestBody @Valid SetPaymentDetailRequest setPaymentDetailRequest,
            @PathVariable("orderId") Long orderId,
            @PathVariable("consumerId") Long consumerId,
            @PathVariable("merchantId") Long merchantId,
            @PathVariable("storeId") Long storeId) {
        orderApplication.setOrderPayment(setPaymentDetailRequest, orderId, consumerId, merchantId, storeId);
        return ResponseUtil.getSuccessResponse(null);
    }

    /**
     * Fetch order list for a store
     *
     * @param merchantId
     * @param storeId
     * @return ResponseEntity<GenericServerResponse>
     */
    @GetMapping(value = "/v1/orders/merchant/{merchantId}/store/{storeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get order list for a store.", notes = "Get order list for a store.")
    public ResponseEntity<GenericServerResponse> getOrderList(
            @PathVariable Long merchantId, @PathVariable Long storeId, @RequestParam @Status String orderStatus) {
        return ResponseUtil.getSuccessResponse(orderApplication.getOrderList(merchantId, storeId, orderStatus));
    }

    /**
     * To update acceptance status of the order from store
     *
     * @param orderId
     * @param merchantId
     * @param storeId
     * @return ResponseEntity<GenericServerResponse>
     */
    @PatchMapping(
            value = "/v1/order/{orderId}/merchant/{merchantId}/store/{storeId}/accept",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "To accept an order by the store.", notes = "To accept an order by the store.")
    public ResponseEntity<GenericServerResponse> acceptOrder(
            @PathVariable("orderId") Long orderId,
            @PathVariable("merchantId") Long merchantId,
            @PathVariable("storeId") Long storeId) {
        orderApplication.acceptOrder(orderId, merchantId, storeId);
        return ResponseUtil.getSuccessResponse(null);
    }

    /**
     * To update reject status of the order from store
     *
     * @param orderId
     * @param merchantId
     * @param storeId
     * @return ResponseEntity<GenericServerResponse>
     */
    @PatchMapping(
            value = "/v1/order/{orderId}/merchant/{merchantId}/store/{storeId}/reject",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "To reject an order by the store.", notes = "To reject an order by the store.")
    public ResponseEntity<GenericServerResponse> rejectOrder(
            @PathVariable("orderId") Long orderId,
            @PathVariable("merchantId") Long merchantId,
            @PathVariable("storeId") Long storeId,
            @RequestBody @Valid RejectOrderRequest rejectOrderRequest) {
        orderApplication.rejectOrder(orderId, merchantId, storeId, rejectOrderRequest);
        return ResponseUtil.getSuccessResponse(null);
    }

    /**
     * To update ready for pickup status of the order from store
     *
     * @param orderId
     * @param merchantId
     * @param storeId
     * @return ResponseEntity<GenericServerResponse>
     */
    @PatchMapping(
            value = "/v1/order/{orderId}/merchant/{merchantId}/store/{storeId}/ready",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "To accept an order by the store.", notes = "To accept an order by the store.")
    public ResponseEntity<GenericServerResponse> readyForPickupOrder(
            @PathVariable("orderId") Long orderId,
            @PathVariable("merchantId") Long merchantId,
            @PathVariable("storeId") Long storeId) {
        return ResponseUtil.getSuccessResponse(orderApplication.readyForPickupOrder(orderId, merchantId, storeId));
    }
}
