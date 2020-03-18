package com.paypay.oa.order.util;

import com.paypay.oa.order.constants.OAOrderManagementResultCode;
import com.paypay.oa.order.controller.response.GenericServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    public static <T> ResponseEntity<GenericServerResponse> getSuccessResponse(T data) {
        return ResponseEntity.status(HttpStatus.OK).body(genericSuccessResponse(data));
    }

    private static <T> GenericServerResponse<T> genericSuccessResponse(T data) {
        return new GenericServerResponse(OAOrderManagementResultCode.API_SUCCESS, data);
    }
}
