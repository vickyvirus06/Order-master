package com.paypay.oa.order.application.service.orderitem.impl;

import com.paypay.oa.order.application.repository.orderitem.PersistOrderItemRepo;
import com.paypay.oa.order.application.service.order.OrderServiceBase;
import com.paypay.oa.order.application.service.orderitem.AddOrderItemService;
import com.paypay.oa.order.constants.OAOrderManagementResultCode;
import com.paypay.oa.order.controller.request.order.AddOrderDetailsRequest;
import com.paypay.oa.order.domain.entity.OrderItem;
import com.paypay.oa.order.util.IDGenerator;
import com.paypay.oa.order.util.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddOrderItemServiceImpl implements AddOrderItemService {
    private final PersistOrderItemRepo persistOrderItemRepo;
    private final OrderServiceBase orderServiceBase;

    @Override
    @Transactional
    public void addOrderItems(AddOrderDetailsRequest addOrderDetailsRequest, Long orderId) {
        // add order item
        addOrderDetailsRequest.getOrderItems().stream().forEach(oi -> {
            OrderItem orderItem = persistOrderItemRepo.add(setOrderItemEntity(oi, orderId));
            orderServiceBase.isObjectNull(orderItem, OAOrderManagementResultCode.ADD_ITEM_TO_ORDER_ERROR);
        });
    }

    private OrderItem setOrderItemEntity(AddOrderDetailsRequest.AddOrderItemRequest addOrderItemRequest, Long orderId) {
        OrderItem orderItem = OrderItem.builder()
                .orderId(orderId)
                .orderItemId(IDGenerator.generateId())
                .itemId(addOrderItemRequest.getItemId())
                .itemStoreMapId(addOrderItemRequest.getItemStoreMapId())
                .name(addOrderItemRequest.getName())
                .price(addOrderItemRequest.getPrice())
                .imageUrl(JsonConverter.convertJsonToString(addOrderItemRequest.getImageUrl()))
                .quantity(addOrderItemRequest.getQuantity())
                .build();
        if (addOrderItemRequest.getItemOptions() != null)
            orderItem.setOptions(JsonConverter.convertJsonToString(addOrderItemRequest.getItemOptions()));
        return orderItem;
    }
}
