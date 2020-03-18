package com.paypay.oa.order.controller.response;

import lombok.Data;

@Data
public class ResultInfoDto {
    private String code;
    private String message;
    private String codeId;

    public ResultInfoDto() {
    }

    public ResultInfoDto(String codeId, String code) {
        super();
        this.code = code;
        this.codeId = codeId;
    }

    public ResultInfoDto(String codeId, String code, String message) {
        this.code = code;
        this.codeId = codeId;
        this.message = message;
    }
}
