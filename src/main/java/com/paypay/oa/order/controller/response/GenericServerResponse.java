package com.paypay.oa.order.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GenericServerResponse<T> {
    @JsonProperty("resultInfo")
    private ResultInfoDto resultInfoDto;

    @JsonProperty("data")
    @JsonInclude
    private T data;

    public GenericServerResponse() {
    }

    public GenericServerResponse(ResultInfoDto resultInfoDto, T data) {
        this.data = data;
        this.resultInfoDto = resultInfoDto;
    }
}
