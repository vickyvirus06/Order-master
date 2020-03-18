package com.paypay.oa.order.controller.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AddOrderDetailsRequest {
    @NotNull(message = "Store pick up address id must not be null")
    private Long storePickupAddressId;
    @NotNull(message = "Please provide the total price")
    private Integer totalPrice;
    private String currency;
    private Integer totalItem;
    private Integer prepareTime;
    private BigDecimal consumerLatitude;
    private BigDecimal consumerLongitude;
    private String consumerRemarks;
    @NotNull(message = "Order items cannot not be null")
    @Valid
    private List<AddOrderItemRequest> orderItems;

    @Data
    public static class AddOrderItemRequest {

        @NotNull(message = "Item id must not be null")
        private Long itemId;
        @NotNull(message = "Item map id must not be null")
        private Long itemStoreMapId;
        private String name;
        private Integer price;
        private Integer quantity;
        @NotNull(message = "Image url name should not be null")
        @Valid
        private ImageUrl imageUrl;

        @Valid
        private ItemOptions itemOptions;

        @Data
        public static class ItemOptions {
            @Valid
            List<OptionGroup> optionGroup;
            private int totalPrice;

            @Data
            public static class OptionGroup {
                private long groupId;
                private String name;
                @Valid
                private List<Options> options;

                @Data
                public static class Options {
                    private Long optionStoreMapId;
                    private long optionId;
                    private String name;
                    @NotNull(message = "Please provide the price of an option")
                    private Integer price;
                }
            }
        }

        @Data
        public static class ImageUrl {
            @NotBlank(message = "Provide the url of the image")
            private String url;
            private String type;
        }
    }

}
