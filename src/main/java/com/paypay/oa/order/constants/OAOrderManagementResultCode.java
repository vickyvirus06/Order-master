package com.paypay.oa.order.constants;

import com.paypay.oa.order.controller.response.ResultInfoDto;
import lombok.Getter;

@Getter
public class OAOrderManagementResultCode {
    // ERROR Common to All
    public static final ResultInfoDto OA_ORDER_GENERAL_EXCEPTION = new ResultInfoDto("23000001", "INTERNAL_SERVER_ERROR", "Something went wrong on Order Ahead service side");
    public static final ResultInfoDto JSON_PARSE_ERROR = new ResultInfoDto("23000002", "JSON_PARSE_ERROR", "Error while parsing json object");
    public static final ResultInfoDto INVALID_ORDER_ID = new ResultInfoDto("23000003", "INVALID_ORDER_ID", "Please provide a valid order id");
    public static final ResultInfoDto INVALID_ORDER = new ResultInfoDto("23000004", "INVALID_ORDER_ID", "Selected order is invalid");
    public static final ResultInfoDto INVALID_ORDER_ITEM_ID = new ResultInfoDto("23000005", "INVALID_ORDER_ITEM_ID", "Please provide a valid order item id");
    public static final ResultInfoDto INVALID_REQUEST_PARAMS = new ResultInfoDto("23000006", "INVALID_REQUEST_PARAMS", "Invalid request params");
    public static final ResultInfoDto MISSING_REQUEST_PARAMS = new ResultInfoDto("23000007", "MISSING_REQUEST_PARAMS", "Some mandatory parameters are missing");

    // ERROR related to POST /v1/order/consumer/{consumerId}
    public static final ResultInfoDto ADD_ITEM_TO_ORDER_ERROR = new ResultInfoDto("23001001", "ADD_ITEM_TO_ORDER_FAILED", "Error while adding an item to order");
    public static final ResultInfoDto ORDER_ID_MISSING_ERROR = new ResultInfoDto("23001002", "ORDER_ID_MISSING_ERROR", "Order already exists, Please provide the order id");

    // ERROR related to PATCH /v1/order/{orderId}/consumer/{consumerId}/merchant/{merchantId}/store/{storeId}/cancel
    public static final ResultInfoDto INVALID_STATUS_FOR_CANCEL_ERROR = new ResultInfoDto("23002001", "INVALID_STATUS_FOR_CANCEL_ERROR", "This order can not be cancelled now");
    public static final ResultInfoDto CANCEL_ORDER_ERROR = new ResultInfoDto("23002002", "CANCEL_ORDER_ERROR", "Error while cancelling order");

    // ERROR related to PATCH /v1/order/{orderId}/consumer/{consumerId}/merchant/{merchantId}/store/{storeId}/payment
    public static final ResultInfoDto INVALID_STATUS_FOR_PAYMENT_ERROR = new ResultInfoDto("23003001", "INVALID_STATUS_FOR_PAYMENT_ERROR", "Payment cannot be set to this Order");
    public static final ResultInfoDto UPDATE_ORDER_PAYMENT_ERROR = new ResultInfoDto("23003002", "UPDATE_ORDER_PAYMENT__ERROR", "Error while updating payment detail of the Order");

    // ERROR related to PATCH /v1/order/{orderId}/consumer/{consumerId}/merchant/{merchantId}/store/{storeId}/accept
    public static final ResultInfoDto INVALID_STATUS_FOR_ACCEPT_ERROR = new ResultInfoDto("23004001", "INVALID_STATUS_FOR_ACCEPT_ERROR", "This order cannot be accepted now");
    public static final ResultInfoDto ACCEPT_ORDER_ERROR = new ResultInfoDto("23004002", "ACCEPT_ORDER_ERROR", "Error while accepting order");

    // ERROR related to PATCH /v1/order/{orderId}/consumer/{consumerId}/merchant/{merchantId}/store/{storeId}/reject
    public static final ResultInfoDto INVALID_STATUS_FOR_REJECT_ERROR = new ResultInfoDto("23005001", "INVALID_STATUS_FOR_REJECT_ERROR", "This order cannot be rejected");
    public static final ResultInfoDto REJECT_ORDER_ERROR = new ResultInfoDto("23005002", "REJECT_ORDER_ERROR", "Error while rejecting order");

    // ERROR related to PATCH /v1/order/{orderId}/consumer/{consumerId}/merchant/{merchantId}/store/{storeId}/ready
    public static final ResultInfoDto INVALID_STATUS_FOR_PICKUP_READY_ERROR = new ResultInfoDto("23006001", "INVALID_STATUS_FOR_PICKUP_READY_ERROR", "This order cannot be set as ready for pickup now");
    public static final ResultInfoDto READY_FOR_PICKUP_ORDER_ERROR = new ResultInfoDto("23006002", "READY_FOR_PICKUP_ORDER_ERROR", "Error while setting ready for pickup code");
    public static final ResultInfoDto READY_FOR_PICKUP_ORDER_PICKUPCODE_ERROR = new ResultInfoDto("23006003", "READY_FOR_PICKUP_ORDER_PICKUPCODE_ERROR", "Error while setting ready for pickup code. Unable to generate pickup code");

    // SUCCESS
    public static final ResultInfoDto API_SUCCESS = new ResultInfoDto("23000000", "SUCCESS", "Request Successfully fulfilled.");
}
