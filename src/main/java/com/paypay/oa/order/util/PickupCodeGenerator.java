package com.paypay.oa.order.util;

import com.paypay.oa.order.application.service.order.OrderServiceBase;
import com.paypay.oa.order.constants.OAOrderManagementResultCode;
import com.paypay.oa.order.domain.entity.OrderPickupCode;
import com.paypay.oa.order.exception.OAOrderManagementException;

import java.util.Objects;

public class PickupCodeGenerator {

    /**
     * Generates a pickup code
     * @param storeId
     * @param orderServiceBase
     * @return String
     */
    public static synchronized String generatePickupCode(Long storeId, OrderServiceBase orderServiceBase) {
        return generateCode(storeId, orderServiceBase);
    }

    /**
     * Generates a sequence number and returns formatted string
     * @param storeId
     * @param orderServiceBase
     * @return String
     */
    private static String generateCode(Long storeId, OrderServiceBase orderServiceBase) {
        OrderPickupCode orderPickupCode = orderServiceBase.fetchOrderPickupCodeEntity(storeId);
        Integer pickupCode = null;
        if (!Objects.isNull(orderPickupCode)) {
            pickupCode = orderPickupCode.getCurrentValue();
            if (pickupCode>=99) {
                pickupCode = 0;
            }
            orderPickupCode.setCurrentValue(++pickupCode);
            orderPickupCode = orderServiceBase.updateOrderPickCode(orderPickupCode);
        } else {
            pickupCode = 1;
            orderPickupCode = OrderPickupCode.builder()
                    .storeId(storeId)
                    .currentValue(pickupCode).build();
            orderPickupCode = orderServiceBase.createOrderPickupCode(orderPickupCode);
        }
        if (Objects.isNull(orderPickupCode))
            throw new OAOrderManagementException(OAOrderManagementResultCode.READY_FOR_PICKUP_ORDER_PICKUPCODE_ERROR);

        return String.format("%02d", pickupCode);
    }
}
