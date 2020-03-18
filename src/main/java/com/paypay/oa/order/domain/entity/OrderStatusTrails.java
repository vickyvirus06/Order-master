package com.paypay.oa.order.domain.entity;

import com.paypay.oa.order.domain.entity.enums.OrderStatus;
import com.paypay.oa.order.domain.entity.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "order_status_trails")
public class OrderStatusTrails extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_status", columnDefinition = "enum('NEW', 'CONFIRMED', 'PREPARING', 'REJECTED', 'CANCELLED', 'READYFORPICKUP', 'PICKEDUP', 'NOPICKUP', 'DELIVERED', 'EXPIRED')")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @CreationTimestamp
    @Column(name = "status_on", updatable = false)
    private LocalDateTime statusOn;

    @Column(name = "type", columnDefinition = "enum('STORE', 'CONSUMER')")
    @Enumerated(EnumType.STRING)
    private Type type;
}
