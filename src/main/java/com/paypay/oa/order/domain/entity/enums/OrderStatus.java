package com.paypay.oa.order.domain.entity.enums;

import java.io.Serializable;

public enum OrderStatus implements Serializable {
    NEW, CONFIRMED, PREPARING, REJECTED, CANCELLED, READYFORPICKUP, PICKEDUP, NOPICKUP, DELIVERED, EXPIRED
}
