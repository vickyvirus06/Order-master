package com.paypay.oa.order.domain.entity.enums;

import java.io.Serializable;

public enum PaymentStatus implements Serializable {
    UNPAID,
    PAID,
    REFUND,
    TRANSFERED,
    FAILEDPAYMENT,
    FAILEDREFUND
}
