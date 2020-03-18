package com.paypay.oa.order.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypay.oa.order.constants.OAOrderManagementResultCode;
import com.paypay.oa.order.controller.request.order.ItemOptions;
import com.paypay.oa.order.exception.OAOrderManagementException;
import org.springframework.http.HttpStatus;

public class JsonConverter {
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode convertStringToJsonNode(String str) {
        try {
            return objectMapper.readTree(str);
        } catch (JsonProcessingException e) {
            throw new OAOrderManagementException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), OAOrderManagementResultCode.JSON_PARSE_ERROR);
        }
    }

    public static <T> String convertJsonToString(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new OAOrderManagementException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), OAOrderManagementResultCode.JSON_PARSE_ERROR);
        }
    }

    public static ItemOptions convertStringToJson(String itemOptions) {
        try {
            return objectMapper.readValue(itemOptions, ItemOptions.class);
        } catch (JsonProcessingException e) {
            throw new OAOrderManagementException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), OAOrderManagementResultCode.JSON_PARSE_ERROR);
        }
    }
}
