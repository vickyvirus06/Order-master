package com.paypay.oa.order.application;

import com.paypay.oa.order.application.service.order.PickupReadyOrderService;
import com.paypay.oa.order.application.service.order.AcceptOrderService;
import com.paypay.oa.order.application.service.order.AddOrderService;
import com.paypay.oa.order.application.service.order.CancelOrderService;
import com.paypay.oa.order.application.service.order.GetOrderService;
import com.paypay.oa.order.application.service.order.OrderHistoryService;
import com.paypay.oa.order.application.service.order.OrderPaymentService;
import com.paypay.oa.order.application.service.order.OrderServiceBase;
import com.paypay.oa.order.application.service.order.RejectOrderService;
import com.paypay.oa.order.application.service.orderitem.AddOrderItemService;
import com.paypay.oa.order.application.service.orderitem.GetOrderItemService;
import com.paypay.oa.order.application.service.orderstaustrails.CreateOrderStatusTrailsService;
import com.paypay.oa.order.constants.OAOrderManagementResultCode;
import com.paypay.oa.order.constants.OrderManagementCode;
import com.paypay.oa.order.controller.request.order.AddOrderDetailsRequest;
import com.paypay.oa.order.controller.request.order.RejectOrderRequest;
import com.paypay.oa.order.controller.request.order.SetPaymentDetailRequest;
import com.paypay.oa.order.controller.response.order.AddOrderDetailsResponse;
import com.paypay.oa.order.controller.response.order.GetOrderDetailsResponse;
import com.paypay.oa.order.controller.response.order.GetOrderPickupCodeResponse;
import com.paypay.oa.order.controller.response.order.OrderHistoryDetailsRepsonse.GetOrderHistoryDetailsResponse;
import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.OrderItem;
import com.paypay.oa.order.domain.entity.OrderStatusTrails;
import com.paypay.oa.order.domain.entity.enums.OrderStatus;
import com.paypay.oa.order.domain.entity.enums.PaymentStatus;
import com.paypay.oa.order.controller.response.order.GetOrderListResponse;
import com.paypay.oa.order.domain.entity.enums.Type;
import com.paypay.oa.order.exception.OAOrderManagementException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderApplication {
    private final GetOrderService getOrderService;
    private final AddOrderService addOrderService;
    private final CancelOrderService cancelOrderService;
    private final OrderPaymentService orderPaymentService;
    private final OrderServiceBase orderServiceBase;
    private final AcceptOrderService acceptOrderService;
    private final CreateOrderStatusTrailsService createOrderStatusTrailsService;
    private final RejectOrderService rejectOrderService;
    private final GetOrderItemService getOrderItems;
    private final AddOrderItemService addOrderItemService;
    private final PickupReadyOrderService pickupReadyOrderService;
    private final OrderHistoryService orderHistoryService;

    /**
     * To add an order and items into the order
     *
     * @param addOrderDetailsRequest
     * @param consumerId
     * @return AddOrderAndItemsResponse
     */
    @Transactional
    public AddOrderDetailsResponse addOrder(AddOrderDetailsRequest addOrderDetailsRequest, Long consumerId, Long merchantId, Long storeId) {
        // add order and order items
        Order order = addOrderService.createOrder(addOrderDetailsRequest, consumerId, merchantId, storeId);
        orderServiceBase.isObjectNull(order, OAOrderManagementResultCode.ADD_ITEM_TO_ORDER_ERROR);
        // add order item
        addOrderItemService.addOrderItems(addOrderDetailsRequest, order.getId());
        //Add order status trail
        OrderStatusTrails orderStatusTrailsEntity = createOrderStatusTrailsService.add(order.getId(), OrderStatus.NEW, Type.CONSUMER);
        if (Objects.isNull(orderStatusTrailsEntity))
            throw new OAOrderManagementException(OAOrderManagementResultCode.ADD_ITEM_TO_ORDER_ERROR);
        //response
        return AddOrderDetailsResponse.buildAddOrderDetailsResponse(order.getOrderId(), order.getTotalPrice());
    }

    /**
     * Fetch order details
     *
     * @param consumerId
     * @param merchantId
     * @param storeId
     * @return GetOrderDetailsResponse
     */
    @Transactional
    public GetOrderDetailsResponse getOrderDetails(Long orderId, Long consumerId, Long merchantId, Long storeId) throws OAOrderManagementException {
        if (Objects.isNull(merchantId) || Objects.isNull(storeId))
            throw new OAOrderManagementException(HttpStatus.BAD_REQUEST, OAOrderManagementResultCode.MISSING_REQUEST_PARAMS);
        Order order = getOrderService.getOrderDetails(orderId, consumerId, merchantId, storeId);
        if (Objects.isNull(order))
            throw new OAOrderManagementException(OAOrderManagementResultCode.INVALID_ORDER);
        List<OrderItem> orderItems = getOrderItems.getOrderItems(order.getId());
        return GetOrderDetailsResponse.buildGetOrderResponse(order, orderItems);
    }

    /**
     * Cancel Order
     *
     * @param orderId
     * @param consumerId
     * @param merchantId
     * @param storeId
     */
    @Transactional
    public void cancelOrder(Long orderId, Long consumerId, Long merchantId, Long storeId) {
        // fetch order based on given details
        Order order = orderServiceBase.fetchOrderEntity(orderId, storeId, merchantId, consumerId);
        // verify order status
        if (order.getOrderStatus() != OrderStatus.CONFIRMED)
            throw new OAOrderManagementException(OAOrderManagementResultCode.INVALID_STATUS_FOR_CANCEL_ERROR);
        // update order status
        Order orderEntity = cancelOrderService.update(order);
        if (Objects.isNull(orderEntity))
            throw new OAOrderManagementException(OAOrderManagementResultCode.CANCEL_ORDER_ERROR);
        // update Order status trails
        OrderStatusTrails orderStatusTrailsEntity = createOrderStatusTrailsService.add(order.getId(), OrderStatus.CANCELLED, Type.CONSUMER);
        if (Objects.isNull(orderStatusTrailsEntity))
            throw new OAOrderManagementException(OAOrderManagementResultCode.CANCEL_ORDER_ERROR);
    }

    /**
     * To update payment detail and change the status of the specified order
     *
     * @param setPaymentDetailRequest
     * @param orderId
     * @param consumerId
     * @param merchantId
     * @param storeId
     */
    @Transactional
    public void setOrderPayment(SetPaymentDetailRequest setPaymentDetailRequest, Long orderId, Long consumerId, Long merchantId, Long storeId) {
        // fetch order using the given detail
        Order order = orderServiceBase.fetchOrderEntity(orderId, storeId, merchantId, consumerId);
        orderServiceBase.isObjectNull(order, OAOrderManagementResultCode.INVALID_ORDER);
        // verify order status
        if (order.getPaymentStatus() != PaymentStatus.UNPAID && order.getPaymentStatus() != PaymentStatus.FAILEDPAYMENT)
            throw new OAOrderManagementException(OAOrderManagementResultCode.INVALID_STATUS_FOR_PAYMENT_ERROR);
        // update order status with payment detail
        Order orderEntity = orderPaymentService.update(order, setPaymentDetailRequest);
        if (Objects.isNull(orderEntity))
            throw new OAOrderManagementException(OAOrderManagementResultCode.UPDATE_ORDER_PAYMENT_ERROR);
        if (setPaymentDetailRequest.getPaymentStatus().equalsIgnoreCase(OrderManagementCode.ORDER_PAYMENT_SUCCESS)) {
            // add Order status trails
            OrderStatusTrails orderStatusTrailsEntity = createOrderStatusTrailsService.add(order.getId(), OrderStatus.CONFIRMED, Type.CONSUMER);
            if (Objects.isNull(orderStatusTrailsEntity))
                throw new OAOrderManagementException(OAOrderManagementResultCode.UPDATE_ORDER_PAYMENT_ERROR);
        }
    }

    /**
     * Fetch order list
     *
     * @param merchantId
     * @param storeId
     * @param orderStatus
     * @return GetOrderDetailsResponse
     */
    public GetOrderListResponse getOrderList(Long merchantId, Long storeId, String orderStatus) throws OAOrderManagementException {
        List<Order> orderList = getOrderService.getOrderList(merchantId, storeId, OrderStatus.valueOf(orderStatus));
        if (Objects.isNull(orderList) || orderList.isEmpty()) {
            return new GetOrderListResponse(new ArrayList<>());
        }
        return GetOrderListResponse.buildGetOrderListResponse(orderList);
    }

    /**
     * Accept Order
     *
     * @param orderId
     * @param merchantId
     * @param storeId
     */
    public void acceptOrder(Long orderId, Long merchantId, Long storeId) {
        // fetch order based on given details
        Order order = orderServiceBase.fetchOrderEntity(orderId, storeId, merchantId);
        // verify order status
        if (order.getOrderStatus() != OrderStatus.CONFIRMED)
            throw new OAOrderManagementException(OAOrderManagementResultCode.INVALID_STATUS_FOR_ACCEPT_ERROR);
        // update order status
        Order orderEntity = acceptOrderService.update(order);
        if (Objects.isNull(orderEntity))
            throw new OAOrderManagementException(OAOrderManagementResultCode.ACCEPT_ORDER_ERROR);
        // update Order status trails
        OrderStatusTrails orderStatusTrailsEntity = createOrderStatusTrailsService.add(order.getId(), OrderStatus.PREPARING, Type.STORE);
        if (Objects.isNull(orderStatusTrailsEntity))
            throw new OAOrderManagementException(OAOrderManagementResultCode.ACCEPT_ORDER_ERROR);
    }

    /**
     * Reject Orders
     *
     * @param orderId
     * @param merchantId
     * @param storeId
     * @param rejectOrderRequest
     */
    @Transactional
    public void rejectOrder(Long orderId, Long merchantId, Long storeId, RejectOrderRequest rejectOrderRequest) {
        // fetch order based on given details
        Order order = orderServiceBase.fetchOrderEntity(orderId, storeId, merchantId);
        // verify order status
        if (order.getOrderStatus() != OrderStatus.CONFIRMED)
            throw new OAOrderManagementException(OAOrderManagementResultCode.INVALID_STATUS_FOR_REJECT_ERROR);
        // update order status
        Order orderEntity = rejectOrderService.update(order, rejectOrderRequest.getRejectReason());
        if (Objects.isNull(orderEntity))
            throw new OAOrderManagementException(OAOrderManagementResultCode.REJECT_ORDER_ERROR);
        // update Order status trails
        OrderStatusTrails orderStatusTrailsEntity = createOrderStatusTrailsService.add(order.getId(), OrderStatus.REJECTED, Type.STORE);
        if (Objects.isNull(orderStatusTrailsEntity))
            throw new OAOrderManagementException(OAOrderManagementResultCode.REJECT_ORDER_ERROR);
    }

    /**
     * Ready For Pickup Order
     *
     * @param orderId
     * @param merchantId
     * @param storeId
     */
    public GetOrderPickupCodeResponse readyForPickupOrder(Long orderId, Long merchantId, Long storeId) {
        // fetch order based on given details
        Order order = orderServiceBase.fetchOrderEntity(orderId, storeId, merchantId);
        // verify order status
        if (order.getOrderStatus() != OrderStatus.PREPARING)
            throw new OAOrderManagementException(OAOrderManagementResultCode.INVALID_STATUS_FOR_PICKUP_READY_ERROR);
        // update order status
        Order orderEntity = pickupReadyOrderService.update(order);
        if (Objects.isNull(orderEntity))
            throw new OAOrderManagementException(OAOrderManagementResultCode.READY_FOR_PICKUP_ORDER_ERROR);
        // update Order status trails
        OrderStatusTrails orderStatusTrailsEntity = createOrderStatusTrailsService.add(order.getId(), OrderStatus.READYFORPICKUP, Type.STORE);
        if (Objects.isNull(orderStatusTrailsEntity))
            throw new OAOrderManagementException(OAOrderManagementResultCode.READY_FOR_PICKUP_ORDER_ERROR);
        //response
        return GetOrderPickupCodeResponse.buildOrderPickupCodeResponse(orderEntity.getOrderId(), orderEntity.getPickupCode());
    }
    
    public GetOrderHistoryDetailsResponse getOrderHistory(Long consumerId)
    {
    	//response
        return orderHistoryService.getOrderHistory(consumerId);
    }
}
