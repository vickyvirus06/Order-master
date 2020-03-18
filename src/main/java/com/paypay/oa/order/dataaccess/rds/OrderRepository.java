package com.paypay.oa.order.dataaccess.rds;

import com.paypay.oa.order.domain.entity.Order;
import com.paypay.oa.order.domain.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * @param storeId
     * @param merchantId
     * @param consumerId
     * @param orderStatus
     * @return Order
     */
    Order findByOrderIdAndStoreIdAndMerchantIdAndConsumerIdAndOrderStatus(Long orderId, Long storeId, Long merchantId, Long consumerId, OrderStatus orderStatus);

    /**
     * @param storeId
     * @param merchantId
     * @return Order
     */
    Order findByOrderIdAndStoreIdAndMerchantId(Long orderId, Long storeId, Long merchantId);

    /**
     *
     * @param orderId
     * @param storeId
     * @param merchantId
     * @param consumerId
     * @return Order
     */
    Order findByOrderIdAndStoreIdAndMerchantIdAndConsumerId(Long orderId, Long storeId, Long merchantId, Long consumerId);

    /**
     *
     * @param storeId
     * @param merchantId
     * @param orderStatus
     * @return List
     */
    @Query(value = "from Order where storeId = :storeId and merchantId = :merchantId and orderStatus = :orderStatus ORDER BY createdAt ASC")
    List<Order> findByStoreIdAndMerchantIdAndOrderStatus(Long storeId, Long merchantId, OrderStatus orderStatus);
    
    /**
     * @param consumerId
     * @return
     */
    List<Order> findByConsumerId(Long consumerId);

}
