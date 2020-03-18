package com.paypay.oa.order.exception.handler;

import com.paypay.oa.order.constants.OAOrderManagementResultCode;
import com.paypay.oa.order.controller.response.GenericServerResponse;
import com.paypay.oa.order.controller.response.ResultInfoDto;
import com.paypay.oa.order.exception.OAOrderManagementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String DEFAULT_EXCEPTION_CAUSE_FORMAT = "Exception : {} , Cause : {}";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GenericServerResponse> handleValidationException(
            final HttpServletResponse response, MethodArgumentNotValidException e) {
        log.error(DEFAULT_EXCEPTION_CAUSE_FORMAT, e.getClass(), e.getMessage());
        ResultInfoDto info = new ResultInfoDto();
        info.setCode(OAOrderManagementResultCode.INVALID_REQUEST_PARAMS.getCode());
        info.setCodeId(OAOrderManagementResultCode.INVALID_REQUEST_PARAMS.getCodeId());
        List<String> errorMsg =
                e.getBindingResult().getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());
        info.setMessage(
                appendRequestId(response, StringUtils.collectionToDelimitedString(errorMsg, ", ")));
        GenericServerResponse serverResponse = new GenericServerResponse();
        serverResponse.setResultInfoDto(info);
        return new ResponseEntity<>(serverResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<GenericServerResponse> handleMissingParams(
            final HttpServletResponse response, MissingServletRequestParameterException ex) {
        ResultInfoDto info = new ResultInfoDto();
        info.setCode(OAOrderManagementResultCode.INVALID_REQUEST_PARAMS.getCode());
        info.setCodeId(OAOrderManagementResultCode.INVALID_REQUEST_PARAMS.getCodeId());
        info.setMessage(appendRequestId(response, "please provide " + ex.getParameterName()));
        GenericServerResponse serverResponse = new GenericServerResponse();
        serverResponse.setResultInfoDto(info);
        return new ResponseEntity<>(serverResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GenericServerResponse> handleConstraintViolation(
            final HttpServletResponse response, ConstraintViolationException ex) {
        ResultInfoDto info = new ResultInfoDto();
        info.setCode(OAOrderManagementResultCode.INVALID_REQUEST_PARAMS.getCode());
        info.setCodeId(OAOrderManagementResultCode.INVALID_REQUEST_PARAMS.getCodeId());
        info.setMessage(appendRequestId(response, ex.getMessage()));
        GenericServerResponse serverResponse = new GenericServerResponse();
        serverResponse.setResultInfoDto(info);
        return new ResponseEntity<>(serverResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OAOrderManagementException.class)
    public ResponseEntity<GenericServerResponse> handleGeneralOAOrderException(
            final HttpServletResponse response, OAOrderManagementException e) {
        log.error(DEFAULT_EXCEPTION_CAUSE_FORMAT, e.getClass(), e.getMessage());

        String errorMessage =
                e.getResultInfoDto().getMessage() != null
                        ? e.getResultInfoDto().getMessage()
                        : e.getMessage();
        e.getResultInfoDto().setMessage(appendRequestId(response, errorMessage));
        GenericServerResponse serverResponse = new GenericServerResponse();
        serverResponse.setResultInfoDto(e.getResultInfoDto());
        return new ResponseEntity<>(serverResponse, e.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericServerResponse> handleGeneralException(
            final HttpServletResponse response, Exception e) {
        log.error(DEFAULT_EXCEPTION_CAUSE_FORMAT, e.getClass(), e.getMessage());
        e.printStackTrace();
        ResultInfoDto info = new ResultInfoDto();
        info.setCode(OAOrderManagementResultCode.OA_ORDER_GENERAL_EXCEPTION.getCode());
        info.setCodeId(OAOrderManagementResultCode.OA_ORDER_GENERAL_EXCEPTION.getCodeId());
        info.setMessage(e.getMessage());
        GenericServerResponse serverResponse = new GenericServerResponse();
        serverResponse.setResultInfoDto(info);
        return new ResponseEntity<>(serverResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String appendRequestId(HttpServletResponse response, String msg) {
        return new StringBuilder().append(msg).toString();
    }
}
