package com.paypay.oa.order.exception;

import com.paypay.oa.order.constants.OAOrderManagementResultCode;
import com.paypay.oa.order.controller.response.ResultInfoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class OAOrderManagementException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    protected ResultInfoDto resultInfoDto = OAOrderManagementResultCode.OA_ORDER_GENERAL_EXCEPTION;
    protected HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public OAOrderManagementException() {
        super();
    }

    public OAOrderManagementException(String msg) {
        super(msg);
    }

    public OAOrderManagementException(HttpStatus status, String msg) {
        super(msg);
        this.status = status;
    }

    public OAOrderManagementException(HttpStatus status, String msg, ResultInfoDto resultInfoDto) {
        super(msg);
        this.resultInfoDto = resultInfoDto;
        this.status = status;
    }

    public OAOrderManagementException(ResultInfoDto resultInfoDto) {
        super();
        this.resultInfoDto = resultInfoDto;
    }

    public OAOrderManagementException(HttpStatus status, ResultInfoDto resultInfoDto) {
        super();
        this.resultInfoDto = resultInfoDto;
        this.status = status;
    }

    public OAOrderManagementException(ResultInfoDto resultInfoDto, String message) {
        super();
        this.resultInfoDto = resultInfoDto;
    }
}
