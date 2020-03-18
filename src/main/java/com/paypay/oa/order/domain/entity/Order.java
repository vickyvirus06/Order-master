package com.paypay.oa.order.domain.entity;

import com.paypay.oa.order.domain.entity.enums.OrderStatus;

import com.paypay.oa.order.domain.entity.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "order_master")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_uid")
    private Long orderId;

    @Column(name = "consumer_id")
    private Long consumerId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "merchant_id")
    private Long merchantId;

    @Column(name = "store_pickup_address_id")
    private Long storePickupAddressId;

    @Column(name = "consumer_pickup_code")
    private String pickupCode;

    @Column(name = "total_item")
    private int totalItem;

    @Column(name = "consumer_remarks")
    private String consumerRemarks;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "currency")
    private String currency;

    @Column(name = "order_status", columnDefinition = "enum('NEW', 'CONFIRMED', 'PREPARING', 'REJECTED', 'CANCELLED', 'READYFORPICKUP', 'PICKEDUP', 'NOPICKUP', 'DELIVERED', 'EXPIRED')")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "payment_status", columnDefinition = "enum('UNPAID','PAID','REFUND','TRANSFERED')")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "order_ref_number")
    private String orderRefNumber;

    @CreationTimestamp
    @Column(name = "order_date", updatable = false)
    private LocalDateTime orderDate;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "payment_ref_number")
    private String paymentRefNumber;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "prepare_time")
    private int prepareTime;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Digits(integer=10, fraction=6)
    @Column(name = "consumer_latitude")
    private BigDecimal consumerLatitude;

    @Digits(integer=10, fraction=6)
    @Column(name = "consumer_longitude")
    private BigDecimal consumerLongitude;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
